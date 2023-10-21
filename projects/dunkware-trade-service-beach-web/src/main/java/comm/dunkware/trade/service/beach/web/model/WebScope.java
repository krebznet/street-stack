package comm.dunkware.trade.service.beach.web.model;

public class WebScope {
	
	private WebScopeAction action; // drop | add 
	private WebScopeLevel level;
	private int id; // the id of either the account / system / trade order 

	public WebScopeAction getAction() {
		return action;
	}
	public void setAction(WebScopeAction action) {
		this.action = action;
	}
	public WebScopeLevel getLevel() {
		return level;
	}
	public void setLevel(WebScopeLevel level) {
		this.level = level;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
