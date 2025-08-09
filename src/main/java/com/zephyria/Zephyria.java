package com.zephyria;

import com.zephyria.commands.CommandBase;
import com.zephyria.commands.cmds.*;
import com.zephyria.mode.KitMapMode;
import com.zephyria.mode.UHCFMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.zephyria.database.DatabaseManager;
import com.zephyria.database.MongoDBHandler;
import com.zephyria.database.MySQLHandler;
import com.zephyria.events.EventManager;
import com.zephyria.factions.FactionManager;
import com.zephyria.abilities.AbilityManager;

public class Zephyria extends JavaPlugin {

    private DatabaseManager databaseManager;
    private EventManager eventManager;
    private FactionManager factionManager;
    private AbilityManager abilityManager;

    @Override
    public void onEnable() {

        CommandBase commandBase = new CommandBase() {
            @Override
            public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                return super.onCommand(sender, cmd, label, args);
            }
        };

        // Laad configuraties
        saveDefaultConfig();
        saveResource("events.yml", false);
        saveResource("factions.yml", false);
        saveResource("abilities.yml", false);
        saveResource("database.yml", false);

        // Database instellen op basis van configuratie
        String databaseType = getConfig().getString("general.database-type").toLowerCase();
        if (databaseType.equals("mongodb")) {
            databaseManager = new MongoDBHandler();
        } else if (databaseType.equals("mysql")) {
            databaseManager = new MySQLHandler();
        }

        // Verbind met de database
        databaseManager.connect();

        // Instantieer andere managers
        eventManager = new EventManager(this);
        factionManager = new FactionManager();
        abilityManager = new AbilityManager(this);

        // Instantie van de modes
        KitMapMode kitMapMode = new KitMapMode();
        UHCFMode uhcfMode = new UHCFMode(this);

        // Start events
        eventManager.startEvents();

        // Registreer de subcommando's
        commandBase.registerSubCommand("zephyria", new ZephyriaCommand(this));
        commandBase.registerSubCommand("ability", new AbilityCommand(new AbilityManager(this)));
        commandBase.registerSubCommand("reclaim", new DailyReclaimCommand());
        commandBase.registerSubCommand("event", new EventCommand());
        commandBase.registerSubCommand("f", new FactionCommand(factionManager));
        commandBase.registerSubCommand("lff", new LFFCommand());
        commandBase.registerSubCommand("report", new ReportCommand());
        commandBase.registerSubCommand("kit", new KitCommand());
        commandBase.registerSubCommand("gamemode", new GameModeCommand(kitMapMode, uhcfMode));
        // Registreer het faction commando
        commandBase.registerSubCommand("f", new FactionCommand(factionManager));
        commandBase.registerSubCommand("faction", new FactionCommand(factionManager));
        commandBase.registerSubCommand("team", new FactionCommand(factionManager));
        commandBase.registerSubCommand("t", new FactionCommand(factionManager));


        // Registreer de CommandBase als de CommandExecutor voor het hoofdcommando
        getCommand("zephyria").setExecutor(commandBase);
        getCommand("gamemode").setExecutor(commandBase);
        // Registreer de CommandBase als de CommandExecutor voor het hoofdcommando
        getCommand("f").setExecutor(commandBase);
        getCommand("faction").setExecutor(commandBase);


        getLogger().info("Zephyria plugin ingeschakeld!");
    }


    @Override
    public void onDisable() {
        // Verbreek de database verbinding
        databaseManager.disconnect();
        getLogger().info("Zephyria plugin uitgeschakeld.");
    }
}
