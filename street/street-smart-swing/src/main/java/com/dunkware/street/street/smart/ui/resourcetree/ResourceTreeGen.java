package com.dunkware.street.street.smart.ui.resourcetree;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.dunkware.street.stream.model.alphabot.AlphaBotInput;
import com.dunkware.street.street.smart.tree.PortfolioTreeNode;
import com.dunkware.street.street.smart.ui.alphabot.AlphaBotDialog;

public class ResourceTreeGen extends JPanel {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private ObservableTreeNode rootNode;

    public ResourceTreeGen() {
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
                                showTradeBotViewer((TradeBotNode) node);
                            }
                        }
                    }
                }
            }
        });

        add(new JScrollPane(tree), BorderLayout.CENTER);

        // Add tool bar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton expandButton = new JButton( loadIcon("images/icons/obj16/back-16.png"));
        expandButton.addActionListener(e -> expandAllNodes(tree, true));
        toolBar.add(expandButton);

        JButton collapseButton = new JButton("", loadIcon("images/icons/obj16/container-16.png"));
        
        collapseButton.addActionListener(e -> expandAllNodes(tree, false));
        toolBar.add(collapseButton);
        
        JButton tradeBotWizard = new JButton("", loadIcon("images/icons/obj16/bot-16.png"));
        tradeBotWizard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            	 AlphaBotDialog dlg = new AlphaBotDialog(SwingUtilities.getWindowAncestor(ResourceTreeGen.this), new AlphaBotInput());
                 dlg.setSize(400, 300);
            	 dlg.setVisible(true);
            

               
            }
        });
        
        toolBar.add(tradeBotWizard);
			
        toolBar.add(collapseButton);


        JButton mockActionButton = new JButton("", loadIcon("images/icons/obj/chart-16.png"));
        mockActionButton.addActionListener(e -> showMockContextMenu(mockActionButton));
        toolBar.add(mockActionButton);

        toolBar.add(Box.createHorizontalGlue()); // Right-align the buttons
        add(toolBar, BorderLayout.NORTH);

        // Initialize login dialog
   //     showLoginDialog();
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
        JPanel twsPanel = new TwsConnectionPanel();

        int result = JOptionPane.showConfirmDialog(this, twsPanel, "Tws Connection", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            TwsConnectionPanel panel = (TwsConnectionPanel) twsPanel;
            String name = panel.getNameField();
            String host = panel.getHostField();
            int port = panel.getPortField();
            int clientId = panel.getClientIdField();

            // Simulate connection to TWS API (replace with actual connection code)
            if (connectToTwsApi(host, port, clientId)) {
                TwsNode twsNode = new TwsNode(name);
                ObservableTreeNode twsTreeNode = new ObservableTreeNode(twsNode);
                twsTreeNode.setTreeModel(treeModel); // Set tree model
                addMockAccounts(twsTreeNode);
                treeNode.add(twsTreeNode);
                treeModel.reload(rootNode);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to connect to TWS API", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAccountTradeBotDialog(TwsAccountNode parentNode, ObservableTreeNode treeNode) {
        JPanel botPanel = new AccountTradeBotPanel();

        int result = JOptionPane.showConfirmDialog(this, botPanel, "Account TradeBot", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            AccountTradeBotPanel panel = (AccountTradeBotPanel) botPanel;
            String botName = panel.getBotName();
            String botType = panel.getBotType();

            TradeBotNode tradeBotNode = new TradeBotNode(botName, botType);
            ObservableTreeNode tradeBotTreeNode = new ObservableTreeNode(tradeBotNode);
            tradeBotTreeNode.setTreeModel(treeModel); // Set tree model
            treeNode.add(tradeBotTreeNode);
            treeModel.reload(rootNode);
        }
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

    private void showTradeBotViewer(TradeBotNode tradeBotNode) {
        
    }

	/*
	 * private void showLoginDialog() { JDialog loginDialog = new JDialog((Frame)
	 * SwingUtilities.getWindowAncestor(this), "Login", true);
	 * loginDialog.setUndecorated(true); loginDialog.setLayout(new BorderLayout());
	 * 
	 * JPanel leftPanel = new JPanel(); leftPanel.setBackground(Color.DARK_GRAY);
	 * JLabel logoLabel = new JLabel("LOGO"); logoLabel.setForeground(Color.WHITE);
	 * leftPanel.add(logoLabel);
	 * 
	 * JPanel rightPanel = new JPanel(new GridBagLayout());
	 * rightPanel.setBackground(Color.LIGHT_GRAY); GridBagConstraints gbc = new
	 * GridBagConstraints(); gbc.insets = new Insets(5, 5, 5, 5); gbc.gridx = 0;
	 * gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
	 * 
	 * JLabel serverLabel = new JLabel("Street Server:");
	 * rightPanel.add(serverLabel, gbc);
	 * 
	 * gbc.gridx = 1; JTextField serverField = new JTextField(15);
	 * rightPanel.add(serverField, gbc);
	 * 
	 * gbc.gridx = 0; gbc.gridy = 1; JLabel userLabel = new JLabel("Street User:");
	 * rightPanel.add(userLabel, gbc);
	 * 
	 * gbc.gridx = 1; JTextField userField = new JTextField(15);
	 * rightPanel.add(userField, gbc);
	 * 
	 * gbc.gridx = 0; gbc.gridy = 2; JLabel passwordLabel = new
	 * JLabel("Street Password:"); rightPanel.add(passwordLabel, gbc);
	 * 
	 * gbc.gridx = 1; JPasswordField passwordField = new JPasswordField(15);
	 * rightPanel.add(passwordField, gbc);
	 * 
	 * gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor =
	 * GridBagConstraints.CENTER; JButton loginButton = new JButton("Login");
	 * rightPanel.add(loginButton, gbc);
	 * 
	 * loginDialog.add(leftPanel, BorderLayout.WEST); loginDialog.add(rightPanel,
	 * BorderLayout.CENTER);
	 * 
	 * loginButton.addActionListener(e -> { // Authentication logic here
	 * loginDialog.dispose(); });
	 * 
	 * loginDialog.pack(); loginDialog.setLocationRelativeTo(this);
	 * loginDialog.setVisible(true); }
	 */

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
