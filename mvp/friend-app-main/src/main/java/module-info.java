module sample {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.graphics;
    requires javafx.web;

    requires MaterialFX;
    requires org.slf4j;
  //  requires javax.inject;

    exports com.dunkware.street.smart;
    exports com.dunkware.street.smart.controller;
    exports com.dunkware.street.smart.ui.common;
    exports com.dunkware.street.smart.ui.common.example;
    opens com.dunkware.street.smart to  javafx.fxml;

    opens com.dunkware.street.smart.controller to javafx.fxml;
    exports com.dunkware.street.smart.broker;
    opens com.dunkware.street.smart.broker to javafx.fxml;
    exports com.dunkware.street.smart.broker.controllers;
    opens com.dunkware.street.smart.broker.controllers to javafx.fxml;
}