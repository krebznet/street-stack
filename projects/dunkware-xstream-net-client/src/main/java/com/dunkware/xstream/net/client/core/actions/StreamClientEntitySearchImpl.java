package com.dunkware.xstream.net.client.core.actions;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntity;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.net.proto.stream.GEntityMatcher;
import com.dunkware.xstream.net.client.StreamClient;
import com.dunkware.xstream.net.client.StreamClientEntitySearch;
import com.dunkware.xstream.net.client.StreamClientEntitySearchCallBack;
import com.dunkware.xstream.net.client.StreamClientEntitySearchStatus;
import com.dunkware.xstream.net.client.StreamClientHandler;
import com.dunkware.xstream.net.client.core.StreamClientProto;

public class StreamClientEntitySearchImpl implements StreamClientEntitySearch, StreamClientHandler {

	private StreamClientEntitySearchCallBack observer;
	private List<GNetEntity> results = new ArrayList<GNetEntity>();
	private int searchId;
	private StreamClient client;
	private String retValues;
	private GEntityMatcher matcher;
	private String exception = null;
	private StreamClientEntitySearchStatus status = StreamClientEntitySearchStatus.PENDING;

	// state ==
	public void init(GEntityMatcher matcher, String retValues, StreamClientEntitySearchCallBack observer,
			StreamClient client) {
		this.client = client;
		this.searchId = DRandom.getRandom(1, 50000);
		this.observer = observer;
		this.client = client;
		this.retValues = retValues;
		this.matcher = matcher;
		Thread runner = new Thread() {

			public void run() {

				execute();
			}
		};

		runner.start();

	}

	public void execute() {
		client.addMessageHandler(this);
		GNetClientMessage searchRequest = StreamClientProto.entitySearchRequest(matcher, retValues, searchId);
		try {
			client.sendMessage(searchRequest);
			status = StreamClientEntitySearchStatus.SUBMITTED;
		} catch (Exception e) {
			exception = "Client Send Search Request Message Failed " + e.toString();
			status = StreamClientEntitySearchStatus.EXCEPTION;
			observer.onException(StreamClientEntitySearchImpl.this);

		}

	}

	@Override
	public int getSearchId() {
		return searchId;
	}

	@Override
	public String getException() {
		return exception;
	}

	@Override
	public List<GNetEntity> getResults() {
		return results;
	}

	@Override
	public StreamClientEntitySearchStatus getStatus() {
		return status;
	}

	@Override
	public void onMessage(GNetServerMessage message) {

		// look for response or look for exception
		if (StreamClientProto.isEntitySearchResponse(message, searchId)) {
			status = StreamClientEntitySearchStatus.RUNNING;
			observer.onResponse(StreamClientEntitySearchImpl.this);
		}
		if (StreamClientProto.isEntitySearchException(message, searchId)) {
			status = StreamClientEntitySearchStatus.EXCEPTION;
			exception = message.getEntitySearchException().getException();
			observer.onException(StreamClientEntitySearchImpl.this);
			client.removeMessageHandler(this);
			// remove client handler -- has to happen in its own thread.
		}
		if (StreamClientProto.isEntitySearchResults(message, searchId)) {
			results.addAll(message.getEntitySearchResults().getEntitiesList());
		}
		if (StreamClientProto.isEntitySearchComplete(message, searchId)) {
			status = StreamClientEntitySearchStatus.COMPLETED;
			observer.onComplete(StreamClientEntitySearchImpl.this);
			client.removeMessageHandler(this);
			// remove client handler;
		}

	}

}
