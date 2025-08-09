package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LFFCommand implements CommandBase.SubCommand {

    @Override
    public String getPermission() {
        return "zephyria.command.lff"; // Permissie voor LFF commando's
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /lff <faction-name>");
            return false;
        }

        String factionName = args[0];
        player.sendMessage("Je bent nu op zoek naar de faction " + factionName + ".");
        return true;
    }
}
