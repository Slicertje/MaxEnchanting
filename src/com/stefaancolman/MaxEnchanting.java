package com.stefaancolman;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MaxEnchanting extends JavaPlugin {

    Logger log = Logger.getLogger("Minecraft");
    
    public void onDisable() {

    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MaxEnchanting.PrepareItemEnchantListener(), this);
    }

    private class PrepareItemEnchantListener implements Listener {

        
        @EventHandler
        public void itemInserted (PrepareItemEnchantEvent event) {
            // Determine max level enchantment in table
            int tmp = event.getEnchantmentBonus();
            if (tmp > 30) {
                tmp = 30;
            }
            tmp = 5 + (tmp >> 1) + tmp;
            
            // Make last row max level, not forgetting player level
            event.getExpLevelCostsOffered()[2] = Math.min(tmp, event.getEnchanter().getLevel());
        }

    }
}
