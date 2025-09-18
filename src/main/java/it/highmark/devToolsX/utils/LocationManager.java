// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocationManager {
    private static final Map<UUID, Location> lastLocations = new HashMap<>();
    
    public static void setLastLocation(Player player, Location location) {
        lastLocations.put(player.getUniqueId(), location.clone());
    }
    
    public static Location getLastLocation(Player player) {
        return lastLocations.get(player.getUniqueId());
    }
    
    public static boolean hasLastLocation(Player player) {
        return lastLocations.containsKey(player.getUniqueId());
    }
    
    public static void removeLastLocation(Player player) {
        lastLocations.remove(player.getUniqueId());
    }
}