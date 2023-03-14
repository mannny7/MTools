package com.manny.mtools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;


public class FrozenCommand implements CommandExecutor {

    // Print out a list of all frozen players
    // Only really useful for debugging

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        ArrayList frozenPlayers = ReadFile.readFile("frozen.txt");

        // Make sure the list isn't empty
        if (frozenPlayers.size() >= 1) {
            ReturnMessage.send(commandSender, "Frozen players: " + String.join(", ", frozenPlayers));
        } else {
            ReturnMessage.send(commandSender, "There are no frozen players");
        }


        return false;
    }
}
