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

public class PingCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage("§cYou must specify a player from console.");
                return true;
            }
            if (!p.hasPermission("devtools.ping.self")) {
                p.sendMessage("§cYou don't have permission: §7devtools.ping.self");
                return true;
            }
            int ping = p.getPing();
            p.sendMessage("§aYour ping: §e" + ping + "ms");
        } else {
            if (!sender.hasPermission("devtools.ping.others")) {
                sender.sendMessage("§cYou don't have permission: §7devtools.ping.others");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found: §7" + args[0]);
                return true;
            }
            int ping = target.getPing();
            sender.sendMessage("§a" + target.getName() + "'s ping: §e" + ping + "ms");
        }
        return true;
    }
    
    private String getPingColor(int ping) {
        if (ping <= 50) return "§a";
        else if (ping <= 100) return "§e";
        else if (ping <= 200) return "§6";
        else return "§c";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && sender.hasPermission("devtools.ping.others")) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Arrays.asList();
    }
}