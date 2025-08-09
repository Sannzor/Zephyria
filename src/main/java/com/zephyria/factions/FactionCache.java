package com.zephyria.factions;

import java.util.HashMap;
import java.util.Map;

public class FactionCache {

    private final Map<String, Faction> factionCache;

    public FactionCache() {
        factionCache = new HashMap<>();
    }

    public void addFactionToCache(String factionName, Faction faction) {
        factionCache.put(factionName, faction);
    }

    public Faction getFactionFromCache(String factionName) {
        return factionCache.get(factionName);
    }

    public void removeFactionFromCache(String factionName) {
        factionCache.remove(factionName);
    }

    public boolean isFactionInCache(String factionName) {
        return factionCache.containsKey(factionName);
    }
}
