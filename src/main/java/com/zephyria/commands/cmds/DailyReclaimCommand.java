package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DailyReclaimCommand implements CommandBase.SubCommand {

    @Override
    public String getPermission() {
        return "zephyria.command.reclaim"; // Permissie voor daily reclaim
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (hasClaimedToday(player)) {
            player.sendMessage("Je hebt je daily reclaim al opgehaald.");
        } else {
            giveDailyReward(player);
            player.sendMessage("Je hebt je daily reclaim beloning ontvangen!");
        }
        return true;
    }

    private boolean hasClaimedToday(Player player) {
        // Voor deze demo laten we het altijd toe
        return false;
    }

    private void giveDailyReward(Player player) {
        // Voorbeeld: geef de speler een random item als beloning
        player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
    }
}
