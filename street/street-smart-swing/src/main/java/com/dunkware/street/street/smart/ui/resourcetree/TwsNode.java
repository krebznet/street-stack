package com.dunkware.street.street.smart.ui.resourcetree;

import java.util.Observable;
import java.util.Observer;

class TwsNode extends AbstractTreeNode implements Observer {
    private boolean connected = true;

    public TwsNode(String name) {
        super(name);
    }

    public String getType() {
        return "TwsNode";
    }

    public String getLabel() {
        return connected ? "TWS Connection: " + getName() : "TWS Connection (Disconnected): " + getName();
    }

    public String getIconPath() {
        return connected ? "images/icons/obj16/up-16.png" : "images/icons/obj16/up-16.png";
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
        updateNode();
    }

  
    private void updateNode() {
        if (getParent() != null && getParent() instanceof ObservableTreeNode) {
            ((ObservableTreeNode) getParent()).notifyObservers();
        }
    }

	@Override
	public void update(Observable o, Object arg) {
		
		 setConnected(!connected); // J
	}
}
