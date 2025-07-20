package com.gregwll.flighttracker.files;

import com.gregwll.flighttracker.Main;
import com.gregwll.flighttracker.files.objects.Settings;
import com.gregwll.flighttracker.utils.Logger;

import java.io.File;

public class FileManager {
    static Logger logger = Main.getLogger();
    static Main instance = Main.getInstance();

    // Main folder
    static File roamingDir = new File(System.getProperty("user.home"), "GregsFlightTracker");

    // Folders
    static File settingsDir = new File(roamingDir, "settings");

    //Jsons
    static File settingsFile = new File(settingsDir, "settings.json");

    public static File getSettingsFile() {
        return settingsFile;
    }

    public static void onJoin() {
        if(!roamingDir.exists()) {
            roamingDir.mkdirs();
            logger.sendLog("Roaming dir just been created");
        }
        if(!settingsDir.exists()) {
            settingsDir.mkdirs();
            logger.sendLog("Settings dir just been created");
        }
        if(!settingsFile.exists()) {
            Settings settings = new Settings(true, null);

            final SettingsSerializationManager settingsSerializationManager = instance.getSettingsSerializationManager();
            final String jsonSettings = settingsSerializationManager.serialize(settings);

            FilesUtils.save(settingsFile, jsonSettings);

            logger.sendLog("Settings FILE just been created");
        }



    }


}
