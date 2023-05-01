package com.manny.mtools.playerdb;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private final HashMap<UUID, CustomPlayer> customPlayers = new HashMap<>();

    public CustomPlayer getCustomPlayer(UUID uuid) {
        return customPlayers.get(uuid);
    }


    // Add a new player to the hashmap
    // Called when a player joins the server
    public void addCustomPlayer (UUID uuid, CustomPlayer player) {
        customPlayers.put(uuid, player);

    }

    // Remove a player from the hashmap
    // Called when a player leaves the server
    public void removeCustomPlayer (UUID uuid) {
        customPlayers.remove(uuid);
    }


}
