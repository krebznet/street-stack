package com.dunkware.trade.tick.provider.atick.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.tick.provider.atick.ActiveTickProvider;

import at.feedapi.ATCallback;
import at.feedapi.ATCallback.ATLoginResponseCallback;
import at.feedapi.ATCallback.ATOutputMessageCallback;
import at.feedapi.ATCallback.ATRequestTimeoutCallback;
import at.feedapi.ATCallback.ATServerTimeUpdateCallback;
import at.feedapi.ATCallback.ATSessionStatusChangeCallback;
import at.feedapi.ActiveTickServerAPI;
import at.feedapi.Helpers;
import at.feedapi.Session;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATGUID;
import at.shared.ATServerAPIDefines.ATLOGIN_RESPONSE;
import at.shared.ATServerAPIDefines.SYSTEMTIME;
import at.utils.jlib.Errors;
import at.utils.jlib.OutputMessage;

public class ATProviderSession extends ATCallback implements ATLoginResponseCallback, ATServerTimeUpdateCallback,
		ATRequestTimeoutCallback, ATSessionStatusChangeCallback, ATOutputMessageCallback

{
	at.feedapi.Session m_session;
	ActiveTickServerAPI m_serverapi;
	ATProviderRequestor m_requestor;
	ATProviderStreamer m_streamer;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	long m_lastRequest;
	String m_userid;
	String m_password;
	ATGUID m_apiKey;

	private ActiveTickProvider provider;
	
	public ATProviderSession(ActiveTickServerAPI serverapi, ActiveTickProvider provider) {
		m_serverapi = serverapi;
		
		this.provider = provider;
	}

	public ActiveTickProvider getProvider() {
		return provider;
	}

	public ActiveTickServerAPI GetServerAPI() {
		return m_serverapi;
	}

	public at.feedapi.Session GetSession() {
		return m_session;
	}

	public ATProviderStreamer GetStreamer() {
		return m_streamer;
	}

	public ATProviderRequestor GetRequestor() {
		return m_requestor;
	}

	public boolean Init(ATGUID apiKey, String serverHostname, int serverPort, String userId, String password) {
		if (m_session != null)
			m_serverapi.ATShutdownSession(m_session);

		m_session = m_serverapi.ATCreateSession();
		m_streamer = new ATProviderStreamer(this,provider);
		m_requestor = new ATProviderRequestor(provider,this, m_streamer);

		m_userid = userId;
		m_password = password;
		m_apiKey = apiKey;

		long rc = m_serverapi.ATSetAPIKey(m_session, m_apiKey);

		m_session.SetServerTimeUpdateCallback(this);
		m_session.SetOutputMessageCallback(this);

		boolean initrc = false;
		if (rc == Errors.ERROR_SUCCESS)
			initrc = m_serverapi.ATInitSession(m_session, serverHostname, serverHostname, serverPort, this);

		return initrc;
	}

	public boolean UnInit() {
		System.out.println("uNinit");
		if (m_session != null) {
			m_serverapi.ATShutdownSession(m_session);
			m_session = null;
		}

		return true;
	}

//ATLoginResponseCallback
	public void process(Session session, long requestId, ATLOGIN_RESPONSE response) {
		System.out.println("process login response?");
		provider.sessionLoginResponse(response);

	}

//ATServerTimeUpdateCallback
	public void process(SYSTEMTIME serverTime) {
//System.out.println(serverTime.second);

	}

//ATRequestTimeoutCallback
	public void process(long origRequest) {
		System.out.println("(" + origRequest + "): Request timed-out\n");
	}

//ATSessionStatusChangeCallback
	public void process(at.feedapi.Session session, ATServerAPIDefines.ATSessionStatusType type) {
		System.out.println("process session, type");
		String strStatusType = "";
		switch (type.m_atSessionStatusType) {
		case ATServerAPIDefines.ATSessionStatusType.SessionStatusConnected:
			strStatusType = "SessionStatusConnected";
			break;
		case ATServerAPIDefines.ATSessionStatusType.SessionStatusDisconnected:
			strStatusType = "SessionStatusDisconnected";
			break;
		case ATServerAPIDefines.ATSessionStatusType.SessionStatusDisconnectedDuplicateLogin:
			strStatusType = "SessionStatusDisconnectedDuplicateLogin";
			break;
		default:
			break;
		}
		if(strStatusType.length() == 0) { 
			logger.error("Amiga Status Change No Code in Switch  " +  type.m_atSessionStatusType);
		} else { 
			logger.error("Amiga Status Change Code " + strStatusType);
		}
		
	

// if we are connected to the server, send a login request
		if (type.m_atSessionStatusType == ATServerAPIDefines.ATSessionStatusType.SessionStatusConnected) {
			m_lastRequest = m_serverapi.ATCreateLoginRequest(session, m_userid, m_password, this);
			boolean rc = m_serverapi.ATSendRequest(session, m_lastRequest, ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT,
					this);

			System.out.println("SEND (" + m_lastRequest + "): Login request [" + m_userid + "] (rc = "
					+ (char) Helpers.ConvertBooleanToByte(rc) + ")");
		}
	}

	public void process(OutputMessage outputMessage) {

		System.out.println(outputMessage.GetMessage());
	}
}
