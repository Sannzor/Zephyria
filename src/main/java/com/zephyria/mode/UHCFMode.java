package com.zephyria.mode;

import com.zephyria.Zephyria;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class UHCFMode implements Listener {


    private boolean isActive = false;
    private final Zephyria plugin;
    private final Map<Player, Long> deathbanTimers = new HashMap<>();

    public UHCFMode(Zephyria plugin) {
        this.plugin = plugin;
    }

    // Enable uHCF Mode
    public void enableUHCFMode(Player player) {
        player.sendMessage("uHCF Mode is ingeschakeld!");
        // Extra logica zoals het doden van de speler zorgt voor deathban
        enableDeathbanForPlayer(player);
    }

    // Disable uHCF Mode
    public void disableUHCFMode(Player player) {
        player.sendMessage("uHCF Mode is uitgeschakeld!");
    }

    // Deathban logica toevoegen: Wanneer een speler sterft, kan deze na de tijd weer spelen
    private void enableDeathbanForPlayer(Player player) {
        long deathbanTime = plugin.getConfig().getLong("deathban-time");

        // Voeg de deathban-timer toe voor de speler
        deathbanTimers.put(player, System.currentTimeMillis() + (deathbanTime * 1000));

        // Start een timer om de deathban op te heffen
        new BukkitRunnable() {
            @Override
            public void run() {
                if (deathbanTimers.containsKey(player)) {
                    long timeLeft = deathbanTimers.get(player) - System.currentTimeMillis();
                    if (timeLeft <= 0) {
                        deathbanTimers.remove(player);
                        player.sendMessage("Je hebt je deathban overleefd!");
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    // Controleer of de uHCF mode actief is
    public boolean isActive() {
        return isActive;
    }

    // Event voor speler die sterft en de deathban activeren
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (deathbanTimers.containsKey(player)) {
            long deathbanTime = deathbanTimers.get(player);
            long timeLeft = deathbanTime - System.currentTimeMillis();
            if (timeLeft > 0) {
                event.setCancelled(true); // Annuleer het respawn event totdat de deathban voorbij is
                player.sendMessage("Je hebt een deathban van " + timeLeft / 1000 + " seconden.");
            }
        }
    }
}
