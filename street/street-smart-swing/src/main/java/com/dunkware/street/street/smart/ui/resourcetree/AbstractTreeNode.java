package com.dunkware.street.street.smart.ui.resourcetree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTreeNode implements TreeNode {
    private String name;
    private TreeNode parent;
    private List<TreeNode> children;

    public AbstractTreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
        child.setParent(this);
    }
}