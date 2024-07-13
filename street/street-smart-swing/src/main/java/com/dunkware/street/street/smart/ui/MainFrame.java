package com.dunkware.street.street.smart.ui;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.dunkware.street.stream.model.trade.TradeSet;
import com.dunkware.street.street.smart.services.LoginService;
import com.dunkware.street.street.smart.ui.login.LoginDialog;
import com.dunkware.street.street.smart.ui.resourcetree.ResourceTreeGen;
import com.dunkware.street.street.smart.ui.viewers.EventLogger;
import com.dunkware.street.street.smart.ui.viewers.TradeViewer;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

@Component
public class MainFrame extends JFrame {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private ApplicationContext ac; 
    
    private JDesktopPane desktopPane;
    private ResourceTreeGen resourceTreeGen;
    
    private LoginDialog loginDlg;

    public MainFrame(LoginService service, ApplicationContext ac) {
        this.loginService = service;
        this.ac = ac;
        loginService.auth();
        System.out.println(loginService.auth());
        LoginDialog dlg = new LoginDialog(this, ac);
        dlg.setVisible(true);
        
        initComponents();
    }
    
    public void initComponents() { 
        setTitle("Street Smart");
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
        resourceTreeGen = new ResourceTreeGen();
        resourceTreeGen.setPreferredSize(new Dimension(300, getHeight()));

        add(resourceTreeGen, BorderLayout.WEST);

        // Desktop pane for internal frames
        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);
        this.setVisible(false);
        // Initialize with trade bots view
        showTradeBots();
    }
    
    public void addTradeViewer(TradeSet tradeSet, EventLogger eventLogger) {
        TradeViewer viewer = new TradeViewer(tradeSet, eventLogger);
        desktopPane.add(viewer);
        viewer.setVisible(true);
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
        // mockTradeViewers();
        desktopPane.revalidate();
        desktopPane.repaint();
    }

    private void showMarketScanners() {
        desktopPane.removeAll();
        // Add mock market scanner viewers
        // mockMarketScanners();
        desktopPane.revalidate();
        desktopPane.repaint();
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
}
