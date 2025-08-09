package com.zephyria.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandBase implements CommandExecutor {

    private final Map<String, SubCommand> subCommands = new HashMap<>();

    // Voeg subcommando’s toe
    public void registerSubCommand(String name, SubCommand command) {
        subCommands.put(name, command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sendMessage(sender, "Gebruik: /" + label + " <subcommand>");
            return false;
        }

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandName);

        if (subCommand == null) {
            sendMessage(sender, "Onbekend subcommando: " + subCommandName);
            return false;
        }

        // Controleer permissies als de sender een speler is
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!hasPermission(player, subCommand.getPermission())) {
                return false;
            }
            return subCommand.execute(player, args);
        } else {
            sendMessage(sender, "Dit commando kan alleen door een speler worden uitgevoerd.");
            return false;
        }
    }

    // Controleer permissies
    protected boolean hasPermission(Player player, String permission) {
        if (player.hasPermission(permission)) {
            return true;
        }
        player.sendMessage("Je hebt geen toestemming om dit commando uit te voeren.");
        return false;
    }

    // Stuur bericht naar de sender
    protected void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(message);
    }

    // SubCommand interface
    public interface SubCommand {
        String getPermission(); // Permissie voor het subcommando
        boolean execute(Player player, String[] args); // De uitvoeringslogica van het subcommando
    }
}
