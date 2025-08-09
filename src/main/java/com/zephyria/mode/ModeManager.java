package com.zephyria.mode;

import com.zephyria.Zephyria;

public class ModeManager {
    private final Zephyria plugin;

    public ModeManager(Zephyria plugin) {
        this.plugin = plugin;
    }

    public boolean isKitMapMode() {
        return plugin.getConfig().getBoolean("mode.kit-map");
    }

    public boolean isUHCFMode() {
        return plugin.getConfig().getBoolean("mode.uHCF");
    }
}
