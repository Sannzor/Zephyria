package com.zephyria.events;

import com.zephyria.Zephyria;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

public class ConquestEvent implements Listener {

    private final Zephyria plugin;

    public ConquestEvent(Zephyria plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        // Als het blok een controlepunt is (bijvoorbeeld een speciaal type blok)
        if (block != null && block.getType().toString().equals("STONE_PRESSURE_PLATE")) {
            // Claim het controlepunt
            // Voeg hier logica toe om het controlepunt in te nemen
            player.sendMessage("Je hebt een controlepunt geclaimd!");
        }
    }
}
