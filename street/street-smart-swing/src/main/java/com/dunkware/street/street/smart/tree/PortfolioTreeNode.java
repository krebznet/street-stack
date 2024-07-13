package com.dunkware.street.street.smart.tree;

import com.dunkware.street.street.smart.ui.resourcetree.AbstractTreeNode;

public class PortfolioTreeNode extends AbstractTreeNode {
    public PortfolioTreeNode(String name) {
        super(name);
    }

    public String getType() {
        return " ResourceTree";
    }

    public String getLabel() {
        return "ResourceTree";
    }

    public String getIconPath() {
        return "images/icons/obj16/coin-16.png";
    }
}