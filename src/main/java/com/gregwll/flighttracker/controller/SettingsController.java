package com.gregwll.flighttracker.controller;

import com.gregwll.flighttracker.Main;
import javafx.event.ActionEvent;

public class SettingsController {

    public void saveClose(ActionEvent e) {
        Main.show();
    }

    public void darkMode(ActionEvent e) {
        System.out.println("dark");
    }

    public void lightMode(ActionEvent e) {
        System.out.println("light");
    }
}

// ON CLICK POUR LE DARK MODE