// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import it.highmark.devToolsX.utils.LocationManager;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class BackCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }
        if (!p.hasPermission("devtools.back")) {
            p.sendMessage("§cYou don't have permission: §7devtools.back");
            return true;
        }

        if (!LocationManager.hasLastLocation(p)) {
            p.sendMessage("§cYou don't have a previous location saved.");
            return true;
        }
        
        Location lastLocation = LocationManager.getLastLocation(p);
        Location currentLocation = p.getLocation();
        
        LocationManager.setLastLocation(p, currentLocation);
        p.teleport(lastLocation);
        
        p.sendMessage("§aYou returned to your previous location!");
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList();
    }
}