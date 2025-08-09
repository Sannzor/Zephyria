package com.zephyria.commands.cmds;

import com.zephyria.abilities.AbilityManager;
import com.zephyria.commands.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AbilityCommand implements CommandBase.SubCommand {

    private final AbilityManager abilityManager;

    public AbilityCommand(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
    }

    @Override
    public String getPermission() {
        return "zephyria.command.ability"; // Permissie voor ability commando's
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /ability <activate|list>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "activate":
                if (args.length < 2) {
                    player.sendMessage("Gebruik: /ability activate <ability>");
                    return false;
                }
                abilityManager.activateAbility(player, args[1]);
                break;
            case "list":
                abilityManager.listAbilities(player);
                break;
            default:
                player.sendMessage("Onbekende ability subcommand.");
                break;
        }
        return true;
    }
}
