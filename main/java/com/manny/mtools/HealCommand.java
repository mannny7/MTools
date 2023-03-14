package com.manny.mtools;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    // Handy lil command

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            player.setHealth(20);
            player.setFoodLevel(20);
            ReturnMessage.send(commandSender, ChatColor.YELLOW + "You have been healed");

        } else {
            ConsoleLog.error("Only a player can run this command!");
        }



        return false;
    }
}
