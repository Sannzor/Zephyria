package com.zephyria.staff;

import org.bukkit.entity.Player;

public class StaffMode {

    public void enableStaffMode(Player player) {
        player.sendMessage("Staff-Mode is ingeschakeld! Je bent nu verborgen.");
        // Verberg de speler voor anderen
        player.setInvisible(true);
    }

    public void disableStaffMode(Player player) {
        player.sendMessage("Staff-Mode is uitgeschakeld! Je bent weer zichtbaar.");
        // Maak de speler zichtbaar
        player.setInvisible(false);
    }
}
