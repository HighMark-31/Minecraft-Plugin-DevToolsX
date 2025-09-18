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
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class MockQuitCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.mockquit")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.mockquit");
            return true;
        }

        Player target;
        
        if (args.length >= 1) {
             target = Bukkit.getPlayer(args[0]);
             if (target == null) {
                 sender.sendMessage(ChatColor.RED + "Player not found: " + args[0]);
                 return true;
             }
         } else {
             if (!(sender instanceof Player)) {
                 sender.sendMessage(ChatColor.RED + "You must specify a player from console!");
                 sender.sendMessage(ChatColor.YELLOW + "Usage: /mockquit <player>");
                 return true;
             }
             target = (Player) sender;
         }

         try {
             PlayerQuitEvent mockEvent = new PlayerQuitEvent(target, ChatColor.YELLOW + target.getName() + " left the game");
             Bukkit.getPluginManager().callEvent(mockEvent);
             
             sender.sendMessage(ChatColor.GREEN + "✓ PlayerQuitEvent simulated for: " + ChatColor.WHITE + target.getName());
             sender.sendMessage(ChatColor.GRAY + "Quit message: " + mockEvent.getQuitMessage());
             
         } catch (Exception e) {
             sender.sendMessage(ChatColor.RED + "Error simulating event: " + e.getMessage());
         }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(player.getName());
                }
            }
        }
        
        return completions;
    }
}