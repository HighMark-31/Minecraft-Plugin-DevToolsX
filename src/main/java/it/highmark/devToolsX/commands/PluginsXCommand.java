// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginsXCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.pluginsx")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.pluginsx");
            return true;
        }

        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        
        sender.sendMessage(ChatColor.GOLD + "=== PLUGIN LIST (" + plugins.length + ") ===");
        
        List<Plugin> enabledPlugins = new ArrayList<>();
        List<Plugin> disabledPlugins = new ArrayList<>();
        
        for (Plugin plugin : plugins) {
            if (plugin.isEnabled()) {
                enabledPlugins.add(plugin);
            } else {
                disabledPlugins.add(plugin);
            }
        }
        
        if (!enabledPlugins.isEmpty()) {
            sender.sendMessage(ChatColor.GREEN + "✓ ENABLED (" + enabledPlugins.size() + "):");
            StringBuilder enabledList = new StringBuilder();
            for (int i = 0; i < enabledPlugins.size(); i++) {
                Plugin plugin = enabledPlugins.get(i);
                enabledList.append(ChatColor.GREEN).append(plugin.getName());
                if (i < enabledPlugins.size() - 1) {
                    enabledList.append(ChatColor.WHITE).append(", ");
                }
            }
            sender.sendMessage(enabledList.toString());
        }
        
        if (!disabledPlugins.isEmpty()) {
            sender.sendMessage("");
            sender.sendMessage(ChatColor.RED + "✗ DISABLED (" + disabledPlugins.size() + "):");
            StringBuilder disabledList = new StringBuilder();
            for (int i = 0; i < disabledPlugins.size(); i++) {
                Plugin plugin = disabledPlugins.get(i);
                disabledList.append(ChatColor.RED).append(plugin.getName());
                if (i < disabledPlugins.size() - 1) {
                    disabledList.append(ChatColor.WHITE).append(", ");
                }
            }
            sender.sendMessage(disabledList.toString());
        }
        
        sender.sendMessage("");
        sender.sendMessage(ChatColor.YELLOW + "Use /pluginreload, /pluginenable, /plugindisable to manage plugins");
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}