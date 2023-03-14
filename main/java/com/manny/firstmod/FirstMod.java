package com.manny.firstmod;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;

public final class FirstMod extends JavaPlugin implements Listener {

    public static final String pluginFolder = ".\\mannyplugin\\";
    public static final String pluginName = "mannyplugin";

    @Override
    public void onEnable() {


        CreateFile.createFile("frozen.txt");

        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnfreezeCommand());
    }


    // CraftPlayer{name=Mannnny}
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        ArrayList frozenPlayers = ReadFile.readFile( "frozen.txt");


        if (frozenPlayers.size() >= 1) {
            for (Object frozenPlayer : frozenPlayers) {
                String currentFrozenPlayer = "CraftPlayer{name=" + frozenPlayer + "}";
                if (currentFrozenPlayer.equals(e.getPlayer().toString())) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "You are frozen!");
                }
            }
        }
    }
}
