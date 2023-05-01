package com.manny.mtools.commands.db.freeze;

import com.manny.mtools.MTools;
import com.manny.mtools.ReturnMessage;
import com.manny.mtools.playerdb.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Freeze implements CommandExecutor {

    MTools main = (MTools) Bukkit.getPluginManager().getPlugin("MTools");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length != 1) {
            ReturnMessage.argumentError(commandSender, 1);
            return false;
        }

        Player recipient = Bukkit.getPlayerExact(strings[0]);

        if (recipient == null) {
            ReturnMessage.error(commandSender, "Invalid player");
            return false;
        }

        try {
            CustomPlayer player = new CustomPlayer(main, recipient.getUniqueId());

            int currentFreeze = player.getFrozen();

            // If the player is already frozen we should unfreeze them
            // Otherwise we should freeze them

            if (currentFreeze == 0) {
                player.setFrozen(true);
                ReturnMessage.send(commandSender, "Successfully frozen " + strings[0]);
            } else {
                player.setFrozen(false);
                ReturnMessage.send(commandSender, "Successfully unfrozen " + strings[0]);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
