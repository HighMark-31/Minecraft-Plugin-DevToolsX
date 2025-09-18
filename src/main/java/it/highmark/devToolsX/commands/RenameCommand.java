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

import java.util.Arrays;
import java.util.List;

public class RenameCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }
        if (!p.hasPermission("devtools.rename")) {
            p.sendMessage("§cYou don't have permission: §7devtools.rename");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage("§eUsage: §f/rename <new name...>");
            return true;
        }
        ItemStack inHand = p.getInventory().getItemInMainHand();
        if (inHand == null || inHand.getType() == Material.AIR) {
            p.sendMessage("§cYou must hold an item in your hand.");
            return true;
        }
        String raw = String.join(" ", args);
        String colored = ColorUtil.colorize(raw);

        ItemMeta meta = inHand.getItemMeta();
        meta.setDisplayName(colored);
        inHand.setItemMeta(meta);

        p.sendMessage("§aName set to: §r" + colored);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 0 ? Arrays.asList() : null;
    }
}