package com.zephyria.mode;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitMapMode {

    // Enable Kit Map Mode
    public void enableKitMapMode(Player player) {
        player.sendMessage("Kit-Map Mode is ingeschakeld!");
        // Geef de speler de standaard kit
        giveDefaultKit(player);
        // Teleporteer de speler naar de spawn
        teleportToSpawn(player);
    }

    // Disable Kit Map Mode
    public void disableKitMapMode(Player player) {
        player.sendMessage("Kit-Map Mode is uitgeschakeld!");
        // Verwijder de items van de speler (optioneel)
        clearPlayerInventory(player);
    }

    // Geef de standaard kit aan de speler
    private void giveDefaultKit(Player player) {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack food = new ItemStack(Material.COOKED_BEEF, 16);

        player.getInventory().addItem(sword, armor, food);
    }

    // Teleporteer de speler naar de spawn
    private void teleportToSpawn(Player player) {
        double x = player.getWorld().getConfig().getDouble("spawn-coordinates.x");
        double y = player.getWorld().getConfig().getDouble("spawn-coordinates.y");
        double z = player.getWorld().getConfig().getDouble("spawn-coordinates.z");

        player.teleport(new Location(player.getWorld(), x, y, z));
    }

    // Verwijder de inventory van de speler
    private void clearPlayerInventory(Player player) {
        player.getInventory().clear();
    }
}
