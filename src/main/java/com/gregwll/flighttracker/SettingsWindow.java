package com.gregwll.flighttracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class SettingsWindow {
    private static Stage stage = new Stage();

    public static void openSettings(URL url) throws Exception {
        Parent root = FXMLLoader.load(url);
        stage.setTitle("GregsFlightTracker - Settings");
        stage.setWidth(400);
        stage.setScene(new Scene(root));
        stage.setHeight(600);
        stage.show();


    }

    public static void hideSettings() {
        stage.hide();
    }
}
