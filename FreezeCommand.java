package com.manny.mtools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.manny.mtools.MTools.pluginFolder;


public class FreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        // Only one parameter pls
        if (strings.length != 1) {
            ReturnMessage.error(commandSender, "Freeze takes exactly 1 parameter!");
            return false;
        }


        ArrayList frozenPlayers = ReadFile.readFile("frozen.txt");

        try {

            FileWriter writer = new FileWriter(pluginFolder + "frozen.txt");

            // Add the new player to freeze to the list
            frozenPlayers.add(strings[0]);


            // Write the complete list to the file
            for (Object frozenPlayer: frozenPlayers) writer.write(frozenPlayer + "\n");

            writer.close();

            ReturnMessage.send(commandSender, "Added " + strings[0] + " to frozen players");

        } catch (IOException e) {
            ConsoleLog.error("An error occurred freezing player" + strings[0]);
            e.printStackTrace();
        }

        Player recipient = Bukkit.getPlayerExact(strings[0]);

        for (int i = 0; i < 3; i++){
            assert recipient != null;
            recipient.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have been frozen!");
        }
        return false;
    }
}
