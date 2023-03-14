package com.manny.mtools;

import java.io.File;
import java.io.IOException;

import static com.manny.mtools.MTools.pluginFolder;

public class CreateFile {

    public static void createFile(String fileName){
        try {
            File newFile = new File(pluginFolder + fileName);

            // Check if the file already exists
            if (newFile.createNewFile()) {
                ConsoleLog.log("File created: " + newFile.getName());
            } else {
                ConsoleLog.log("File " + fileName + " has already been created");
            }
        } catch (IOException e) {
            ConsoleLog.error("An error occurred creating file: " + fileName);
            e.printStackTrace();
        }

    }
}
