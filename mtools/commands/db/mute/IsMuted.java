package com.manny.mtools.commands.db.mute;

import com.manny.mtools.MTools;
import com.manny.mtools.ReturnMessage;
import com.manny.mtools.playerdb.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class IsMuted implements CommandExecutor {

    MTools main = (MTools) Bukkit.getPluginManager().getPlugin("MTools");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        // Check for arguments
        if (strings.length != 1) {
            ReturnMessage.argumentError(commandSender, 1);
        }

        // Create the player from the name supplied in the args
        Player recipient = Bukkit.getPlayerExact(strings[0]);

        try {
            // Cast to a custom player using the UUID
            CustomPlayer player = new CustomPlayer(main, recipient.getUniqueId());

            int currentMute = player.getMuted();
            // The SQL database uses TinyInts instead of Booleans, so we have to convert it
            if (currentMute == 1) {
                ReturnMessage.send(commandSender, strings[0] + " is muted");
            } else {
                ReturnMessage.send(commandSender, strings[0] + " is not muted");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;


    }
}
