package com.dunkware.trade.service.stream.server.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "stream_session_problem")
public class StreamSessionProblemEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id; 
		
		@ManyToOne
		private StreamSessionEntity session; 
		
		@Column(length = 65555)
		private String problem;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public StreamSessionEntity getSession() {
			return session;
		}

		public void setSession(StreamSessionEntity session) {
			this.session = session;
		}

		public String getProblem() {
			return problem;
		}

		public void setProblem(String problem) {
			this.problem = problem;
		} 
		
		
}
