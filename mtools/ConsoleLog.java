package com.manny.mtools;


import static com.manny.mtools.MTools.pluginName;

public class ConsoleLog {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    // Use for logging any information
    public static void log(String output) {
        System.out.println(ANSI_GREEN + pluginName + " >>> " + output + ANSI_RESET);
    }

    // Use for logging any errors only to the console

    public static void error(String output) {
        System.out.println(ANSI_RED + "[ERROR] " + pluginName + " >>> " + output + ANSI_RESET);
    }



}
