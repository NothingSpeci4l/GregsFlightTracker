package com.gregwll.flighttracker.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SimbriefUtils {

    public static String getFlightNumber(String username){
        try {
            String json = SimbriefAPI.fetchFlightData(username);
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            JsonObject general = obj.getAsJsonObject("general");
            return general.get("flight_number").getAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserId(String username){
        try {
            String json = SimbriefAPI.fetchFlightData(username);
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            JsonObject general = obj.getAsJsonObject("params");
            return general.get("user_id").getAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
