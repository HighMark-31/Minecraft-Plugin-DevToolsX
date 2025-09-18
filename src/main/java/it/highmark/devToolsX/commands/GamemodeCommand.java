// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class GamemodeCommand implements TabExecutor {
    private final GameMode mode;

    public GamemodeCommand(GameMode mode) {
        this.mode = mode;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean targetOthers = args.length >= 1;
        String perm = targetOthers ? "devtools.gamemode.others" : "devtools.gamemode.self";
        if (!sender.hasPermission(perm)) {
            sender.sendMessage("§cYou don't have permission: §7" + perm);
            return true;
        }

        Player target;
        if (targetOthers) {
            target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found: §f" + args[0]);
                return true;
            }
        } else {
            if (!(sender instanceof Player p)) {
                sender.sendMessage("§eFrom console use: §f/" + label + " <player>");
                return true;
            }
            target = p;
        }

        target.setGameMode(mode);
        target.sendMessage("§aGameMode set to §f" + mode.name());
        if (sender != target) {
            sender.sendMessage("§aSet §f" + target.getName() + "§a's GameMode to §f" + mode.name());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        return args.length == 1 ? null : Collections.emptyList();
    }
}
