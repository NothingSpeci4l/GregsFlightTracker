package com.gregwll.flighttracker;

import com.gregwll.flighttracker.api.simconnect.SystemState;
import com.gregwll.flighttracker.files.FileManager;
import com.gregwll.flighttracker.files.FilesUtils;
import com.gregwll.flighttracker.files.SettingsSerializationManager;
import com.gregwll.flighttracker.files.objects.Settings;
import com.gregwll.flighttracker.frames.SettingsWindow;
import com.gregwll.flighttracker.utils.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage2;
    private static Scene mainscene;
    private static Scene settingscene;

    SettingsSerializationManager settingsSerializationManager;

    private static Logger logger = new Logger("GregsFlightTracker");

    private static Main instance;

    //

    private static StackPane root;
    private static HBox overlay;

    @Override
    public void start(Stage stage) {
        this.settingsSerializationManager = new SettingsSerializationManager();
        instance = this;
        files();

        // WebView avec la carte
        WebView webView = new WebView();

        Settings settings = Main.getInstance().getSettingsSerializationManager().deserialize(FilesUtils.loadContent(FileManager.getSettingsFile()));
        String simbriefId = settings.getSimbriefId();
        Boolean darkTheme = settings.getDark();
        if(darkTheme) {
            var url = getClass().getResource("/mapdark.html");
            if (url == null) {
                System.err.println("map.html introuvable !");
                System.exit(1);
            }
            webView.getEngine().load(url.toExternalForm());
        } else {
            var url = getClass().getResource("/maplight.html");
            if (url == null) {
                System.err.println("map.html introuvable !");
                System.exit(1);
            }
            webView.getEngine().load(url.toExternalForm());
        }


        // Barre latérale gauche
        VBox sideBar = new VBox(10);
        sideBar.setAlignment(Pos.CENTER);
        sideBar.setPadding(new Insets(20));
        sideBar.setBackground(new Background(new BackgroundFill(
                Color.rgb(0,0,0, 0.50), CornerRadii.EMPTY, Insets.EMPTY)));

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
                var urlcss = getClass().getResource("/style.css");
                if (urlcss == null) {
                    System.err.println("stylecss introuvable !");
                    System.exit(1);
                }
                SettingsWindow.getInstance().showSettings(url, urlcss, mainscene, settingscene, stage);
            }
        });


        VBox.setVgrow(spacer, Priority.ALWAYS);

        sideBar.getChildren().addAll(spacer, settingsBtn);
        sideBar.setPrefWidth(250);

        root = new StackPane(webView);
        overlay = new HBox(sideBar);
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
        FileManager.onJoin();
    }

    public static Logger getLogger() {
        return logger;
    }

    public SettingsSerializationManager getSettingsSerializationManager() {
        return this.settingsSerializationManager;
    }

    public static Main getInstance() {
        return instance;
    }

    // themes changes

    public static void lightTheme() {
        WebView webView = new WebView();
        var urlLight = instance.getClass().getResource("/maplight.html");
        webView.getEngine().load(urlLight.toExternalForm());
        StackPane root = new StackPane(webView);
        root.getChildren().add(overlay);
        mainscene.setRoot(root);
    }

    public static void darkTheme() {
        WebView webView = new WebView();
        var urlDark = instance.getClass().getResource("/mapdark.html");
        webView.getEngine().load(urlDark.toExternalForm());
        StackPane root = new StackPane(webView);
        root.getChildren().add(overlay);
        mainscene.setRoot(root);
    }
}
