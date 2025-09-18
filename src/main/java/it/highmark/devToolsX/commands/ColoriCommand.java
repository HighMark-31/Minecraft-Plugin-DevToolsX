// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ColoriCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }
        if (!p.hasPermission("devtools.colori")) {
            p.sendMessage("§cYou don't have permission: §7devtools.colori");
            return true;
        }

        p.sendMessage("§6=== §eColor Codes §6===");
        p.sendMessage("§0&0 §1&1 §2&2 §3&3 §4&4 §5&5 §6&6 §7&7");
        p.sendMessage("§8&8 §9&9 §a&a §b&b §c&c §d&d §e&e §f&f");
        p.sendMessage("§l&l §m&m §n&n §o&o §r&r §k&k");
        p.sendMessage("§eUse these codes in chat, signs, and items!");
        return true;
    }



    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}