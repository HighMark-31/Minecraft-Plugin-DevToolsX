// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DuplicateCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }
        if (!p.hasPermission("devtools.duplicate")) {
            p.sendMessage("§cYou don't have permission: §7devtools.duplicate");
            return true;
        }

        ItemStack inHand = p.getInventory().getItemInMainHand();
        if (inHand == null || inHand.getType() == Material.AIR) {
            p.sendMessage("§cYou must hold an item in your hand.");
            return true;
        }

        ItemStack duplicate = inHand.clone();
        HashMap<Integer, ItemStack> leftover = p.getInventory().addItem(duplicate);
        if (!leftover.isEmpty()) {
            p.getWorld().dropItemNaturally(p.getLocation(), duplicate);
            p.sendMessage("§eInventory full! Duplicated item dropped on the ground.");
        } else {
            p.sendMessage("§aItem duplicated successfully!");
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList();
    }
}