package com.dunkware.street.street.smart;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.dunkware.street.street.smart.ui.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;

@SpringBootApplication
public class StreetSmartApplication {

	public static void main(String[] args) {
		//SpringApplication.run(StreetSmartApplication.class, args);
		SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
           
           // setVisible(true);
        });

		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				StreetSmartApplication.class).headless(false).run(args);

		MainFrame appFrame = context.getBean(MainFrame.class);
		appFrame.setVisible(true);
	}

}
