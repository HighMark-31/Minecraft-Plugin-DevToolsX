// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HealCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage("§cYou must specify a player from console.");
                return true;
            }
            if (!p.hasPermission("devtools.heal.self")) {
                p.sendMessage("§cYou don't have permission: §7devtools.heal.self");
                return true;
            }
            healPlayer(p);
            p.sendMessage("§aYou have been fully healed!");
            return true;
        }
        
        if (!sender.hasPermission("devtools.heal.others")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.heal.others");
            return true;
        }
        
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found: §7" + args[0]);
            return true;
        }
        
        healPlayer(target);
        sender.sendMessage("§aYou healed §e" + target.getName() + "§a!");
        target.sendMessage("§aYou have been healed by §e" + sender.getName() + "§a!");
        
        return true;
    }
    
    private void healPlayer(Player player) {
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(20.0f);
        player.setExhaustion(0.0f);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && sender.hasPermission("devtools.heal.others")) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Arrays.asList();
    }
}