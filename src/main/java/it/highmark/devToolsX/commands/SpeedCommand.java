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

public class SpeedCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§eUsage: §f/speed <1-10> [player]");
            return true;
        }
        
        int speed;
        try {
            speed = Integer.parseInt(args[0]);
            if (speed < 1 || speed > 10) {
                sender.sendMessage("§cSpeed must be between 1 and 10.");
                return true;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("§cInvalid value. Use a number between 1 and 10.");
            return true;
        }
        
        Player target;
        if (args.length == 1) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage("§cYou must specify a player from console.");
                return true;
            }
            if (!p.hasPermission("devtools.speed.self")) {
                p.sendMessage("§cYou don't have permission: §7devtools.speed.self");
                return true;
            }
            target = p;
        } else {
            if (!sender.hasPermission("devtools.speed.others")) {
                sender.sendMessage("§cYou don't have permission: §7devtools.speed.others");
                return true;
            }
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found: §7" + args[1]);
                return true;
            }
        }
        
        float speedValue = speed / 10.0f;
        target.setWalkSpeed(speedValue);
        target.setFlySpeed(speedValue);
        
        if (target == sender) {
            sender.sendMessage("§aSpeed set to §e" + speed + "§a!");
        } else {
            sender.sendMessage("§a" + target.getName() + "§a's speed set to §e" + speed + "§a!");
            target.sendMessage("§aYour speed has been set to §e" + speed + "§a by §e" + sender.getName() + "§a!");
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        } else if (args.length == 2 && sender.hasPermission("devtools.speed.others")) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Arrays.asList();
    }
}