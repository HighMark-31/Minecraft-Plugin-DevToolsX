// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityCountCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.entitycount")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.entitycount");
            return true;
        }

        World world;
        if (sender instanceof Player p) {
            world = p.getWorld();
        } else {
            if (args.length == 0) {
                sender.sendMessage("§cYou must specify a world from console");
                return true;
            }
            world = sender.getServer().getWorld(args[0]);
            if (world == null) {
                sender.sendMessage("§cWorld not found: §7" + args[0]);
                return true;
            }
        }
        
        Map<EntityType, Integer> entityCounts = new HashMap<>();
        int totalEntities = 0;
        int totalMobs = 0;
        int totalAnimals = 0;
        int totalPlayers = 0;
        int totalItems = 0;
        
        for (Entity entity : world.getEntities()) {
            EntityType type = entity.getType();
            entityCounts.put(type, entityCounts.getOrDefault(type, 0) + 1);
            totalEntities++;
            
            if (entity instanceof Monster) {
                totalMobs++;
            } else if (entity instanceof Animals) {
                totalAnimals++;
            } else if (entity instanceof Player) {
                totalPlayers++;
            } else if (entity instanceof Item) {
                totalItems++;
            }
        }
        
        sender.sendMessage("§6=== §eEntity Count - " + world.getName() + " §6===");
        sender.sendMessage("§aTotal Entities: §f" + totalEntities);
        sender.sendMessage("§aPlayers: §f" + totalPlayers);
        sender.sendMessage("§aHostile Mobs: §f" + totalMobs);
        sender.sendMessage("§aAnimals: §f" + totalAnimals);
        sender.sendMessage("§aDropped Items: §f" + totalItems);
        sender.sendMessage("§aOther: §f" + (totalEntities - totalPlayers - totalMobs - totalAnimals - totalItems));
        
        if (totalEntities > 500) {
            sender.sendMessage("§c⚠ Warning: Many entities could cause lag!");
        } else if (totalEntities > 300) {
            sender.sendMessage("§e⚠ High number of entities in the world.");
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && !(sender instanceof Player)) {
            return sender.getServer().getWorlds().stream()
                    .map(World::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                    .toList();
        }
        return Arrays.asList();
    }
}