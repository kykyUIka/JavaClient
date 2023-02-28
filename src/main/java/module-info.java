module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;
    requires com.dlsc.formsfx;
    requires okhttp3;
    requires com.google.gson;
    requires annotations;




    opens com.example.client to javafx.fxml;
    exports com.example.client;

    opens com.example.client.controller to javafx.fxml;
    exports com.example.client.controller;

    opens com.example.client.Entity to javafx.fxml;
    exports com.example.client.Entity;

    opens com.example.client.response to javafx.fxml;
    exports com.example.client.response;

    opens com.example.client.service to javafx.fxml;
    exports com.example.client.service;

    opens com.example.client.utils to javafx.fxml;
    exports com.example.client.utils;
}
