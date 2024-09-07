package com.dunkware.street.smart;

import com.dunkware.street.smart.controller.LoginController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class MainApp extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppResource.setLanguage(Locale.ENGLISH);

            this.primaryStage = primaryStage;


        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/shell.fxml"));
            Parent root = fxmlLoader.load(); // Load the FXML file

//            LoginController loginController = fxmlLoader.getController();
  //          loginController.setApplication(this);


            Scene scene = new Scene(root, 800, 600);
            // Load the dark theme CSS
        //    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/dark-theme.css")).toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Street Smart");
            primaryStage.show();
            // Set application icons

            //     primaryStage.getIcons().add(new Image((getClass().getResourceAsStream("com.dunkware.street.client.login/images/branding/newrock16.png"))));
            //  primaryStage.getIcons().add(new Image((getClass().getResourceAsStream("/com/dunkware/street/client/login/images/branding/newrock25.png"))));
            //  primaryStage.getIcons().add(new Image((getClass().getResourceAsStream("/com/dunkware/street/client/login/images/branding/newrock32.png"))));
            //  primaryStage.getIcons().add(new Image((getClass().getResourceAsStream("/com/dunkware/street/client/login/images/branding/newrock48.png"))));
            // primaryStage.getIcons().add(new Image((getClass().getResourceAsStream("/com/dunkware/street/client/login/images/branding/newrock128.png"))));


            // Create a new stage for the modal window
        //    Stage modalStage = new Stage();

        //    modalStage.initStyle(StageStyle.UTILITY); // Remove window decorations
       //     modalStage.initModality(Modality.WINDOW_MODAL); // Make it modal
        //    modalStage.initOwner(primaryStage); // Set the owner for the modality

            //   modalStage.set
            // Create and set the scene
      //      Scene scene = new Scene(root, 662, 443); // Adjust size as needed
           // modalStage.setScene(scene);
          //  modalStage.setTitle("Login Screen");
         //   loginController.setModalStage(modalStage);
      //      modalStage.showAndWait(); // Show the modal window and wait for it to close


        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly
        }
    }

    public void showMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shell.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Dunktrade");

         //   primaryStage.showAndWait();;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
