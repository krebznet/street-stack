package com.dunkware.street.street.smart.aigen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.formdev.flatlaf.FlatLightLaf;

public class ResourceTreeGen extends JFrame {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private ObservableTreeNode rootNode;

    public ResourceTreeGen() {
        setTitle("Trading Platform");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        rootNode = new ObservableTreeNode(new PortfolioTreeNode("Resources"));
        treeModel = new DefaultTreeModel(rootNode);
        rootNode.setTreeModel(treeModel); // Set tree model for root node
        tree = new JTree(treeModel) {
            @Override
            public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                if (value instanceof ObservableTreeNode) {
                    ObservableTreeNode node = (ObservableTreeNode) value;
                    Object userObject = node.getUserObject();
                    if (userObject instanceof TreeNode) {
                        return ((TreeNode) userObject).getLabel();
                    }
                }
                return super.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
            }
        };
        tree.setCellRenderer(new CustomTreeCellRenderer());
        tree.setShowsRootHandles(true);
        tree.setRootVisible(true);

        // Add context menu
        JPopupMenu popupMenu = new JPopupMenu();

        tree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int selRow = tree.getRowForLocation(e.getX(), e.getY());
                    if (selRow != -1) {
                        tree.setSelectionRow(selRow);
                        ObservableTreeNode selectedNode = (ObservableTreeNode) tree.getLastSelectedPathComponent();
                        if (selectedNode != null) {
                            TreeNode node = (TreeNode) selectedNode.getUserObject();
                            createPopupMenu(node, selectedNode, popupMenu);
                            popupMenu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            }

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selRow = tree.getRowForLocation(e.getX(), e.getY());
                    if (selRow != -1) {
                        tree.setSelectionRow(selRow);
                        ObservableTreeNode selectedNode = (ObservableTreeNode) tree.getLastSelectedPathComponent();
                        if (selectedNode != null) {
                            TreeNode node = (TreeNode) selectedNode.getUserObject();
                            if (node instanceof TradeBotNode) {
                                showTradeBotViewerDialog((TradeBotNode) node);
                            }
                        }
                    }
                }
            }
        });

        // Add the portfolio tree to the left side of the main frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree), new JPanel());
        splitPane.setDividerLocation(300);

        add(splitPane, BorderLayout.CENTER);

        // Add tool bar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton expandButton = new JButton("Expand", loadIcon("icons/expand_icon.png"));
        expandButton.addActionListener(e -> expandAllNodes(tree, true));
        toolBar.add(expandButton);

        JButton collapseButton = new JButton("Collapse", loadIcon("icons/collapse_icon.png"));
        collapseButton.addActionListener(e -> expandAllNodes(tree, false));
        toolBar.add(collapseButton);

        JButton mockActionButton = new JButton("Mock Action", loadIcon("icons/mock_action_icon.png"));
        mockActionButton.addActionListener(e -> showMockContextMenu(mockActionButton));
        toolBar.add(mockActionButton);

        toolBar.add(Box.createHorizontalGlue()); // Right-align the buttons
        add(toolBar, BorderLayout.NORTH);

        // Initialize login dialog
        showLoginDialog();
    }

    private void createPopupMenu(TreeNode node, ObservableTreeNode treeNode, JPopupMenu popupMenu) {
        popupMenu.removeAll();
        if (node instanceof PortfolioTreeNode) {
            JMenuItem twsConnectItem = new JMenuItem("Tws Connection");
            twsConnectItem.addActionListener(e -> showTwsConnectionDialog((PortfolioTreeNode) node, treeNode));
            popupMenu.add(twsConnectItem);
        } else if (node instanceof TwsAccountNode) {
            JMenuItem accountTradeBotItem = new JMenuItem("Account TradeBot");
            accountTradeBotItem.addActionListener(e -> showAccountTradeBotDialog((TwsAccountNode) node, treeNode));
            popupMenu.add(accountTradeBotItem);
        }
    }

    private void showTwsConnectionDialog(PortfolioTreeNode parentNode, ObservableTreeNode treeNode) {
        JDialog dialog = new JDialog(this, "Tws Connection", true);
        dialog.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Connection Name:");
        JTextField nameField = new JTextField();
        JLabel hostLabel = new JLabel("Host:");
        JTextField hostField = new JTextField();
        JLabel portLabel = new JLabel("Port:");
        JTextField portField = new JTextField();
        JLabel clientIdLabel = new JLabel("Client ID:");
        JTextField clientIdField = new JTextField();

        JButton connectButton = new JButton("Connect");
        JButton cancelButton = new JButton("Cancel");

        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(hostLabel);
        dialog.add(hostField);
        dialog.add(portLabel);
        dialog.add(portField);
        dialog.add(clientIdLabel);
        dialog.add(clientIdField);
        dialog.add(connectButton);
        dialog.add(cancelButton);

        connectButton.addActionListener(e -> {
            String name = nameField.getText();
            String host = hostField.getText();
            int port = Integer.parseInt(portField.getText());
            int clientId = Integer.parseInt(clientIdField.getText());

            // Simulate connection to TWS API (replace with actual connection code)
            if (connectToTwsApi(host, port, clientId)) {
                TwsNode twsNode = new TwsNode(name);
                ObservableTreeNode twsTreeNode = new ObservableTreeNode(twsNode);
                twsTreeNode.setTreeModel(treeModel); // Set tree model
                addMockAccounts(twsTreeNode);
                treeNode.add(twsTreeNode);
                treeModel.reload(rootNode);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to connect to TWS API", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showAccountTradeBotDialog(TwsAccountNode parentNode, ObservableTreeNode treeNode) {
        JDialog dialog = new JDialog(this, "Account TradeBot", true);
        dialog.setLayout(new GridLayout(3, 2));

        JLabel botNameLabel = new JLabel("Bot Name:");
        JTextField botNameField = new JTextField();
        JLabel botTypeLabel = new JLabel("Bot Type:");
        JComboBox<String> botTypeComboBox = new JComboBox<>(new String[]{"Bot 1", "Bot 2", "Bot 3"});

        JButton createButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");

        dialog.add(botNameLabel);
        dialog.add(botNameField);
        dialog.add(botTypeLabel);
        dialog.add(botTypeComboBox);
        dialog.add(createButton);
        dialog.add(cancelButton);

        createButton.addActionListener(e -> {
            String botName = botNameField.getText();
            String botType = (String) botTypeComboBox.getSelectedItem();

            TradeBotNode tradeBotNode = new TradeBotNode(botName, botType);
            ObservableTreeNode tradeBotTreeNode = new ObservableTreeNode(tradeBotNode);
            tradeBotTreeNode.setTreeModel(treeModel); // Set tree model
            treeNode.add(tradeBotTreeNode);
            treeModel.reload(rootNode);
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void addMockAccounts(ObservableTreeNode parentNode) {
        TwsAccountNode account1 = new TwsAccountNode("Account 1");
        TwsAccountNode account2 = new TwsAccountNode("Account 2");
        parentNode.add(new ObservableTreeNode(account1));
        parentNode.add(new ObservableTreeNode(account2));
    }

    private boolean connectToTwsApi(String host, int port, int clientId) {
        // Simulate a successful connection
        return true;
    }

    private void expandAllNodes(JTree tree, boolean expand) {
        ObservableTreeNode root = (ObservableTreeNode) tree.getModel().getRoot();
        expandAllNodes(tree, new TreePath(root), expand);
    }

    private void expandAllNodes(JTree tree, TreePath parent, boolean expand) {
        ObservableTreeNode node = (ObservableTreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration<?> e = node.children(); e.hasMoreElements(); ) {
                ObservableTreeNode n = (ObservableTreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAllNodes(tree, path, expand);
            }
        }

        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }

    private void showMockContextMenu(JButton source) {
        JPopupMenu mockMenu = new JPopupMenu();
        JMenuItem mockItem1 = new JMenuItem("Mock Item 1");
        JMenuItem mockItem2 = new JMenuItem("Mock Item 2");
        mockMenu.add(mockItem1);
        mockMenu.add(mockItem2);
        mockMenu.show(source, 0, source.getHeight());
    }

    private void showTradeBotViewerDialog(TradeBotNode tradeBotNode) {
        JDialog dialog = new JDialog(this, "TradeBot Viewer", true);
        dialog.setLayout(new BorderLayout());

        JLabel botLabel = new JLabel("TradeBot: " + tradeBotNode.getName() + " (" + tradeBotNode.getBotType() + ")");
        dialog.add(botLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showLoginDialog() {
        JDialog loginDialog = new JDialog(this, "Login", true);
        loginDialog.setUndecorated(true);
        loginDialog.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.DARK_GRAY);
        JLabel logoLabel = new JLabel("LOGO");
        logoLabel.setForeground(Color.WHITE);
        leftPanel.add(logoLabel);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel serverLabel = new JLabel("Street Server:");
        rightPanel.add(serverLabel, gbc);

        gbc.gridx = 1;
        JTextField serverField = new JTextField(15);
        rightPanel.add(serverField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel userLabel = new JLabel("Street User:");
        rightPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField userField = new JTextField(15);
        rightPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Street Password:");
        rightPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        rightPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        rightPanel.add(loginButton, gbc);

        loginDialog.add(leftPanel, BorderLayout.WEST);
        loginDialog.add(rightPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            // Authentication logic here
            loginDialog.dispose();
        });

        loginDialog.pack();
        loginDialog.setLocationRelativeTo(this);
        loginDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            ResourceTreeGen app = new ResourceTreeGen();
            app.setVisible(true);
        });
    }

    private ImageIcon loadIcon(String path) {
        URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

interface TreeNode {
    String getName();
    String getType();
    TreeNode getParent();
    void setParent(TreeNode parent);
    List<TreeNode> getChildren();
    String getLabel();
    String getIconPath();
}

abstract class AbstractTreeNode implements TreeNode {
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

class ObservableTreeNode extends DefaultMutableTreeNode {
    private List<Observer> observers = new ArrayList<>();
    private DefaultTreeModel treeModel;

    public ObservableTreeNode(TreeNode userObject) {
        super(userObject);
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        this.treeModel = treeModel;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, null);
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

class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof ObservableTreeNode) {
            ObservableTreeNode node = (ObservableTreeNode) value;
            TreeNode treeNode = node.getTreeNode();
            setIcon(loadIcon(treeNode.getIconPath()));
        }
        return c;
    }

    private ImageIcon loadIcon(String path) {
        URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

interface Observer {
    void update(ObservableTreeNode node, Object arg);
}

class PortfolioTreeNode extends AbstractTreeNode {
    public PortfolioTreeNode(String name) {
        super(name);
    }

    public String getType() {
        return "ResourceTree";
    }

    public String getLabel() {
        return "ResourceTree";
    }

    public String getIconPath() {
        return "icons/portfolio_icon.png";
    }
}

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
        return connected ? "icons/tws_icon.png" : "icons/tws_disconnected_icon.png";
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
        updateNode();
    }

    @Override
    public void update(ObservableTreeNode node, Object arg) {
        setConnected(!connected); // Just for testing
    }

    private void updateNode() {
        if (getParent() != null && getParent() instanceof ObservableTreeNode) {
            ((ObservableTreeNode) getParent()).notifyObservers();
        }
    }
}

class TwsAccountNode extends AbstractTreeNode {
    public TwsAccountNode(String name) {
        super(name);
    }

    public String getType() {
        return "TwsAccountNode";
    }

    public String getLabel() {
        return "Account: " + getName();
    }

    public String getIconPath() {
        return "icons/account_icon.png";
    }
}

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
        return running ? "icons/tradebot_running_icon.png" : "icons/tradebot_icon.png";
    }

    public String getBotType() {
        return botType;
    }

    public void setRunning(boolean running) {
        this.running = running;
        updateNode();
    }

    @Override
    public void update(ObservableTreeNode node, Object arg) {
        setRunning(!running); // Just for testing
    }

    private void updateNode() {
        if (getParent() != null && getParent() instanceof ObservableTreeNode) {
            ((ObservableTreeNode) getParent()).notifyObservers();
        }
    }
}
