package com.zephyria.donator;

import com.zephyria.Zephyria;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DonatorPerksManager {

    private final Zephyria plugin;

    public DonatorPerksManager(Zephyria plugin) {
        this.plugin = plugin;
    }

    public void applyPerks(Player player) {
        if (player.hasPermission("zephyria.donator")) {
            if (plugin.getConfig().getBoolean("donator-perks.speed")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            }
            if (plugin.getConfig().getBoolean("donator-perks.fire-resistance")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));
            }
            if (plugin.getConfig().getBoolean("donator-perks.invisibility")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            }
        }
    }
}
