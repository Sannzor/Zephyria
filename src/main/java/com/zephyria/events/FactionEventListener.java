package com.zephyria.events;

import com.zephyria.factions.FactionManager;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Player;

public class FactionEventListener implements Listener {

    private final FactionManager factionManager;

    public FactionEventListener(FactionManager factionManager) {
        this.factionManager = factionManager;
    }

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            factionManager.applyDeathban(player, 60000L); // 60 seconden deathban
        }
    }
}
