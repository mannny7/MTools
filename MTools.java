package com.manny.mtools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public final class MTools extends JavaPlugin implements Listener {

    // Variables used for any reference of the plugin
    public static final String pluginFolder = ".\\MTools\\";
    public static final String pluginName = "[MTools]";

    @Override
    public void onEnable() {
        // Create a folder for all the text files
        new File(".\\MTools").mkdirs();

        // Create files for saving player igns
        CreateFile.createFile("frozen.txt");

        Bukkit.getPluginManager().registerEvents(this, this);

        // Commands
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeCommand());
        Objects.requireNonNull(getCommand("unfreeze")).setExecutor(new UnfreezeCommand());
        Objects.requireNonNull(getCommand("frozen")).setExecutor(new FrozenCommand());

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {


        // If a player joins that is frozen tell them that they still are

        ArrayList<String> frozenPlayers = ReadFile.readFile("frozen.txt");

        for (Object frozenPlayer : frozenPlayers) {
            String currentFrozenPlayer = "CraftPlayer{name=" + frozenPlayer + "}";
            if (currentFrozenPlayer.equals(e.getPlayer().toString())) {
                e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You are frozen!");
            }
        }

    }


    // Work out a better event to use than player move as it causes lag
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        /*
        --> Check frozen players
        */

        ArrayList<String> frozenPlayers = ReadFile.readFile("frozen.txt");

        if (frozenPlayers.size() >= 1) {
            for (Object frozenPlayer : frozenPlayers) {
                String currentFrozenPlayer = "CraftPlayer{name=" + frozenPlayer + "}";
                if (currentFrozenPlayer.equals(e.getPlayer().toString())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}