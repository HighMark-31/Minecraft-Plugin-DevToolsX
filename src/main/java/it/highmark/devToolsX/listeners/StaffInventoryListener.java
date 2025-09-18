// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.listeners;

import it.highmark.devToolsX.utils.StaffInventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class StaffInventoryListener implements Listener {
    
    private final Plugin plugin;
    
    public StaffInventoryListener(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getPlayer();
        String title = event.getView().getTitle();
        
        if (title.startsWith("§6Staff Inventory - ")) {
            StaffInventoryManager manager = StaffInventoryManager.getInstance(plugin);
            manager.saveStaffInventory(player);
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        StaffInventoryManager manager = StaffInventoryManager.getInstance(plugin);
        manager.saveStaffInventory(player);
    }
}