package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import com.zephyria.events.EventManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventCommand implements CommandBase.SubCommand {

    private final EventManager eventManager;

    public EventCommand(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public String getPermission() {
        return "zephyria.command.event"; // Permissie voor event commando's
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /event <start|stop|list>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                // Start KoTH event
                eventManager.startKoTH(); // Start KoTH met of zonder boosters, afhankelijk van uHCF-modus
                break;

            case "stop":
                // Stop KoTH event
                eventManager.stopKoTH();
                break;

            case "list":
                // Toon actieve evenementen (zoals KoTH, Conquest, etc.)
                player.sendMessage("Actieve evenementen: KoTH, Conquest, etc.");
                break;

            default:
                player.sendMessage("Onbekend event commando.");
                break;
        }

        return true;
    }
}
