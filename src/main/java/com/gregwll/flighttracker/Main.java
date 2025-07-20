package com.gregwll.flighttracker;

import com.gregwll.flighttracker.utils.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.xml.stream.Location;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main extends Application {

    private static Stage stage2;
    private static Scene mainscene;
    private static Scene settingscene;

    private static Logger logger = new Logger("GregsFlightTracker");

    @Override
    public void start(Stage stage) {
        //fonc
        files();


        // WebView avec la carte
        WebView webView = new WebView();
        var url = getClass().getResource("/mapdark.html");
        if (url == null) {
            System.err.println("map.html introuvable !");
            System.exit(1);
        }
        webView.getEngine().load(url.toExternalForm());

        // Barre latérale gauche
        VBox sideBar = new VBox(10);
        sideBar.setAlignment(Pos.CENTER);
        sideBar.setPadding(new Insets(20));
        sideBar.setBackground(new Background(new BackgroundFill(
                Color.rgb(0,0,0, 0.50), CornerRadii.EMPTY, Insets.EMPTY)));

        Button btn1 = new Button("Zoom +");
        Button btn2 = new Button("Zoom -");
        Region spacer = new Region(); // espace flexible

        Button settingsBtn = new Button("Settings");
        settingsBtn.getStyleClass().add("button-custom");
        settingsBtn.setPrefWidth(150);


        settingsBtn.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
               var url = getClass().getResource("/settings.fxml");
                if (url == null) {
                    System.err.println("settings.fxml introuvable !");
                    System.exit(1);
                }
                Parent root;
                try {
                    root = FXMLLoader.load(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root, mainscene.getWidth(), mainscene.getHeight());
                var urlcss = getClass().getResource("/style.css");
                if (urlcss == null) {
                    System.err.println("stylecss introuvable !");
                    System.exit(1);
                }
                scene.getStylesheets().add(urlcss.toExternalForm());
                settingscene = scene;
                stage.setScene(scene);
                stage.show();
            }
        });


        VBox.setVgrow(spacer, Priority.ALWAYS);

        sideBar.getChildren().addAll(btn1, btn2, spacer, settingsBtn);
        sideBar.setPrefWidth(250);

        StackPane root = new StackPane(webView);
        HBox overlay = new HBox(sideBar);
        overlay.setPickOnBounds(false);
        root.getChildren().add(overlay);
        StackPane.setAlignment(overlay, javafx.geometry.Pos.CENTER_LEFT);

        Scene scene = new Scene(root, 1000, 700);
        var urlcss = getClass().getResource("/style.css");
        if (urlcss == null) {
            System.err.println("stylecss introuvable !");
            System.exit(1);
        }
        scene.getStylesheets().add(urlcss.toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Gregs Flight Tracker - V-dev0.1");
        stage.show();

        stage2 = stage;
        mainscene = scene;

    }

    public static void main(String[] args) {
        launch();

    }

    public static void show() {
        stage2.setScene(mainscene);
    }

    private static void files() {
        File roamingDir = new File(System.getProperty("user.home"), "GregsFlightTracker");
        if(!roamingDir.exists()) {
            roamingDir.mkdirs();
            System.out.println("JETPIZEJ");
        }


    }


}
