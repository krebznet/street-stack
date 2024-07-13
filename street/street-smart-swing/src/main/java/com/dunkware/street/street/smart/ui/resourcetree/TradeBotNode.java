package com.dunkware.street.street.smart.ui.resourcetree;

import java.util.Observable;
import java.util.Observer;

class TradeBotNode extends AbstractTreeNode implements Observer {
    private String botType;
    private boolean running = false;

    public TradeBotNode(String name, String botType) {
        super(name);
        this.botType = botType;
    }

    public String getType() {
        return "TradeBotNode";
    }

    public String getLabel() {
        return running ? "TradeBot (Running): " + getName() : "TradeBot: " + getName();
    }

    public String getIconPath() {
        return running ? "images/icons/obj16/view-16.png" : "images/icons/obj16/view-16.png";
    }

    public String getBotType() {
        return botType;
    }

    public void setRunning(boolean running) {
        this.running = running;
        updateNode();
    }

  // @Override
   // public void update(ObservableTreeNode node, Object arg) {
  //      setRunning(!running); // Just for testing
  //  }
    
    

    @Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	private void updateNode() {
        if (getParent() != null && getParent() instanceof ObservableTreeNode) {
            ((ObservableTreeNode) getParent()).notifyObservers();
        }
    }
}