package com.dunkware.trade.engine.core;

import javax.swing.JPanel;
import javax.swing.JTree;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TreePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TreePanel() {
		
		JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(				tree.getExpandsSelectedPaths());

			}
		});
		add(tree);

	}

}
