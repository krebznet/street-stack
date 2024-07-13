package com.dunkware.street.street.smart.ui.resourcetree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

class ObservableTreeNode extends DefaultMutableTreeNode {
    private List<CustomObserver> observers = new ArrayList<>();
    private DefaultTreeModel treeModel;

    public ObservableTreeNode(TreeNode userObject) {
        super(userObject);
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        this.treeModel = treeModel;
    }

    public void addObserver(CustomObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CustomObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (CustomObserver observer : observers) {
            observer.update(this);
        }
    }

    public TreeNode getTreeNode() {
        return (TreeNode) getUserObject();
    }

    public void updateNode() {
        if (treeModel != null) {
            treeModel.nodeChanged(this);
        }
    }
}