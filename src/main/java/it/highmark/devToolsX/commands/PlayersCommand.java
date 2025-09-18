// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.players")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.players");
            return true;
        }

        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        int maxPlayers = Bukkit.getMaxPlayers();
        
        sender.sendMessage(ChatColor.GOLD + "=== ONLINE PLAYERS ===");
        sender.sendMessage(ChatColor.YELLOW + "Online: " + ChatColor.WHITE + onlinePlayers.size() + 
                          ChatColor.GRAY + "/" + ChatColor.WHITE + maxPlayers);
        
        if (onlinePlayers.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "No players online");
            return true;
        }
        
        sender.sendMessage("");
        StringBuilder playerList = new StringBuilder();
        
        for (int i = 0; i < onlinePlayers.size(); i++) {
            Player player = onlinePlayers.get(i);
            
            ChatColor nameColor = ChatColor.GREEN;
            if (player.isOp()) {
                nameColor = ChatColor.RED;
            } else if (player.hasPermission("devtools.admin")) {
                nameColor = ChatColor.GOLD;
            }
            
            playerList.append(nameColor).append(player.getName());
            
            if (i < onlinePlayers.size() - 1) {
                playerList.append(ChatColor.WHITE).append(", ");
            }
        }
        
        sender.sendMessage(playerList.toString());
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "Legend: " + 
                          ChatColor.RED + "OP " + ChatColor.WHITE + "| " +
                          ChatColor.GOLD + "Admin " + ChatColor.WHITE + "| " +
                          ChatColor.GREEN + "Player");
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}