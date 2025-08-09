package com.zephyria.commands.cmds;

import com.zephyria.Zephyria;
import com.zephyria.commands.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZephyriaCommand implements CommandBase.SubCommand {

    private final Zephyria plugin;

    public ZephyriaCommand(Zephyria plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getPermission() {
        return "zephyria.command.zephyria"; // Permissie voor Zephyria commando
    }

    // Aangepaste execute-methode die een Player accepteert
    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /zephyria <reload|stats>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                if (player.hasPermission(getPermission())) {
                    plugin.reloadConfig();
                    player.sendMessage("Zephyria configuratie herladen!");
                } else {
                    player.sendMessage("Je hebt geen toestemming om deze actie uit te voeren.");
                }
                break;
            case "stats":
                player.sendMessage("Plugin Statistieken: ...");
                break;
            default:
                player.sendMessage("Onbekend commando.");
                break;
        }
        return true;
    }
}
