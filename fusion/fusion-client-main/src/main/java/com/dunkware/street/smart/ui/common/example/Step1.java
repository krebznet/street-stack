package com.dunkware.street.smart.ui.common.example;

import com.dunkware.street.smart.ui.common.WizardStep;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Step1 implements WizardStep {

    private VBox view;

    public Step1() {
        view = new VBox(new Label("This is Step 1"));
    }

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public String getStepTitle() {
        return "Step 1: Introduction";
    }

    @Override
    public boolean canProceed() {
        return true; // Step can always proceed in this simple example
    }

    @Override
    public void onEnter() {
        // Logic to execute when entering this step
    }

    @Override
    public void onExit() {
        // Logic to execute when exiting this step
    }
}