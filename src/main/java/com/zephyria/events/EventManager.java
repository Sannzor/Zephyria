package com.zephyria.events;

import com.zephyria.Zephyria;
import com.zephyria.mode.UHCFMode;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EventManager {

    private final Zephyria plugin;
    private final long koTHInterval;
    private final long conquestInterval;
    private final long glowstoneInterval;
    private final long oreMountainInterval;
    private final long purgeInterval;
    private  UHCFMode uhcfMode;
    private  KoTHEvent koTHEvent;

    public EventManager(Zephyria plugin) {
        this.plugin = plugin;
        this.koTHInterval = plugin.getConfig().getLong("events.KoTH.interval") * 20L * 60L;
        this.conquestInterval = plugin.getConfig().getLong("events.Conquest.interval") * 20L * 60L;
        this.glowstoneInterval = plugin.getConfig().getLong("events.GlowstoneMountain.interval") * 20L * 60L;
        this.oreMountainInterval = plugin.getConfig().getLong("events.OreMountain.interval") * 20L * 60L;
        this.purgeInterval = plugin.getConfig().getLong("events.Purge.interval") * 20L * 60L;
    }

    // Algemene methode om een event te starten
    private void startEvent(String eventName, String broadcastMessage, long interval) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(broadcastMessage);
            }
        }.runTaskTimer(plugin, 0L, interval);
    }

    public void startKoTH() {
        if (uhcfMode.isActive()) {
            koTHEvent.startKoTH(); // Start KoTH met boosters als uHCF actief is
            Bukkit.broadcastMessage("KoTH event is gestart met boosters!");
        } else {
            koTHEvent.startKoTH(); // Start KoTH zonder boosters als uHCF niet actief is
            Bukkit.broadcastMessage("KoTH event is gestart zonder boosters!");
        }
    }

    public void startConquest() {
        startEvent("Conquest", "Conquest event is gestart! Vecht om de controlepunten!", conquestInterval);
    }

    public void startPurge() {
        startEvent("Purge", "Purge event is gestart! Verdedig jezelf tegen andere spelers!", purgeInterval);
    }

    public void startGlowstoneMountain() {
        startEvent("GlowstoneMountain", "Glowstone Mountain event is gestart! Verzamel de glowstone!", glowstoneInterval);
    }

    public void startOreMountain() {
        startEvent("OreMountain", "Ore Mountain event is gestart! Verzamel de ores!", oreMountainInterval);
    }

    // Nieuwe stopKoTH methode
    public void stopKoTH() {
        koTHEvent.stopKoTH(); // Stop KoTH event logica in KoTHEvent klasse
        Bukkit.broadcastMessage("KoTH event gestopt!");
    }

    public void startEvents() {
        if (plugin.getConfig().getBoolean("events.KoTH.enabled")) {
            startKoTH();
        }
        if (plugin.getConfig().getBoolean("events.Conquest.enabled")) {
            startConquest();
        }
        if (plugin.getConfig().getBoolean("events.GlowstoneMountain.enabled")) {
            startGlowstoneMountain();
        }
        if (plugin.getConfig().getBoolean("events.OreMountain.enabled")) {
            startOreMountain();
        }
        if (plugin.getConfig().getBoolean("events.Purge.enabled")) {
            startPurge();
        }
    }



}
