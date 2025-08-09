package com.zephyria.factions;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class FactionManager {

    private final Map<String, Faction> factions = new HashMap<>();
    private final Map<Player, Faction> playerFactions = new HashMap<>();

    // Maak een nieuwe faction
    public void createFaction(Player player, String factionName) {
        if (factions.containsKey(factionName)) {
            player.sendMessage("Faction met deze naam bestaat al.");
            return;
        }
        Faction faction = new Faction(factionName);
        factions.put(factionName, faction);
        playerFactions.put(player, faction);
        faction.addMember(player);
        player.sendMessage("Faction " + factionName + " is aangemaakt.");
    }

    // Disband een faction
    public void disbandFaction(Player player) {
        Faction faction = playerFactions.get(player);
        if (faction == null) {
            player.sendMessage("Je maakt geen deel uit van een faction.");
            return;
        }
        if (!faction.getLeader().equals(player)) {
            player.sendMessage("Je bent geen leider van deze faction.");
            return;
        }
        factions.remove(faction.getName());
        player.sendMessage("Faction " + faction.getName() + " is ontbonden.");
    }

    // Rename een faction
    public void renameFaction(Player player, String newName) {
        Faction faction = playerFactions.get(player);
        if (faction == null) {
            player.sendMessage("Je maakt geen deel uit van een faction.");
            return;
        }
        faction.rename(newName);
        player.sendMessage("Faction is hernoemd naar " + newName);
    }

    // Toon faction informatie
    public void showFactionInfo(Player player) {
        Faction faction = playerFactions.get(player);
        if (faction == null) {
            player.sendMessage("Je maakt geen deel uit van een faction.");
            return;
        }
        player.sendMessage("Faction Info: " + faction.getName());
        player.sendMessage("Leader: " + faction.getLeader().getName());
        player.sendMessage("Leden: " + faction.getMembers().size());
        player.sendMessage("Coördinaten: " + faction.getCoords());
    }

    // Toon faction top
    public void showFactionTop(Player player) {
        player.sendMessage("Faction Top: ");
        // Plaats hier de ranking logica voor top factions
    }

    // Voeg speler toe aan faction
    public void invitePlayer(Player player, Player target) {
        Faction faction = playerFactions.get(player);
        if (faction == null) {
            player.sendMessage("Je maakt geen deel uit van een faction.");
            return;
        }
        faction.addMember(target);
        target.sendMessage(player.getName() + " heeft je uitgenodigd voor faction " + faction.getName());
        player.sendMessage(target.getName() + " is uitgenodigd voor faction " + faction.getName());
    }

    // Verwijder een speler uit de faction
    public void kickPlayer(Player player, Player target) {
        Faction faction = playerFactions.get(player);
        if (faction == null) {
            player.sendMessage("Je maakt geen deel uit van een faction.");
            return;
        }
        if (faction.getLeader().equals(player)) {
            faction.removeMember(target);
            target.sendMessage("Je bent gekicked uit faction " + faction.getName());
            player.sendMessage("Je hebt " + target.getName() + " gekicked uit faction.");
        } else {
            player.sendMessage("Je bent geen leider van deze faction.");
        }
    }

    // Set de leader van de faction
    public void setLeader(Player player, Player newLeader) {
        Faction faction = playerFactions.get(player);
        if (faction == null) {
            player.sendMessage("Je maakt geen deel uit van een faction.");
            return;
        }
        if (faction.getLeader().equals(player)) {
            faction.setLeader(newLeader);
            player.sendMessage("Je hebt de leiderschap overgedragen aan " + newLeader.getName());
        } else {
            player.sendMessage("Je bent geen leider van deze faction.");
        }
    }

    // Verkrijg de faction van een speler
    public Faction getFactionOfPlayer(Player player) {
        return playerFactions.get(player);
    }

    // Verkrijg faction door naam
    public Faction getFactionByName(String factionName) {
        return factions.get(factionName);
    }
}
