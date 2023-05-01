package com.manny.mtools.playerdb;

import com.manny.mtools.MTools;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;


public class ConnectionListener implements Listener {

    private final MTools main;

    public ConnectionListener (MTools main) {
        this.main = main;
    }



    // When a player joins check if they have an entry in the database
    // Then either create one or try to load their data
    // If there are any errors they are kicked from the server

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();


        try {
            CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());

            main.getPlayerManager().addCustomPlayer(player.getUniqueId(), playerData);


        } catch (SQLException ex) {
            player.kickPlayer("Your data could not be loaded");
            ex.printStackTrace();
        }

    }

    // When the player leaves remove them from the hashmap

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        main.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());

    }

}
