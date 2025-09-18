// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StaffInventoryManager {
    
    private static StaffInventoryManager instance;
    private final Plugin plugin;
    private final File dataFolder;
    private final Map<UUID, Inventory> staffInventories;
    
    private StaffInventoryManager(Plugin plugin) {
        this.plugin = plugin;
        this.dataFolder = new File(plugin.getDataFolder(), "staffinventories");
        this.staffInventories = new HashMap<>();
        
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }
    
    public static StaffInventoryManager getInstance(Plugin plugin) {
        if (instance == null) {
            instance = new StaffInventoryManager(plugin);
        }
        return instance;
    }
    
    public Inventory getStaffInventory(Player player) {
        UUID uuid = player.getUniqueId();
        
        if (staffInventories.containsKey(uuid)) {
            return staffInventories.get(uuid);
        }
        
        Inventory inventory = Bukkit.createInventory(null, 54, "§6Staff Inventory - " + player.getName());
        loadInventoryFromFile(uuid, inventory);
        staffInventories.put(uuid, inventory);
        
        return inventory;
    }
    
    public void saveStaffInventory(Player player) {
        UUID uuid = player.getUniqueId();
        Inventory inventory = staffInventories.get(uuid);
        
        if (inventory != null) {
            saveInventoryToFile(uuid, inventory);
        }
    }
    
    public void saveAllInventories() {
        for (Map.Entry<UUID, Inventory> entry : staffInventories.entrySet()) {
            saveInventoryToFile(entry.getKey(), entry.getValue());
        }
    }
    
    private void loadInventoryFromFile(UUID uuid, Inventory inventory) {
        File file = new File(dataFolder, uuid.toString() + ".dat");
        
        if (!file.exists()) {
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ItemStack[] contents = (ItemStack[]) ois.readObject();
            inventory.setContents(contents);
        } catch (Exception e) {
            plugin.getLogger().warning("Error loading inventory for " + uuid + ": " + e.getMessage());
        }
    }
    
    private void saveInventoryToFile(UUID uuid, Inventory inventory) {
        File file = new File(dataFolder, uuid.toString() + ".dat");
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(inventory.getContents());
        } catch (Exception e) {
            plugin.getLogger().warning("Error saving inventory for " + uuid + ": " + e.getMessage());
        }
    }
    
    public void removeStaffInventory(UUID uuid) {
        staffInventories.remove(uuid);
        File file = new File(dataFolder, uuid.toString() + ".dat");
        if (file.exists()) {
            file.delete();
        }
    }
}