package com.dunkware.street.street.smart.aigen;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AppFoundationGen extends JFrame {
    private JDesktopPane desktopPane;
    private JPanel sidePanel;
    private JButton toggleButton;
    private boolean isSidePanelCollapsed = false;

    public AppFoundationGen() {
        setTitle("Street Smart Alpha");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar for theming
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("View");
        JMenuItem lightThemeItem = new JMenuItem("Light Theme");
        lightThemeItem.addActionListener(e -> setTheme(new FlatLightLaf()));
        viewMenu.add(lightThemeItem);

        JMenuItem darkThemeItem = new JMenuItem("Dark Theme");
        darkThemeItem.addActionListener(e -> setTheme(new FlatDarkLaf()));
        viewMenu.add(darkThemeItem);

        menuBar.add(viewMenu);
        setJMenuBar(menuBar);

        // Sidebar for navigation
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(200, getHeight()));

        // Toggle button for collapsing and expanding the sidebar
        toggleButton = new JButton("Toggle Sidebar");
        toggleButton.addActionListener(e -> toggleSidePanel());
        sidePanel.add(toggleButton);

        // Trade Bots button with icon and text
        JButton tradeBotButton = new JButton("Trade Bots", loadIcon("icons/trade_bots_icon.png"));
        tradeBotButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        tradeBotButton.addActionListener(e -> showTradeBots());
        sidePanel.add(tradeBotButton);

        // Market Scanners button with icon and text
        JButton marketScannerButton = new JButton("Market Scanners", loadIcon("icons/market_scanners_icon.png"));
        marketScannerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        marketScannerButton.addActionListener(e -> showMarketScanners());
        sidePanel.add(marketScannerButton);

        // Button to tile internal frames with icon and text
        JButton tileButton = new JButton("Tile Windows", loadIcon("icons/tile_icon.png"));
        tileButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        tileButton.addActionListener(e -> tileInternalFrames());
        sidePanel.add(tileButton);

        add(sidePanel, BorderLayout.WEST);

        // Desktop pane for internal frames
        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);
        this.setVisible(false);
        // Show login dialog
        showLoginDialog();
    }

    private void setTheme(LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

    private void showTradeBots() {
        desktopPane.removeAll();
        mockTradeViewers();
        desktopPane.revalidate();
        desktopPane.repaint();
    }

    private void showMarketScanners() {
        desktopPane.removeAll();
        mockMarketScanners();
        desktopPane.revalidate();
        desktopPane.repaint();
    }

    private void mockTradeViewers() {
        // Create mock trade sets
        TradeSet tradeSet1 = new TradeSet();
        tradeSet1.addTrade(new Trade(UUID.randomUUID(), "AAPL", TradeState.OPEN, 150.0, 155.0, 100));
        tradeSet1.addTrade(new Trade(UUID.randomUUID(), "GOOG", TradeState.CLOSED, 2500.0, 2550.0, 50));
        tradeSet1.addTrade(new Trade(UUID.randomUUID(), "MSFT", TradeState.OPENING, 300.0, 0.0, 200));

        TradeSet tradeSet2 = new TradeSet();
        tradeSet2.addTrade(new Trade(UUID.randomUUID(), "TSLA", TradeState.OPEN, 700.0, 720.0, 150));
        tradeSet2.addTrade(new Trade(UUID.randomUUID(), "AMZN", TradeState.CLOSED, 3200.0, 3250.0, 20));
        tradeSet2.addTrade(new Trade(UUID.randomUUID(), "NFLX", TradeState.CLOSING, 500.0, 0.0, 70));

        EventLogger eventLogger = new EventLogger();

        addTradeViewer(tradeSet1, eventLogger);
        addTradeViewer(tradeSet2, eventLogger);
    }

    private void mockMarketScanners() {
        // Mock implementation for market scanners
        MarketScannerView scannerView1 = new MarketScannerView("Scanner 1");
        MarketScannerView scannerView2 = new MarketScannerView("Scanner 2");

        desktopPane.add(scannerView1);
        desktopPane.add(scannerView2);

        scannerView1.setVisible(true);
        scannerView2.setVisible(true);
    }

    public void addTradeViewer(TradeSet tradeSet, EventLogger eventLogger) {
        TradeViewer viewer = new TradeViewer(tradeSet, eventLogger);
        desktopPane.add(viewer);
        viewer.setVisible(true);
    }

    private void tileInternalFrames() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        int count = frames.length;
        if (count == 0) return;

        int rows = (int) Math.sqrt(count);
        int cols = (int) Math.ceil((double) count / rows);

        Dimension size = desktopPane.getSize();
        int w = size.width / cols;
        int h = size.height / rows;

        for (int i = 0; i < count; i++) {
            int r = i / cols;
            int c = i % cols;

            JInternalFrame frame = frames[i];
            frame.setBounds(c * w, r * h, w, h);
            frame.revalidate();
        }
    }

    private void toggleSidePanel() {
        if (isSidePanelCollapsed) {
            sidePanel.setPreferredSize(new Dimension(200, getHeight()));
        } else {
            sidePanel.setPreferredSize(new Dimension(50, getHeight()));
        }
        sidePanel.revalidate();
        sidePanel.repaint();
        isSidePanelCollapsed = !isSidePanelCollapsed;
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

    private void showLoginDialog() {
        LoginDialog dialog = new LoginDialog(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            AppFoundationGen app = new AppFoundationGen();
            app.setVisible(true);
        });
    }

    // Login Dialog Class
    class LoginDialog extends JDialog {
        private JTextField serverField;
        private JTextField userField;
        private JPasswordField passwordField;
        private JLabel errorLabel;
        private JCheckBox termsCheckBox;
        private JButton loginButton;
        private AppFoundationGen parent;

        public LoginDialog(AppFoundationGen parent) {
            super(parent, "Login", true);
            this.parent = parent;
            setUndecorated(true); // Remove window borders
            setSize(400, 350);
            setLocationRelativeTo(parent);

            // Main panel with background image and sentences
            JPanel mainPanel = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon icon = new ImageIcon("path/to/your/login_background.jpg");
                    Image img = icon.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

                    // Draw the sentences
                    g.setColor(Color.WHITE);
                    g.drawString("(c) 2024 Dunkware Inc", 10, getHeight() - 30);
                    g.drawString("User agrees to terms and conditions", 10, getHeight() - 15);
                }
            };
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Sub-panel with GridLayout for login fields and labels
            JPanel loginPanel = new JPanel(new GridLayout(5, 2, 10, 10));
            loginPanel.setOpaque(false);

            JLabel serverLabel = new JLabel("Street Server: ");
            serverField = new JTextField(20);
            JLabel userLabel = new JLabel("Street User: ");
            userField = new JTextField(20);
            JLabel passwordLabel = new JLabel("Street Password: ");
            passwordField = new JPasswordField(20);

            loginPanel.add(serverLabel);
            loginPanel.add(serverField);
            loginPanel.add(userLabel);
            loginPanel.add(userField);
            loginPanel.add(passwordLabel);
            loginPanel.add(passwordField);

            termsCheckBox = new JCheckBox("I agree to the terms and conditions");
            termsCheckBox.setOpaque(false);
            termsCheckBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginButton.setEnabled(termsCheckBox.isSelected());
                }
            });

            loginPanel.add(termsCheckBox);
            loginPanel.add(new JLabel()); // Empty label for spacing

            loginButton = new JButton("Login");
            loginButton.setEnabled(false); // Initially disabled
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    authenticate();
                }
            });

            loginPanel.add(new JLabel()); // Empty label for spacing
            loginPanel.add(loginButton);

            // Center panel to hold the login panel and error label
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setOpaque(false);
            centerPanel.add(loginPanel, BorderLayout.CENTER);

            errorLabel = new JLabel("", SwingConstants.CENTER);
            errorLabel.setForeground(Color.RED);
            centerPanel.add(errorLabel, BorderLayout.SOUTH);

            mainPanel.add(centerPanel, BorderLayout.CENTER);
            add(mainPanel);

            pack();
            setSize(400, 350);
            setLocationRelativeTo(parent);
        }

        private void authenticate() {
            String server = serverField.getText();
            String user = userField.getText();
            String password = new String(passwordField.getPassword());

            // Replace with actual authentication service call
            if (authenticateWithServer(server, user, password)) {
                dispose(); // Close the dialog on successful authentication
                parent.setVisible(true);
            } else {
                errorLabel.setText("Invalid credentials. Please try again.");
            }
        }

        // Mock authentication service method (replace with actual logic)
        private boolean authenticateWithServer(String server, String user, String password) {
            // Replace with actual authentication logic
            return "server".equals(server) && "user".equals(user) && "password".equals(password);
        }
    }

    // TradeViewer Class
    class TradeViewer extends JInternalFrame {
        private JTabbedPane tabbedPane;
        private JTable tradeTable;
        private TradeTableModel tradeTableModel;
        private JPanel summaryPanel;
        private EventLogger eventLogger;

        public TradeViewer(TradeSet tradeSet, EventLogger eventLogger) {
            super("Trade Viewer", true, true, true, true);
            this.eventLogger = eventLogger;
            setSize(600, 400);
            setLayout(new BorderLayout());

            tabbedPane = new JTabbedPane();

            tradeTableModel = new TradeTableModel(tradeSet.getTrades());
            tradeTable = new JTable(tradeTableModel);
            tabbedPane.addTab("All Trades", new JScrollPane(tradeTable));

            summaryPanel = new JPanel();
            summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
            updateSummaryPanel(tradeSet.getMetrics());

            tabbedPane.addTab("Summary", summaryPanel);

            add(tabbedPane, BorderLayout.CENTER);
        }

        private void updateSummaryPanel(ObservableBean metrics) {
            summaryPanel.removeAll();
            summaryPanel.add(new JLabel("Active Trades: " + metrics.getActiveTradeCount()));
            summaryPanel.add(new JLabel("Traded Capital: " + metrics.getTradedCapital()));
            summaryPanel.add(new JLabel("Realized Gain/Loss: " + metrics.getRealizedGainLoss()));
            summaryPanel.add(new JLabel("Unrealized Gain/Loss: " + metrics.getUnrealizedGainLoss()));
            summaryPanel.add(new JLabel("Active Order Count: " + metrics.getActiveOrderCount()));
            summaryPanel.revalidate();
            summaryPanel.repaint();
        }
    }

    // MarketScannerView Class
    class MarketScannerView extends JInternalFrame {
        public MarketScannerView(String title) {
            super(title, true, true, true, true);
            setSize(600, 400);
            setLayout(new BorderLayout());

            // Sample content for market scanner
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.add(new JLabel("Market Scanner: " + title), BorderLayout.CENTER);
            add(contentPanel);
        }
    }

    // TradeTableModel Class
    class TradeTableModel extends AbstractTableModel {
        private List<Trade> trades;
        private String[] columnNames = {"Trade ID", "Symbol", "State", "Entry Price", "Exit Price", "Volume"};

        public TradeTableModel(List<Trade> trades) {
            this.trades = trades;
        }

        @Override
        public int getRowCount() {
            return trades.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Trade trade = trades.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return trade.getId();
                case 1:
                    return trade.getSymbol();
                case 2:
                    return trade.getState();
                case 3:
                    return trade.getEntryPrice();
                case 4:
                    return trade.getExitPrice();
                case 5:
                    return trade.getVolume();
                default:
                    return null;
            }
        }

        public void setTrades(List<Trade> trades) {
            this.trades = trades;
            fireTableDataChanged();
        }
    }

    // Trade Class
    class Trade {
        private UUID id;
        private String symbol;
        private TradeState state;
        private double entryPrice;
        private double exitPrice;
        private int volume;

        public Trade(UUID id, String symbol, TradeState state, double entryPrice, double exitPrice, int volume) {
            this.id = id;
            this.symbol = symbol;
            this.state = state;
            this.entryPrice = entryPrice;
            this.exitPrice = exitPrice;
            this.volume = volume;
        }

        public UUID getId() {
            return id;
        }

        public String getSymbol() {
            return symbol;
        }

        public TradeState getState() {
            return state;
        }

        public double getEntryPrice() {
            return entryPrice;
        }

        public double getExitPrice() {
            return exitPrice;
        }

        public int getVolume() {
            return volume;
        }

        public double getTradedCapital() {
            return entryPrice * volume;
        }

        public double getRealizedGainLoss() {
            return (exitPrice - entryPrice) * volume;
        }

        public double getUnrealizedGainLoss() {
            // Assuming current price is 0 for simplicity; this should be fetched from market data
            return (0 - entryPrice) * volume;
        }

        public int getActiveOrderCount() {
            // Example: returning 1 for simplicity
            return 1;
        }
    }

    // TradeSet Class
    class TradeSet {
        private List<Trade> trades;
        private ObservableBean metrics;

        public TradeSet() {
            this.trades = new ArrayList<>();
            this.metrics = new ObservableBean();
        }

        public List<Trade> getTrades() {
            return trades;
        }

        public void addTrade(Trade trade) {
            trades.add(trade);
            updateMetrics();
        }

        public ObservableBean getMetrics() {
            return metrics;
        }

        private void updateMetrics() {
            metrics.setActiveTradeCount((int) trades.stream().filter(trade -> trade.getState() == TradeState.OPEN).count());
            metrics.setTradedCapital(trades.stream().mapToDouble(Trade::getTradedCapital).sum());
            metrics.setRealizedGainLoss(trades.stream().mapToDouble(Trade::getRealizedGainLoss).sum());
            metrics.setUnrealizedGainLoss(trades.stream().mapToDouble(Trade::getUnrealizedGainLoss).sum());
            metrics.setActiveOrderCount((int) trades.stream().filter(trade -> trade.getState() == TradeState.OPEN).count());
        }
    }

    // TradeState Enum
    enum TradeState {
        OPEN, CLOSED, OPENING, CLOSING
    }

    // ObservableBean Class
    class ObservableBean {
        private int activeTradeCount;
        private double tradedCapital;
        private double realizedGainLoss;
        private double unrealizedGainLoss;
        private int activeOrderCount;

        public int getActiveTradeCount() {
            return activeTradeCount;
        }

        public void setActiveTradeCount(int activeTradeCount) {
            this.activeTradeCount = activeTradeCount;
        }

        public double getTradedCapital() {
            return tradedCapital;
        }

        public void setTradedCapital(double tradedCapital) {
            this.tradedCapital = tradedCapital;
        }

        public double getRealizedGainLoss() {
            return realizedGainLoss;
        }

        public void setRealizedGainLoss(double realizedGainLoss) {
            this.realizedGainLoss = realizedGainLoss;
        }

        public double getUnrealizedGainLoss() {
            return unrealizedGainLoss;
        }

        public void setUnrealizedGainLoss(double unrealizedGainLoss) {
            this.unrealizedGainLoss = unrealizedGainLoss;
        }

        public int getActiveOrderCount() {
            return activeOrderCount;
        }

        public void setActiveOrderCount(int activeOrderCount) {
            this.activeOrderCount = activeOrderCount;
        }
    }

    // EventLogger Class
    class EventLogger {
        private List<TradeEvent> events = new ArrayList<>();

        public void logEvent(UUID tradeId, String eventType, String description) {
            TradeEvent event = new TradeEvent(tradeId, eventType, description, LocalDateTime.now());
            events.add(event);
        }

        public List<TradeEvent> getEventsByTradeId(UUID tradeId) {
            return events.stream()
                    .filter(event -> event.getTradeId().equals(tradeId))
                    .collect(Collectors.toList());
        }

        public List<TradeEvent> getAllEvents() {
            return new ArrayList<>(events);
        }
    }

    // TradeEvent Class
    class TradeEvent {
        private UUID tradeId;
        private String eventType;
        private String description;
        private LocalDateTime timestamp;

        public TradeEvent(UUID tradeId, String eventType, String description, LocalDateTime timestamp) {
            this.tradeId = tradeId;
            this.eventType = eventType;
            this.description = description;
            this.timestamp = timestamp;
        }

        public UUID getTradeId() {
            return tradeId;
        }

        public String getEventType() {
            return eventType;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
