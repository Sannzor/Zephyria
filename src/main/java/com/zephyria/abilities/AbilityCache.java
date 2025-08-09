package com.zephyria.abilities;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AbilityCache {

    private final Map<Player, String> activeAbilities = new HashMap<>();

    // Zet een ability als geactiveerd voor een speler
    public void setActiveAbility(Player player, String abilityName) {
        activeAbilities.put(player, abilityName);
    }

    // Haal de actieve ability van een speler op
    public String getActiveAbility(Player player) {
        return activeAbilities.getOrDefault(player, "Geen actieve ability");
    }

    // Verwijder de actieve ability voor een speler
    public void removeActiveAbility(Player player) {
        activeAbilities.remove(player);
    }

    // Controleer of een speler een specifieke ability heeft geactiveerd
    public boolean hasActiveAbility(Player player, String abilityName) {
        return activeAbilities.getOrDefault(player, "").equalsIgnoreCase(abilityName);
    }
}
