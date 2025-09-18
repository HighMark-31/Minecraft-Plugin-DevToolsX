// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;


import it.highmark.devToolsX.utils.ColorUtil;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class LoreCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }
        if (!p.hasPermission("devtools.lore")) {
            p.sendMessage("§cYou don't have permission: §7devtools.lore");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage("§eUsage: §f/lore add <text...> §7| §f/lore set <line 1-based> <text...> §7| §f/lore clear");
            return true;
        }
        ItemStack inHand = p.getInventory().getItemInMainHand();
        if (inHand == null || inHand.getType() == Material.AIR) {
            p.sendMessage("§cYou must hold an item in your hand.");
            return true;
        }
        ItemMeta meta = inHand.getItemMeta();
        List<String> lore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();

        String sub = args[0].toLowerCase(Locale.ROOT);
        switch (sub) {
            case "add": {
                if (args.length < 2) {
                    p.sendMessage("§eUsage: §f/lore add <text...>");
                    return true;
                }
                String line = ColorUtil.colorize(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
                lore.add(line);
                meta.setLore(lore);
                inHand.setItemMeta(meta);
                p.sendMessage("§aLine added to lore.");
                return true;
            }
            case "set": {
                if (args.length < 3) {
                    p.sendMessage("§eUsage: §f/lore set <line> <text...>");
                    return true;
                }
                int index;
                try {
                    index = Integer.parseInt(args[1]) - 1;
                } catch (NumberFormatException e) {
                    p.sendMessage("§cLine must be a number (1,2,3...).");
                    return true;
                }
                String line = ColorUtil.colorize(String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
                while (lore.size() <= index) lore.add("");
                lore.set(index, line);
                meta.setLore(lore);
                inHand.setItemMeta(meta);
                p.sendMessage("§aLine " + (index + 1) + " set.");
                return true;
            }
            case "clear": {
                meta.setLore(null);
                inHand.setItemMeta(meta);
                p.sendMessage("§aLore removed.");
                return true;
            }
            default:
                p.sendMessage("§eUsage: §f/lore add|set|clear");
                return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("add", "set", "clear");
        }
        return Collections.emptyList();
    }
}