package com.dunkware.trade.service.stream.server.session.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "stream_session_problem")
public class StreamSessionProblemDO {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id; 
		
		@ManyToOne
		private StreamSessionDO session; 
		
		private String problem;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public StreamSessionDO getSession() {
			return session;
		}

		public void setSession(StreamSessionDO session) {
			this.session = session;
		}

		public String getProblem() {
			return problem;
		}

		public void setProblem(String problem) {
			this.problem = problem;
		} 
		
		
}
