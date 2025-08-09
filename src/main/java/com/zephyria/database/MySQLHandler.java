package com.zephyria.database;

import com.zephyria.Zephyria;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLHandler extends DatabaseManager {

    private Connection connection;
    private Zephyria plugin;

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zephyria", "user", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData(String key, String value) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO data (key, value) VALUES (?, ?)");
                    ps.setString(1, key);
                    ps.setString(2, value);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    @Override
    public String getData(String key) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT value FROM data WHERE key = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteData(String key) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM data WHERE key = ?");
                    ps.setString(1, key);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    public void saveFactionData(String factionName, String data) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO factions (factionName, data) VALUES (?, ?)");
            ps.setString(1, factionName);
            ps.setString(2, data);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFactionData(String factionName) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT data FROM factions WHERE factionName = ?");
            ps.setString(1, factionName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveAbilityData(String playerName, String abilityName) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO abilities (playerName, abilityName) VALUES (?, ?)");
            ps.setString(1, playerName);
            ps.setString(2, abilityName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAbilityData(String playerName) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT abilityName FROM abilities WHERE playerName = ?");
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("abilityName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
