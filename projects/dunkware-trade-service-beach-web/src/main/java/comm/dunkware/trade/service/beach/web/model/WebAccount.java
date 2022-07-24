package comm.dunkware.trade.service.beach.web.model;

public class WebAccount {

	private long id; 
	private String name; 
	private String borker; 
	private String status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBorker() {
		return borker;
	}
	public void setBorker(String borker) {
		this.borker = borker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	
	
}
