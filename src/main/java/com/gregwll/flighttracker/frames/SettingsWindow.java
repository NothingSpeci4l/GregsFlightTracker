package com.gregwll.flighttracker.frames;

import com.gregwll.flighttracker.Main;
import com.gregwll.flighttracker.api.SimbriefUtils;
import com.gregwll.flighttracker.controller.SettingsController;
import com.gregwll.flighttracker.files.FileManager;
import com.gregwll.flighttracker.files.FilesUtils;
import com.gregwll.flighttracker.files.objects.Settings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.net.URL;

public class SettingsWindow {

    static SettingsWindow instance = new SettingsWindow();

    public void showSettings(URL urlfxml, URL urlcss, Scene mainScene, Scene settingscene, Stage stage) {

        Settings settings = Main.getInstance().getSettingsSerializationManager().deserialize(FilesUtils.loadContent(FileManager.getSettingsFile()));
        String simbriefId = settings.getSimbriefId();
        Boolean darkTheme = settings.getDark();


        Parent root;
        FXMLLoader loader = new FXMLLoader(urlfxml);
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SettingsController controller = loader.getController();
        controller.getDarkLightSwitch().setSelected(darkTheme);
        if(simbriefId != "") {
            controller.getSimbriefUsernameField().setText(simbriefId);

            //label
            try {
                controller.getSimbriefid().setText(SimbriefUtils.getUserId(simbriefId));
                controller.getSimbriefid().setTextFill(Paint.valueOf("GREEN"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Scene scene = new Scene(root, mainScene.getWidth(), mainScene.getHeight());
        scene.getStylesheets().add(urlcss.toExternalForm());
        settingscene = scene;
        stage.setScene(scene);
        stage.show();
    }

    public static SettingsWindow getInstance() {
        return instance;
    }
}
