// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.util.Arrays;
import java.util.List;

public class ServerInfoCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.serverinfo")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.serverinfo");
            return true;
        }
        
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;
        
        double[] tps = getTPS();
        String tpsColor = getTpsColor(tps[0]);
        
        sender.sendMessage("§6=== §eServer Information §6===");
        sender.sendMessage("§aBukkit Version: §f" + Bukkit.getBukkitVersion());
        sender.sendMessage("§aServer Version: §f" + Bukkit.getVersion());
        sender.sendMessage("§aOnline Players: §f" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
        sender.sendMessage("§aTPS (1m): " + tpsColor + String.format("%.2f", tps[0]));
        sender.sendMessage("§aTPS (5m): " + getTpsColor(tps[1]) + String.format("%.2f", tps[1]));
        sender.sendMessage("§aTPS (15m): " + getTpsColor(tps[2]) + String.format("%.2f", tps[2]));
        sender.sendMessage("§aUsed RAM: §f" + usedMemory + "MB §7/ §f" + maxMemory + "MB");
        sender.sendMessage("§aFree RAM: §f" + (maxMemory - usedMemory) + "MB");
        sender.sendMessage("§aUptime: §f" + getUptime());
        
        return true;
    }
    
    private double[] getTPS() {
        try {
            Object server = Bukkit.getServer().getClass().getMethod("getServer").invoke(Bukkit.getServer());
            Object tpsField = server.getClass().getField("recentTps").get(server);
            return (double[]) tpsField;
        } catch (Exception e) {
            return new double[]{20.0, 20.0, 20.0};
        }
    }
    
    private String getTpsColor(double tps) {
        if (tps >= 18.0) return "§a";
        else if (tps >= 15.0) return "§e";
        else if (tps >= 10.0) return "§6";
        else return "§c";
    }
    
    private String getUptime() {
        try {
            long uptime = System.currentTimeMillis() - (long) Bukkit.getServer().getClass().getMethod("getStartTime").invoke(Bukkit.getServer());
            long seconds = uptime / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            
            if (days > 0) {
                return days + "g " + (hours % 24) + "h " + (minutes % 60) + "m";
            } else if (hours > 0) {
                return hours + "h " + (minutes % 60) + "m";
            } else {
                return minutes + "m " + (seconds % 60) + "s";
            }
        } catch (Exception e) {
            return "N/A";
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList();
    }
}