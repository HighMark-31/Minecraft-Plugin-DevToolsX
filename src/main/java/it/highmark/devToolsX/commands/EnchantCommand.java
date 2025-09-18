// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import it.highmark.devToolsX.utils.EnchantUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EnchantCommand implements TabExecutor {

    public EnchantCommand() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.enchant")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.enchant");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("§eUsage: §f/enchantx <enchant> <level|max> [player]");
            return true;
        }

        Enchantment ench = EnchantUtil.resolve(args[0]);
        if (ench == null) {
            sender.sendMessage("§cUnknown enchantment: §f" + args[0]);
            return true;
        }

        int level;
        if ("max".equalsIgnoreCase(args[1])) {
            level = Integer.MAX_VALUE;
        } else {
            try {
                level = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cInvalid level. Use a number or 'max'.");
                return true;
            }
        }

        Player target;
        if (args.length >= 3) {
            target = Bukkit.getPlayerExact(args[2]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found: §f" + args[2]);
                return true;
            }
        } else if (sender instanceof Player) {
            target = (Player) sender;
        } else {
            sender.sendMessage("§eFrom console specify the player: §f/enchantx <enchant> <level|max> <player>");
            return true;
        }

        ItemStack inHand = target.getInventory().getItemInMainHand();
        if (inHand == null || inHand.getType() == Material.AIR) {
            sender.sendMessage("§c" + target.getName() + " doesn't have an item in hand.");
            return true;
        }

        inHand.addUnsafeEnchantment(ench, level);
        sender.sendMessage("§aApplied §f" + ench.getKey().getKey().toLowerCase(Locale.ROOT) + " §aLv §f" + level + " §ato " + target.getName());
        if (sender != target) {
            target.sendMessage("§aYour item in hand received: §f" + ench.getKey().getKey() + " §7Lv " + level);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return EnchantUtil.suggestions(args[0]);
        } else if (args.length == 2) {
            return List.of("1", "5", "10", "32", "100", "max");
        }
        return Collections.emptyList();
    }
}