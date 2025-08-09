package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import com.zephyria.factions.FactionManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionCommand implements CommandBase.SubCommand {

    private final FactionManager factionManager;

    public FactionCommand(FactionManager factionManager) {
        this.factionManager = factionManager;
    }

    @Override
    public String getPermission() {
        return "zephyria.command.faction"; // Permissie voor faction commando's
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /f <subcommand>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                if (args.length > 1) {
                    factionManager.createFaction(player, args[1]);
                } else {
                    player.sendMessage("Gebruik: /f create <factionnaam>");
                }
                break;

            case "disband":
                factionManager.disbandFaction(player);
                break;

            case "rename":
                if (args.length > 1) {
                    factionManager.renameFaction(player, args[1]);
                } else {
                    player.sendMessage("Gebruik: /f rename <nieuwe naam>");
                }
                break;

            case "invite":
                if (args.length > 1) {
                    Player target = player.getServer().getPlayer(args[1]);
                    if (target != null) {
                        factionManager.invitePlayer(player, target);
                    } else {
                        player.sendMessage("Speler niet gevonden.");
                    }
                } else {
                    player.sendMessage("Gebruik: /f invite <speler>");
                }
                break;

            case "kick":
                if (args.length > 1) {
                    Player target = player.getServer().getPlayer(args[1]);
                    if (target != null) {
                        factionManager.kickPlayer(player, target);
                    } else {
                        player.sendMessage("Speler niet gevonden.");
                    }
                } else {
                    player.sendMessage("Gebruik: /f kick <speler>");
                }
                break;

            case "leader":
                if (args.length > 1) {
                    Player newLeader = player.getServer().getPlayer(args[1]);
                    if (newLeader != null) {
                        factionManager.setLeader(player, newLeader);
                    } else {
                        player.sendMessage("Speler niet gevonden.");
                    }
                } else {
                    player.sendMessage("Gebruik: /f leader <speler>");
                }
                break;

            default:
                player.sendMessage("Onbekend faction commando.");
                break;
        }

        return true;
    }
}
