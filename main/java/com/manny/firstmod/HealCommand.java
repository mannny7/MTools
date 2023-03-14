package com.manny.firstmod;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            player.setHealth(20);
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.YELLOW + "Your health has been restored!");

        } else {
            System.out.println("Only a player can run this command!");
        }



        return false;
    }
}
