package com.dunkware.trade.tick.provider.atick.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.reactive.client.ReactiveRequest;
import org.eclipse.jetty.reactive.client.ReactiveResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class StreamTest {

	public static void main(String[] args) {
		new StreamTest();
	}

	public StreamTest() {

		try {

			SslContextFactory.Client sslContextFactory = new SslContextFactory.Client();
			sslContextFactory.setTrustAll(true);
			HttpClient httpClient = new HttpClient(sslContextFactory);
			httpClient.start();

			Request request = httpClient.newRequest(
					"https://api.activetick.com/stream.json?sessionid=12225759b81b421a9ce69db19c9222d8&symbols=AAPL_S-U,IBM_S-U");
			ReactiveRequest reactiveRequest = ReactiveRequest.newBuilder(request).build();
			Publisher<ReactiveResponse> publisher = reactiveRequest.response();
			MyBlockingSubscriber subscriber = new MyBlockingSubscriber();
			publisher.subscribe(subscriber);
			subscriber.block();
			
			//ReactiveResponse response = subscriber.block();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	public class MyBlockingSubscriber implements Subscriber<ReactiveResponse> {
		BlockingQueue<ReactiveResponse> sink = new LinkedBlockingQueue<>(1);

		@Override
		public void onSubscribe(Subscription subscription) {
			System.out.println("on subscribe");
			subscription.request(1);
		}

		@Override
		public void onNext(ReactiveResponse response) {
			
			sink.offer(response);
		}

		@Override
		public void onError(Throwable failure) {
			System.out.println("error " + failure.toString());
		}

		@Override
		public void onComplete() {
			System.out.println("on complete");
		}

		public ReactiveResponse block() throws InterruptedException {
			return sink.poll(5, TimeUnit.SECONDS);
		}
	}

}
