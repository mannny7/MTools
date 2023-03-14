package com.manny.firstmod;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.manny.firstmod.FirstMod.pluginFolder;

public class ReadFile {
    public static ArrayList readFile(String fileName) {
        try {
            ArrayList<String> list = new ArrayList<>();
            File file = new File(pluginFolder + fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                list.add(reader.nextLine());

            return list;
            }
        } catch (FileNotFoundException e) {
            ConsoleLog.error("File " + fileName + "not found!");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
