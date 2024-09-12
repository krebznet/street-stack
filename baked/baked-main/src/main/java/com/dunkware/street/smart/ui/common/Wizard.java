package com.dunkware.street.smart.ui.common;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Wizard extends BorderPane {

    private List<WizardStep> steps = new ArrayList<>();
    private int currentStepIndex = 0;
    private Label titleLabel;
    private HBox contentContainer;
    private Button backButton;
    private Button nextButton;
    private Button finishButton;

    public Wizard(Stage primaryStage) {
        titleLabel = new Label();
        contentContainer = new HBox();
        HBox.setHgrow(contentContainer, Priority.ALWAYS);
        backButton = new Button("Back");
        nextButton = new Button("Next");
        finishButton = new Button("Finish");

        ToolBar toolBar = new ToolBar(backButton, nextButton, finishButton);
        this.setTop(toolBar);
        this.setCenter(contentContainer);
        this.setBottom(titleLabel);

        backButton.setOnAction(e -> previousStep());
        nextButton.setOnAction(e -> nextStep());
        finishButton.setOnAction(e -> finish());

        primaryStage.setScene(new Scene(this, 600, 400));
    }

    public void addStep(WizardStep step) {
        steps.add(step);
    }

    public void start() {
        if (!steps.isEmpty()) {
            showStep(0);
        }
    }

    private void showStep(int index) {
        if (index < 0 || index >= steps.size()) {
            return;
        }

        WizardStep currentStep = steps.get(currentStepIndex);
        currentStep.onExit();

        currentStepIndex = index;
        WizardStep newStep = steps.get(currentStepIndex);
        newStep.onEnter();

        contentContainer.getChildren().clear();
        contentContainer.getChildren().add(newStep.getView());

        titleLabel.setText(newStep.getStepTitle());
        updateButtonStates();
    }

    private void updateButtonStates() {
        backButton.setDisable(currentStepIndex == 0);
        nextButton.setDisable(currentStepIndex == steps.size() - 1 || !steps.get(currentStepIndex).canProceed());
        finishButton.setDisable(currentStepIndex != steps.size() - 1);
    }

    private void nextStep() {
        if (currentStepIndex < steps.size() - 1) {
            showStep(currentStepIndex + 1);
        }
    }

    private void previousStep() {
        if (currentStepIndex > 0) {
            showStep(currentStepIndex - 1);
        }
    }

    private void finish() {
        System.out.println("Wizard finished!");
        // Perform any final actions and close the wizard
    }
}