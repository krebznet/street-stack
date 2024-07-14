package com.dunkware.street.street.smart.ui.resourcetree;


public class PortfolioTreeNode extends AbstractTreeNode {
    public PortfolioTreeNode(String name) {
        super(name);
    }

    public String getType() {
        return "ResourceTree";
    }

    public String getLabel() {
        return "Resources";
    }

    public String getIconPath() {
        return "images/icons/obj16/node2.png";
    }
}