package com.manny.firstmod;

import static com.manny.firstmod.FirstMod.pluginName;

public class ConsoleLog {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void log(String output) {
        System.out.println(ANSI_GREEN + pluginName + " >>> " + output + ANSI_RESET);
    }

    public static void error(String output) {
        System.out.println(ANSI_RED + "[ERROR] " + pluginName + " >>> " + output + ANSI_RESET);
    }



}
