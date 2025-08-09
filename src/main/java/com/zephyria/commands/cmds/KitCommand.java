package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandBase.SubCommand {

    @Override
    public String getPermission() {
        return "zephyria.command.kit"; // Permissie voor kit commando's
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /kit <name>");
            return false;
        }

        // Logica voor het geven van kits
        player.sendMessage("Kit " + args[0] + " is succesvol gegeven.");
        return true;
    }
}
