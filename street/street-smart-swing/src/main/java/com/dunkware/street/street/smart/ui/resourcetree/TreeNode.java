package com.dunkware.street.street.smart.ui.resourcetree;

import java.util.List;

interface TreeNode {
	
    String getName();
    
    String getType();
    
    TreeNode getParent();
    
    void setParent(TreeNode parent);
    
    List<TreeNode> getChildren();
    
    String getLabel();
    
    String getIconPath();
}
