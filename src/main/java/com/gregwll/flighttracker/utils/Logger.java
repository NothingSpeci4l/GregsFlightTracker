package com.gregwll.flighttracker.utils;

public class Logger {

    String prefix;

    public Logger(String prefix) {
        this.prefix = prefix;
    }

    public void sendLog(String mes) {
        System.out.println(prefix + " " + mes);
    }

    public void sendError(String content) {
        System.err.println(prefix + ": " + content);
    }
}
