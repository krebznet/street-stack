package com.dunkware.street.bot.time.stream;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// DataNode class
class DataNode {
    String id;
    String type;
    String name;
    List<DataNode> children;

    public DataNode(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(DataNode child) {
        children.add(child);
    }

    public List<DataNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return name;
    }
}

// UINode class
class UINode {
    DataNode dataNode;
    Icon icon;
    List<JMenuItem> contextMenuItems;

    public UINode(DataNode dataNode, Icon icon, List<JMenuItem> contextMenuItems) {
        this.dataNode = dataNode;
        this.icon = icon;
        this.contextMenuItems = contextMenuItems;
    }

    public DataNode getDataNode() {
        return dataNode;
    }

    public Icon getIcon() {
        return icon;
    }

    public List<JMenuItem> getContextMenuItems() {
        return contextMenuItems;
    }

    @Override
    public String toString() {
        return dataNode.toString();
    }
}

// TradeTree class
class TradeTree {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;

    public TradeTree() {
        root = new DefaultMutableTreeNode(new UINode(new DataNode("root", "tradeTree", "ALGO Tree"), null, new ArrayList<>()));
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        addContextMenuToRoot();
    }

    public JTree getTree() {
        return tree;
    }

	    private void addContextMenuToRoot() {
	        JPopupMenu rootMenu = new JPopupMenu();
	        JMenuItem addBrokerItem = new JMenuItem("Decision Tree");
        addBrokerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "New -> Trade Trade:");
            }
        });
        rootMenu.add(addBrokerItem);

        JMenuItem riskManagementItem = new JMenuItem("Traading Tree");
        riskManagementItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Risk Management Placeholder");
            }
        });
        rootMenu.add(riskManagementItem);

        tree.setComponentPopupMenu(rootMenu);
    }

    public void addNode(DataNode dataNode, DefaultMutableTreeNode parent) {
        UINode uiNode = createUINode(dataNode);
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(uiNode);
        parent.add(newNode);
        treeModel.reload(parent);
    }

    private UINode createUINode(DataNode dataNode) {
        // Define icons and context menu items based on dataNode type
        Icon icon = UIManager.getIcon("FileView.fileIcon"); // Placeholder icon
        List<JMenuItem> contextMenuItems = new ArrayList<>();

        // Example context menu item
        JMenuItem menuItem = new JMenuItem("Example Action");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action performed on: " + dataNode.name);
            }
        });
        contextMenuItems.add(menuItem);

        return new UINode(dataNode, icon, contextMenuItems);
    }
}

// Main class to test the implementation
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Trade Tree Example");
                TradeTree tradeTree = new TradeTree();
                frame.add(new JScrollPane(tradeTree.getTree()));

                // Adding some mock data
                DataNode tradeNode1 = new DataNode("1", "trade", "Trade Node 1");
                DataNode tradeNode2 = new DataNode("2", "trade", "Trade Node 2");
                DataNode tradeNode3 = new DataNode("3", "trade", "Trade Node 3");

                tradeTree.addNode(tradeNode1, (DefaultMutableTreeNode) tradeTree.getTree().getModel().getRoot());
                tradeTree.addNode(tradeNode2, (DefaultMutableTreeNode) tradeTree.getTree().getModel().getRoot());
                tradeTree.addNode(tradeNode3, (DefaultMutableTreeNode) tradeTree.getTree().getModel().getRoot());

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.setVisible(true);
            }
        });
    }
}
