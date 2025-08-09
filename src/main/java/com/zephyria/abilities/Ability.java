package com.zephyria.abilities;

import org.bukkit.entity.Player;

public class Ability {

    private final String name;
    private final String description;

    public Ability(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Methode om een ability te activeren (basisimplementatie)
    public void activate(Player player) {
        player.sendMessage("De ability " + name + " is geactiveerd! " + description);
        // Voeg hier specifieke effecten toe (bijv. teleportatie, krachtverhoging, etc.)
    }
}
