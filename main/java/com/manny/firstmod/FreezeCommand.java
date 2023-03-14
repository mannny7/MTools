package com.manny.firstmod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.FileWriter;
import java.io.IOException;

import static com.manny.firstmod.FirstMod.pluginFolder;


public class FreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 1) {
            try {

                FileWriter writer = new FileWriter(pluginFolder + "frozen.txt");
                writer.write(strings[0]);
                writer.close();

                ReturnMessage.send(commandSender, "Added " + strings[0] + " to frozen players");

            } catch (IOException e) {
                ConsoleLog.error("An error occurred freezing player" + strings[0]);
                e.printStackTrace();
            }
        } else {
            ReturnMessage.error(commandSender, "Freeze takes exactly 1 parameter!");
        }

        return false;
    }
}
