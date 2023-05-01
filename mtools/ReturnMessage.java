package com.manny.mtools;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.manny.mtools.MTools.pluginName;

public class ReturnMessage {

    // Use to return a message to a player or to the console
    public static void send(CommandSender sender, String message) {

        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + pluginName + " >>> " + message);
        } else {
            ConsoleLog.log(message);
        }

    }


    // Use to log error in commands to the console or player
    public static void error(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + pluginName + " >>> " + message);
        } else {
            ConsoleLog.error(message);
        }
    }

    public static void argumentError(CommandSender sender, int desiredAmount) {
        ReturnMessage.error(sender, String.format("This command takes exactly %d parameters", desiredAmount));

    }
}
