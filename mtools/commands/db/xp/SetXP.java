package com.manny.mtools.commands.db.xp;

import com.manny.mtools.MTools;
import com.manny.mtools.ReturnMessage;
import com.manny.mtools.playerdb.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;


public class SetXP implements CommandExecutor {

    MTools main = (MTools) Bukkit.getPluginManager().getPlugin("MTools");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length != 2) {
            ReturnMessage.argumentError(commandSender, 2);
            return false;
        }

        Player recipient = Bukkit.getPlayerExact(strings[0]);

        // Argument 1 is the player
        // Argument 2 is the xp to set

        try {
            CustomPlayer player = new CustomPlayer(main, recipient.getUniqueId());
            player.setXP(Integer.parseInt(strings[1]));
            ReturnMessage.send(commandSender, "Updated " + strings[0] +"'s xp to "+ strings[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;

    }
}
