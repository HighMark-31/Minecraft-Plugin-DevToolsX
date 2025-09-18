// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CoordsCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }
        if (!p.hasPermission("devtools.coords")) {
            p.sendMessage("§cYou don't have permission: §7devtools.coords");
            return true;
        }

        Location loc = p.getLocation();
        String world = loc.getWorld().getName();
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();

        p.sendMessage("§aYour coordinates:");
        p.sendMessage("§eWorld: §f" + world);
        p.sendMessage("§eX: §f" + x + " §eY: §f" + y + " §eZ: §f" + z);
        p.sendMessage("§aYaw: §f" + String.format("%.2f", yaw) + "° §aPitch: §f" + String.format("%.2f", pitch) + "°");
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList();
    }
}