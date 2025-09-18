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

import java.util.ArrayList;
import java.util.List;

public class PermCheckCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.permcheck")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.permcheck");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /permcheck <permission> [player]");
            return true;
        }

        String permission = args[0];
        Player target;

        if (args.length >= 2) {
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found: " + args[1]);
                return true;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must specify a player from console!");
                return true;
            }
            target = (Player) sender;
        }

        boolean hasPermission = target.hasPermission(permission);
        
        sender.sendMessage(ChatColor.YELLOW + "Player: " + ChatColor.WHITE + target.getName());
        sender.sendMessage(ChatColor.YELLOW + "Permission: " + ChatColor.WHITE + permission);
        
        if (hasPermission) {
            sender.sendMessage(ChatColor.GREEN + "✓ HAS PERMISSION");
        } else {
            sender.sendMessage(ChatColor.RED + "✗ NO PERMISSION");
        }
        
        if (target.isOp()) {
            sender.sendMessage(ChatColor.GOLD + "ℹ Player is OP");
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            String[] commonPerms = {
                "devtools.", "bukkit.", "minecraft.", "essentials.", 
                "worldedit.", "worldguard.", "luckperms.", "vault."
            };
            for (String perm : commonPerms) {
                if (perm.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(perm);
                }
            }
        } else if (args.length == 2) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                    completions.add(player.getName());
                }
            }
        }
        
        return completions;
    }
}