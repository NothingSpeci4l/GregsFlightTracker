package com.gregwll.flighttracker.controller;

import com.gregwll.flighttracker.Main;
import com.gregwll.flighttracker.files.FilesUtils;
import com.gregwll.flighttracker.files.SettingsSerializationManager;
import com.gregwll.flighttracker.files.objects.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.ToggleSwitch;

import static com.gregwll.flighttracker.files.FileManager.settingsFile;

public class SettingsController {

    @FXML private ToggleSwitch darkLightSwitch;

    public void saveClose(ActionEvent e) {
        Settings settings = new Settings(darkLightSwitch.isSelected(), null);
        final SettingsSerializationManager settingsSerializationManager = Main.getInstance().getSettingsSerializationManager();
        final String jsonSettings = settingsSerializationManager.serialize(settings);

        FilesUtils.save(settingsFile, jsonSettings);
        
        Main.show();
    }


    public void darkMode(MouseEvent e) {
        if(!darkLightSwitch.isSelected()) {
            System.out.println("pute");
        }
    }

    public ToggleSwitch getDarkLightSwitch() {
        return darkLightSwitch;
    }
}