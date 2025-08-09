package com.zephyria.deathban;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class DeathbanManager {

    private final Map<Player, Long> deathbanTimers = new HashMap<>();
    private final long deathbanTimeInSeconds = 120; // 2 minuten deathban

    public void applyDeathban(Player player) {
        long deathbanEndTime = System.currentTimeMillis() + (deathbanTimeInSeconds * 1000);
        deathbanTimers.put(player, deathbanEndTime);
    }

    public boolean isDeathbanned(Player player) {
        Long deathbanEndTime = deathbanTimers.get(player);
        if (deathbanEndTime != null && System.currentTimeMillis() < deathbanEndTime) {
            return true;
        }
        return false;
    }

    public void removeDeathban(Player player) {
        deathbanTimers.remove(player);
    }

    public long getRemainingTime(Player player) {
        Long deathbanEndTime = deathbanTimers.get(player);
        if (deathbanEndTime != null) {
            return (deathbanEndTime - System.currentTimeMillis()) / 1000; // tijd in seconden
        }
        return 0;
    }
}
