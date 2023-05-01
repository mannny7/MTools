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

public class GetXP implements CommandExecutor {


    MTools main = (MTools) Bukkit.getPluginManager().getPlugin("MTools");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if (strings.length != 1) {
            ReturnMessage.argumentError(commandSender, 1);
            return false;
        }

        Player recipient = Bukkit.getPlayerExact(strings[0]);


        try {
            CustomPlayer player = new CustomPlayer(main, recipient.getUniqueId());
            ReturnMessage.send(commandSender, String.valueOf(player.getXP()));
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return false;
    }
}
