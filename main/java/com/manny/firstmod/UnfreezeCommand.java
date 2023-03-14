package com.manny.firstmod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.manny.firstmod.FirstMod.pluginFolder;

public class UnfreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 1) {
            ArrayList frozenPlayers = ReadFile.readFile("frozen.txt");


            if (frozenPlayers.size() >= 1) {
                if (frozenPlayers.contains(strings[0])) {
                    int location = frozenPlayers.indexOf(strings[0]);
                    frozenPlayers.remove(location);

                    ReturnMessage.send(commandSender, "Successfully unfroze " + strings[0]);

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
            } else {
                ReturnMessage.error(commandSender, "freeze.txt is empty");
            }

        } else {
            ReturnMessage.error(commandSender, "Unfreeze takes exactly 1 parameter");
        }

        return false;
    }
}

