package com.manny.mtools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.manny.mtools.MTools.pluginFolder;


public class UnfreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        ArrayList frozenPlayers;

        // Ensure sender has given one parameter
        if (strings.length == 1) {
            frozenPlayers = ReadFile.readFile("frozen.txt");

        } else {
            ReturnMessage.error(commandSender, "Unfreeze takes exactly 1 parameter");
            return false;
        }

        // Make sure the frozen players file isn't empty
        if (frozenPlayers.size() < 1) {
            ReturnMessage.error(commandSender, "frozen.txt is empty");
            return false;
        }

        // Check if the supplied player is actually frozen
        if (frozenPlayers.contains(strings[0])) {

            // Get the index of the player in the list
            frozenPlayers.remove(strings[0]);

            ReturnMessage.send(commandSender, "Successfully unfroze " + strings[0]);


            // Re-write the file with the player removed
            try {
                FileWriter writer = new FileWriter(pluginFolder + "frozen.txt");

                for (Object frozenPlayer : frozenPlayers) {
                    writer.write(frozenPlayer + "\n");
                }
                writer.close();
            } catch (IOException e) {
                ConsoleLog.error("An error occurred in unfreezing " + strings[0]);
                e.printStackTrace();
            }
        } else {
            ReturnMessage.error(commandSender, strings[0] + " is not frozen");
        }


        return false;
    }
}

