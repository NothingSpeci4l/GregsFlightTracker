package com.gregwll.flighttracker.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregwll.flighttracker.files.objects.Settings;

public class SettingsSerializationManager {

    private Gson gson;

    public SettingsSerializationManager() {
        this.gson = createGsonInstance();
    }

    private Gson createGsonInstance() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

    public String serialize(Settings settings) {
        return this.gson.toJson(settings);
    }

    public Settings deserialize(String json) {
        return this.gson.fromJson(json, Settings.class);
    }
}
