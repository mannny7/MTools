package com.manny.mtools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.manny.mtools.MTools.pluginFolder;


public class ReadFile {

    // Read a specified file and return the contents as a list

    public static ArrayList<String> readFile(String fileName) {
        ArrayList<String> list;
        try {

            Scanner s = new Scanner(new File(pluginFolder + fileName));

            list = new ArrayList<String>();

            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }

            return list;

        } catch (FileNotFoundException e) {
            ConsoleLog.error("Error reading file" + fileName);
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }
}
