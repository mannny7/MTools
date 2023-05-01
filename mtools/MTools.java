package com.manny.mtools;

import com.manny.mtools.commands.HealCommand;
import com.manny.mtools.commands.db.freeze.IsFrozen;
import com.manny.mtools.commands.db.xp.GetXP;
import com.manny.mtools.commands.db.xp.SetXP;
import com.manny.mtools.commands.db.freeze.Freeze;
import com.manny.mtools.commands.db.mute.IsMuted;
import com.manny.mtools.commands.db.mute.Mute;
import com.manny.mtools.playerdb.ConnectionListener;
import com.manny.mtools.playerdb.CustomPlayer;
import com.manny.mtools.playerdb.Database;
import com.manny.mtools.playerdb.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Objects;

public final class MTools extends JavaPlugin implements Listener {

    // Variables used for any reference of the plugin
    public static final String pluginName = "[MTools]";


    private Database database;
    private PlayerManager playerManager;

    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }

    @Override
    public void onEnable() {

        // Data base


        database = new Database();

        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (database.isConnected()) {
            ConsoleLog.log("Connected to database");
        } else {
            ConsoleLog.error("Failed to connect to database");
        }

        playerManager = new PlayerManager();

        /*
         End of database
        */

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);


        // Commands
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
        getCommand("getxp").setExecutor(new GetXP());
        getCommand("setxp").setExecutor(new SetXP());
        getCommand("ismuted").setExecutor(new IsMuted());
        getCommand("mute").setExecutor(new Mute());
        getCommand("freeze").setExecutor(new Freeze());
        getCommand("isfrozen").setExecutor(new IsFrozen());

    }


    @Override
    public void onDisable() { database.disconnect(); }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        try {
            CustomPlayer player = new CustomPlayer(this, e.getPlayer().getUniqueId());
            // If the player is frozen, cancel the event
            if (player.getFrozen() == 1) {
                e.setCancelled(true);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }




    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        // Handle mutes
        try {
            CustomPlayer player = new CustomPlayer(this, event.getPlayer().getUniqueId());
            // The database uses tinyint instead of booleans
            if (player.getMuted() == 1) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You are currently muted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

}