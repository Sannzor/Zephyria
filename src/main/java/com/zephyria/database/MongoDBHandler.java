package com.zephyria.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.zephyria.Zephyria;
import org.bson.Document;
import org.bukkit.scheduler.BukkitRunnable;

public class MongoDBHandler extends DatabaseManager {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private final Zephyria plugin;

    public MongoDBHandler(Zephyria plugin) {
        this.plugin = plugin;
    }

    @Override
    public void connect() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("zephyria");
    }

    @Override
    public void disconnect() {
        mongoClient.close();
    }

    @Override
    public void saveData(String key, String value) {
        new BukkitRunnable() {
            @Override
            public void run() {
                database.getCollection("data").insertOne(new Document("key", key).append("value", value));
            }
        }.runTaskAsynchronously(plugin); // Asynchroon uitvoeren
    }

    @Override
    public String getData(String key) {
        final String[] result = new String[1];
        new BukkitRunnable() {
            @Override
            public void run() {
                Document doc = database.getCollection("data").find(new Document("key", key)).first();
                if (doc != null) {
                    result[0] = doc.getString("value");
                }
            }
        }.runTaskAsynchronously(plugin);
        return result[0];
    }

    public void saveFactionData(String factionName, String data) {
        new BukkitRunnable() {
            @Override
            public void run() {
                database.getCollection("factions").insertOne(new Document("factionName", factionName).append("data", data));
            }
        }.runTaskAsynchronously(plugin);
    }

    public String getFactionData(String factionName) {
        final String[] result = new String[1];
        new BukkitRunnable() {
            @Override
            public void run() {
                Document doc = database.getCollection("factions").find(new Document("factionName", factionName)).first();
                if (doc != null) {
                    result[0] = doc.getString("data");
                }
            }
        }.runTaskAsynchronously(plugin);
        return result[0];
    }

    public void saveAbilityData(String playerName, String abilityName) {
        new BukkitRunnable() {
            @Override
            public void run() {
                database.getCollection("abilities").insertOne(new Document("playerName", playerName).append("abilityName", abilityName));
            }
        }.runTaskAsynchronously(plugin);
    }

    public String getAbilityData(String playerName) {
        final String[] result = new String[1];
        new BukkitRunnable() {
            @Override
            public void run() {
                Document doc = database.getCollection("abilities").find(new Document("playerName", playerName)).first();
                if (doc != null) {
                    result[0] = doc.getString("abilityName");
                }
            }
        }.runTaskAsynchronously(plugin);
        return result[0];
    }

    @Override
    public void deleteData(String key) {
        new BukkitRunnable() {
            @Override
            public void run() {
                database.getCollection("data").deleteOne(new Document("key", key));
            }
        }.runTaskAsynchronously(plugin);
    }
}
