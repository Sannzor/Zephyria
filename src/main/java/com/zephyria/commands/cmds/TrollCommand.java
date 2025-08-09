package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import org.bukkit.entity.Player;

public class TrollCommand implements CommandBase.SubCommand {

    @Override
    public String getPermission() {
        return "zephyria.command.troll"; // Permissie die vereist is om het commando uit te voeren
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Gebruik: /troll <teleport|invert>");
            return false;
        }

        String subCommand = args[1].toLowerCase();

        switch (subCommand) {
            case "teleport":
                teleportRandom(player);
                break;
            case "invert":
                invertControls(player);
                break;
            default:
                player.sendMessage("Onbekend troll subcommando.");
                return false;
        }
        return true;
    }

    private void teleportRandom(Player player) {
        double x = (Math.random() * 2000) - 1000;
        double z = (Math.random() * 2000) - 1000;
        player.teleport(player.getWorld().getHighestBlockAt((int) x, (int) z).getLocation());
        player.sendMessage("Je bent geteleporteerd naar een random locatie!");
    }

    private void invertControls(Player player) {
        // Hier kun je logica toevoegen om de controls van de speler om te keren.
        player.sendMessage("Je besturing is omgekeerd!");
    }
}
