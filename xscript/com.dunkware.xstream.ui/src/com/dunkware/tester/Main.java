package com.dunkware.tester;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;

public class Main {
	private static Table table;

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("SWT Application");
        shell.setLayout(new FillLayout());

        // Login Dialog
        login(shell);

        // Main Menu
        Menu menuBar = new Menu(shell, SWT.BAR);
        createFileMenu(menuBar);
        createActionMenu(menuBar);
        shell.setMenuBar(menuBar);

        // Tab Folder
        CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
        tabFolder.setSimple(false);
        tabFolder.setTabPosition(SWT.BOTTOM);
        createTabs(tabFolder);
        
        CTabItem tbtmTrades = new CTabItem(tabFolder, SWT.NONE);
        tbtmTrades.setText("Trades");
        
        TableViewer tableViewer = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
        table = tableViewer.getTable();
        table.setHeaderVisible(true);
        tbtmTrades.setControl(table);
        
        TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnSymbol = tableViewerColumn.getColumn();
        tblclmnSymbol.setWidth(100);
        tblclmnSymbol.setText("Symbol");
        
        TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnSize = tableViewerColumn_1.getColumn();
        tblclmnSize.setWidth(100);
        tblclmnSize.setText("Size");
        
        CTabItem tbtmOrders = new CTabItem(tabFolder, SWT.NONE);
        tbtmOrders.setText("Orders");

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    private static void login(Shell parent) {
        // Implement your login logic here
    }

    private static void createFileMenu(Menu menuBar) {
        MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuHeader.setText("&File");

        Menu fileMenu = new Menu(menuBar);
        fileMenuHeader.setMenu(fileMenu);

        MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("&Quit");
        fileExitItem.addListener(SWT.Selection, event -> System.exit(0));
    }

    private static void createActionMenu(Menu menuBar) {
        MenuItem actionMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        actionMenuHeader.setText("&Action");

        Menu actionMenu = new Menu(menuBar);
        actionMenuHeader.setMenu(actionMenu);

        MenuItem startBot = new MenuItem(actionMenu, SWT.PUSH);
        startBot.setText("Start Bot");
        // Add listener

        MenuItem killBot = new MenuItem(actionMenu, SWT.PUSH);
        killBot.setText("Kill Bot");
        // Add listener

        MenuItem stopBot = new MenuItem(actionMenu, SWT.PUSH);
        stopBot.setText("Stop Bot");
        // Add listener
    }

    private static void createTabs(CTabFolder folder) {
        String[] titles = {"Trades", "Orders", "Metrics", "Events"};
        for (String title : titles) {
            CTabItem tabItem = new CTabItem(folder, SWT.NONE);
            tabItem.setText(title);
            Composite composite = new Composite(folder, SWT.NONE);
            composite.setLayout(new FillLayout());
            tabItem.setControl(composite);
        }
    }
}
