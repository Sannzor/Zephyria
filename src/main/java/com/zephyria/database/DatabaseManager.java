package com.zephyria.database;

public abstract class DatabaseManager {
    public abstract void connect();
    public abstract void disconnect();
    public abstract void saveData(String key, String value);
    public abstract String getData(String key);
    public abstract void deleteData(String key);
}
