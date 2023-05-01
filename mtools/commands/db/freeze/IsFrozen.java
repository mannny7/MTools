package com.manny.mtools.commands.db.freeze;

import com.manny.mtools.MTools;
import com.manny.mtools.ReturnMessage;
import com.manny.mtools.playerdb.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class IsFrozen implements CommandExecutor {

    MTools main = (MTools) Bukkit.getPluginManager().getPlugin("MTools");
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, java.lang.String s, java.lang.String[] strings) {

        if (strings.length != 1) {
            ReturnMessage.argumentError(commandSender, 1);
            return false;
        }
        // Find the player based of the IGN passed
        Player recipient = Bukkit.getPlayerExact(strings[0]);

        try {
            CustomPlayer player = new CustomPlayer(main, recipient.getUniqueId());

            int currentFreeze = player.getFrozen();

            // The database uses tinyints instead of booleans, so 0 is false and 1 is true

            if (currentFreeze == 0) {
                ReturnMessage.send(commandSender, strings[0] + " is not frozen");
            } else {
                ReturnMessage.send(commandSender, strings[0] + " is frozen");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
