package com.gregwll.flighttracker.files.objects;

public class Settings {

    String simbriefId;
    Boolean isDark;

    public Settings(Boolean isDark, String simbriefId) {
        this.isDark = isDark;
        this.simbriefId = simbriefId;
    }

    public String getSimbriefId() {
        return simbriefId;
    }

    public void setSimbriefId(String simbriefId) {
        this.simbriefId = simbriefId;
    }

    public Boolean getDark() {
        return isDark;
    }

    public void setDark(Boolean dark) {
        isDark = dark;
    }
}
