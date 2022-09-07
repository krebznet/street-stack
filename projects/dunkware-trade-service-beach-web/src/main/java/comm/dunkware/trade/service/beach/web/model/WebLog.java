package comm.dunkware.trade.service.beach.web.model;

import java.time.LocalDateTime;

public class WebLog {
	
	private LocalDateTime timestamp;
	private String type; 
	private String source; 
	private String log; // 
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
	

}
