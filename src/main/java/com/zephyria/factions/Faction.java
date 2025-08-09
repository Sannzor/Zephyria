package com.zephyria.factions;

import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;

public class Faction {

    private String name;
    private final Set<Player> members;
    private Player leader;
    private boolean friendlyFireEnabled;
    private final String coords;

    public Faction(String name) {
        this.name = name;
        this.members = new HashSet<>();
        this.friendlyFireEnabled = false;
        this.coords = "X: 100, Y: 64, Z: 100"; // Placeholder coördinaten
    }

    public String getName() {
        return name;
    }

    public Set<Player> getMembers() {
        return members;
    }

    public void addMember(Player player) {
        members.add(player);
    }

    public void removeMember(Player player) {
        members.remove(player);
    }

    public boolean isFriendlyFireEnabled() {
        return friendlyFireEnabled;
    }

    public void toggleFriendlyFire() {
        friendlyFireEnabled = !friendlyFireEnabled;
    }

    public String getCoords() {
        return coords;
    }

    public Player getLeader() {
        return leader;
    }

    public void setLeader(Player player) {
        this.leader = player;
    }

    public void rename(String newName) {
        this.name = newName;
    }
}
