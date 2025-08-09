package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandBase.SubCommand {

    @Override
    public String getPermission() {
        return "zephyria.command.report"; // Permissie voor het rapporteren van spelers
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /report <speler>");
            return false;
        }

        String reportedPlayer = args[0];
        // Voeg hier logica toe om de speler te rapporteren
        player.sendMessage("Je hebt " + reportedPlayer + " gerapporteerd.");
        return true;
    }
}
