package com.zephyria.events;

import com.zephyria.mode.UHCFMode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class KoTHEvent {

    private final UHCFMode uhcfMode;
    private final Location koTHLocation;
    private final long baseKoTHTime = 600; // Basis tijd van KoTH in seconden (bijv. 10 minuten)
    private final Map<Player, Long> playerTimeInKoTH = new HashMap<>();
    private final Map<Player, Double> playerBoosters = new HashMap<>();
    private BukkitRunnable koTHTimer;

    public KoTHEvent(UHCFMode uhcfMode, Location koTHLocation) {
        this.uhcfMode = uhcfMode;
        this.koTHLocation = koTHLocation;
    }

    // Start het KoTH event
    public void startKoTH() {
        Bukkit.broadcastMessage("KoTH event is gestart! Kom naar de KoTH locatie!");

        new BukkitRunnable() {
            @Override
            public void run() {
                // We starten de KoTH-timer en volgen spelers op de KoTH-locatie
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (isInKoTHZone(player)) {
                        // Speler is in de KoTH zone, verhoog hun tijd in KoTH
                        playerTimeInKoTH.put(player, playerTimeInKoTH.getOrDefault(player, 0L) + 1);

                        // Bereken de booster alleen als uHCF Mode actief is
                        double booster = 0;
                        if (uhcfMode.isActive()) {
                            booster = calculateBooster(player); // Alleen boost als uHCF mode actief is
                        }

                        // Bereken de verkorte tijd voor deze speler
                        long adjustedTime = (long) (baseKoTHTime - (baseKoTHTime * booster));
                        playerBoosters.put(player, booster);

                        player.sendMessage("Je hebt een booster van " + booster * 100 + "%! Je resterende tijd op KoTH is: " + adjustedTime + " seconden.");
                    }
                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Zephyria"), 0L, 20L); // Elke seconde
    }

    // Controleer of de speler in de KoTH zone staat
    private boolean isInKoTHZone(Player player) {
        if (player.getLocation().distance(koTHLocation) <= 10) { // Stel een bereik van 10 blokken in
            return true;
        }
        return false;
    }

    // Bereken de booster voor een speler op basis van tijd en teamleden in de zone
    private double calculateBooster(Player player) {
        long timeInKoTH = playerTimeInKoTH.getOrDefault(player, 0L);
        int teamMembersInZone = getTeamMembersInKoTHZone(player);

        // De formule voor de booster is: hoe langer de speler op KoTH staat en hoe meer teamleden er zijn, hoe hoger de booster
        double baseBooster = timeInKoTH / 600.0; // Na 10 minuten in KoTH krijgt een speler 100% boost
        double teamBooster = teamMembersInZone * 0.05; // Elke teamgenoot verhoogt de booster met 5%

        return Math.min(baseBooster + teamBooster, 1.0); // Zorg ervoor dat de booster niet boven 100% uitkomt
    }

    // Stop het KoTH event
    public void stopKoTH() {
        if (koTHTimer != null) {
            koTHTimer.cancel(); // Stop de KoTH timer
            Bukkit.broadcastMessage("KoTH event is gestopt.");
        }
    }

    // Tel het aantal teamleden van de speler die zich in de KoTH zone bevinden
    private int getTeamMembersInKoTHZone(Player player) {
        int count = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (isInKoTHZone(p) && p.getDisplayName().equals(player.getDisplayName())) {
                count++;
            }
        }
        return count;
    }
}
