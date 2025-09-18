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

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

public class ThreadInfoCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("devtools.threadinfo")) {
            sender.sendMessage("§cYou don't have permission: §7devtools.threadinfo");
            return true;
        }

        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadBean.dumpAllThreads(false, false);
        
        sender.sendMessage(ChatColor.GOLD + "=== THREAD INFORMATION ===");
        sender.sendMessage(ChatColor.YELLOW + "Active threads: " + ChatColor.WHITE + threadInfos.length);
        sender.sendMessage("");
        
        int blockedCount = 0;
        int waitingCount = 0;
        int runnableCount = 0;
        
        for (ThreadInfo threadInfo : threadInfos) {
            String threadName = threadInfo.getThreadName();
            Thread.State state = threadInfo.getThreadState();
            
            ChatColor stateColor;
            switch (state) {
                case BLOCKED:
                    stateColor = ChatColor.RED;
                    blockedCount++;
                    break;
                case WAITING:
                case TIMED_WAITING:
                    stateColor = ChatColor.YELLOW;
                    waitingCount++;
                    break;
                case RUNNABLE:
                    stateColor = ChatColor.GREEN;
                    runnableCount++;
                    break;
                default:
                    stateColor = ChatColor.GRAY;
                    break;
            }
            
            sender.sendMessage(ChatColor.AQUA + threadName + " " + stateColor + "[" + state + "]");
            
            if (state == Thread.State.BLOCKED && threadInfo.getLockOwnerName() != null) {
                sender.sendMessage(ChatColor.RED + "  Blocked by: " + threadInfo.getLockOwnerName());
            }
        }
        
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GREEN + "Runnable: " + runnableCount + 
                          ChatColor.YELLOW + " | Waiting: " + waitingCount + 
                          ChatColor.RED + " | Blocked: " + blockedCount);
        
        if (blockedCount > 0) {
            sender.sendMessage(ChatColor.RED + "⚠ Warning: " + blockedCount + " blocked threads detected!");
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}