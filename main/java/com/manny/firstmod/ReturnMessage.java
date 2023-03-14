package com.manny.firstmod;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReturnMessage {

    // Use to return a message to a player or to the console
    public static void send(CommandSender sender, String message) {

        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            ConsoleLog.log(message);
        }

    }


    // Use to log error in commands to the console
    public static void error(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + message);
        } else {
            ConsoleLog.error(message);
        }
    }

}
