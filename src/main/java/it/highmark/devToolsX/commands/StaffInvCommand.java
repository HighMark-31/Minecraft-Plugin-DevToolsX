// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import it.highmark.devToolsX.utils.StaffInventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class StaffInvCommand implements TabExecutor {
    
    private final Plugin plugin;
    
    public StaffInvCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.staffinv")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.staffinv");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;
        StaffInventoryManager manager = StaffInventoryManager.getInstance(plugin);
        
        Inventory staffInv = manager.getStaffInventory(player);
        player.openInventory(staffInv);
        
        player.sendMessage(ChatColor.GREEN + "✓ Staff inventory opened!");
        player.sendMessage(ChatColor.GRAY + "Items are automatically saved when you close the inventory.");
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}