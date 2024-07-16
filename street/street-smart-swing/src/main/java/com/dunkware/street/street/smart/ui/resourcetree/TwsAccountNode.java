package com.dunkware.street.street.smart.ui.resourcetree;

class TwsAccountNode extends AbstractTreeNode {
    public TwsAccountNode(String name) {
        super(name);
    }

    public String getType() {

        return "TwsAccountNode";
    }

    public String getLabel() {
        return getName();
    }

    public String getIconPath() {
        return "images/icons/obj16/beach-16.png";
    }
}