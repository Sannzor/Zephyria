package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import com.zephyria.mode.KitMapMode;
import com.zephyria.mode.UHCFMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandBase.SubCommand {

    private final KitMapMode kitMapMode;
    private final UHCFMode uhcfMode;

    public GameModeCommand(KitMapMode kitMapMode, UHCFMode uhcfMode) {
        this.kitMapMode = kitMapMode;
        this.uhcfMode = uhcfMode;
    }

    @Override
    public String getPermission() {
        return "zephyria.command.gamemode"; // Permissie voor gamemode commando's
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /gamemode <kitmap|uhcf>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "kitmap":
                kitMapMode.enableKitMapMode(player);
                break;
            case "uhcf":
                uhcfMode.enableUHCFMode(player);
                break;
            default:
                player.sendMessage("Onbekend game mode.");
                break;
        }
        return true;
    }
}
