package com.dunkware.street.smart.ui.common;

import javafx.scene.Node;

public interface WizardStep {
    Node getView();           // Returns the UI component for this step
    String getStepTitle();    // Returns the title of this step
    boolean canProceed();     // Indicates if the wizard can proceed to the next step
    void onEnter();           // Called when the step is entered
    void onExit();            // Called when the step is exited
}