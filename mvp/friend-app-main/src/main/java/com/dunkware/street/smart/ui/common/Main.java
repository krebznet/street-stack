package com.dunkware.street.smart.ui.common;


import com.dunkware.street.smart.ui.common.example.Step1;
import com.dunkware.street.smart.ui.common.example.Step2;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Wizard wizard = new Wizard(primaryStage);

        // Add wizard steps
        wizard.addStep(new Step1());
        wizard.addStep(new Step2());

        // Start the wizard
        wizard.start();

        primaryStage.setTitle("Wizard Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}