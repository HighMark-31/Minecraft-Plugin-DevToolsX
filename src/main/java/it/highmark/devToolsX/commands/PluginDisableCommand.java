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
import java.util.List;

public class PluginDisableCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.plugindisable")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.plugindisable");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§eUsage: §f/plugindisable <plugin>");
            return true;
        }

        String pluginName = args[0];
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        
        if (plugin == null) {
            sender.sendMessage("§cPlugin not found: §7" + args[0]);
            return true;
        }

        if (!plugin.isEnabled()) {
            sender.sendMessage(ChatColor.YELLOW + "Plugin " + plugin.getName() + " is already disabled!");
            return true;
        }

        try {
            Bukkit.getPluginManager().disablePlugin(plugin);
            
            if (!plugin.isEnabled()) {
                sender.sendMessage(ChatColor.GREEN + "✓ Plugin " + plugin.getName() + " disabled successfully!");
            } else {
                sender.sendMessage(ChatColor.RED + "✗ Error disabling plugin!");
            }
            
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Error disabling plugin:");
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (plugin.isEnabled() && plugin.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(plugin.getName());
                }
            }
        }
        
        return completions;
    }
}