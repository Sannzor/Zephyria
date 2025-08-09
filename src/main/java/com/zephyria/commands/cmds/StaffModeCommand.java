package com.zephyria.commands.cmds;

import com.zephyria.commands.CommandBase;
import com.zephyria.staff.StaffMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffModeCommand implements CommandBase.SubCommand {

    private final StaffMode staffMode;

    public StaffModeCommand(StaffMode staffMode) {
        this.staffMode = staffMode;
    }

    @Override
    public String getPermission() {
        return "zephyria.command.staffmode"; // Permissie voor staff mode commando
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Gebruik: /staffmode <enable|disable>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "enable":
                staffMode.enableStaffMode(player);
                break;
            case "disable":
                staffMode.disableStaffMode(player);
                break;
            default:
                player.sendMessage("Onbekend staffmode subcommand.");
                break;
        }

        return true;
    }
}
