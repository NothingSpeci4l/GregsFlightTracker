package com.gregwll.flighttracker.controller;

import com.gregwll.flighttracker.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.ToggleSwitch;

public class SettingsController {

    @FXML private ToggleSwitch darkLightSwitch;

    public void saveClose(ActionEvent e) {
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