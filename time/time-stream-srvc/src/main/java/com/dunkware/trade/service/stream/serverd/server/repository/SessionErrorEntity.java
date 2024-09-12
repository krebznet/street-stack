package com.dunkware.trade.service.stream.serverd.server.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "stream_session_problem")
public class SessionErrorEntity {

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
