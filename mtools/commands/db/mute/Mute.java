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

public class Mute  implements CommandExecutor {

    // Get the instance of MTools
    MTools main = (MTools) Bukkit.getPluginManager().getPlugin("MTools");
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        // Check arguments
        if (strings.length != 1) {
            ReturnMessage.argumentError(commandSender, 1);
            return false;
        }

        // Create the player from the name given in args
        Player recipient = Bukkit.getPlayerExact(strings[0]);

        if (recipient == null) {
            ReturnMessage.error(commandSender, "Invalid player");
            return false;
        }

        try {
            // Cast to the CustomPlayer class from the UUID
            CustomPlayer player = new CustomPlayer(main, recipient.getUniqueId());

            int currentMute = player.getMuted();

            // Convert from TinyInt used in the database to a boolean
            if (currentMute == 0) {
                player.setMuted(true);
                ReturnMessage.send(commandSender, "Successfully muted " + strings[0]);
            } else {
                player.setMuted(false);
                ReturnMessage.send(commandSender, "Successfully unmuted " + strings[0]);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
