// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX;
import it.highmark.devToolsX.commands.*;
import it.highmark.devToolsX.listeners.LocationListener;
import it.highmark.devToolsX.listeners.StaffInventoryListener;
import it.highmark.devToolsX.utils.StaffInventoryManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DevToolsX extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("rename").setExecutor(new RenameCommand());
        getCommand("rename").setTabCompleter(new RenameCommand());

        getCommand("lore").setExecutor(new LoreCommand());
        getCommand("lore").setTabCompleter(new LoreCommand());

        EnchantCommand enchantCommand = new EnchantCommand();
        getCommand("enchantx").setExecutor(enchantCommand);
        getCommand("enchantx").setTabCompleter(enchantCommand);

        getCommand("gmc").setExecutor(new GamemodeCommand(org.bukkit.GameMode.CREATIVE));
        getCommand("gms").setExecutor(new GamemodeCommand(org.bukkit.GameMode.SURVIVAL));
        getCommand("gma").setExecutor(new GamemodeCommand(org.bukkit.GameMode.ADVENTURE));
        getCommand("gmsp").setExecutor(new GamemodeCommand(org.bukkit.GameMode.SPECTATOR));

        DuplicateCommand duplicateCommand = new DuplicateCommand();
        getCommand("duplicate").setExecutor(duplicateCommand);
        getCommand("duplicate").setTabCompleter(duplicateCommand);

        HealCommand healCommand = new HealCommand();
        getCommand("heal").setExecutor(healCommand);
        getCommand("heal").setTabCompleter(healCommand);

        SpeedCommand speedCommand = new SpeedCommand();
        getCommand("speed").setExecutor(speedCommand);
        getCommand("speed").setTabCompleter(speedCommand);

        BackCommand backCommand = new BackCommand();
        getCommand("back").setExecutor(backCommand);
        getCommand("back").setTabCompleter(backCommand);

        PingCommand pingCommand = new PingCommand();
        getCommand("ping").setExecutor(pingCommand);
        getCommand("ping").setTabCompleter(pingCommand);

        CoordsCommand coordsCommand = new CoordsCommand();
        getCommand("coords").setExecutor(coordsCommand);
        getCommand("coords").setTabCompleter(coordsCommand);

        ServerInfoCommand serverInfoCommand = new ServerInfoCommand();
        getCommand("serverinfo").setExecutor(serverInfoCommand);
        getCommand("serverinfo").setTabCompleter(serverInfoCommand);

        EntityCountCommand entityCountCommand = new EntityCountCommand();
        getCommand("entitycount").setExecutor(entityCountCommand);
        getCommand("entitycount").setTabCompleter(entityCountCommand);

        ThreadInfoCommand threadInfoCommand = new ThreadInfoCommand();
        getCommand("threadinfo").setExecutor(threadInfoCommand);
        getCommand("threadinfo").setTabCompleter(threadInfoCommand);

        PermCheckCommand permCheckCommand = new PermCheckCommand();
        getCommand("permcheck").setExecutor(permCheckCommand);
        getCommand("permcheck").setTabCompleter(permCheckCommand);

        MockJoinCommand mockJoinCommand = new MockJoinCommand();
        getCommand("mockjoin").setExecutor(mockJoinCommand);
        getCommand("mockjoin").setTabCompleter(mockJoinCommand);

        MockQuitCommand mockQuitCommand = new MockQuitCommand();
        getCommand("mockquit").setExecutor(mockQuitCommand);
        getCommand("mockquit").setTabCompleter(mockQuitCommand);

        ReloadConfigCommand reloadConfigCommand = new ReloadConfigCommand();
        getCommand("reloadconfig").setExecutor(reloadConfigCommand);
        getCommand("reloadconfig").setTabCompleter(reloadConfigCommand);

        PluginsXCommand pluginsXCommand = new PluginsXCommand();
        getCommand("pluginsx").setExecutor(pluginsXCommand);
        getCommand("pluginsx").setTabCompleter(pluginsXCommand);

        PluginReloadCommand pluginReloadCommand = new PluginReloadCommand();
        getCommand("pluginreload").setExecutor(pluginReloadCommand);
        getCommand("pluginreload").setTabCompleter(pluginReloadCommand);

        PluginEnableCommand pluginEnableCommand = new PluginEnableCommand();
        getCommand("pluginenable").setExecutor(pluginEnableCommand);
        getCommand("pluginenable").setTabCompleter(pluginEnableCommand);

        PluginDisableCommand pluginDisableCommand = new PluginDisableCommand();
        getCommand("plugindisable").setExecutor(pluginDisableCommand);
        getCommand("plugindisable").setTabCompleter(pluginDisableCommand);

        ColoriCommand coloriCommand = new ColoriCommand();
        getCommand("colori").setExecutor(coloriCommand);
        getCommand("colori").setTabCompleter(coloriCommand);

        PlayersCommand playersCommand = new PlayersCommand();
        getCommand("players").setExecutor(playersCommand);
        getCommand("players").setTabCompleter(playersCommand);

        StaffInvCommand staffInvCommand = new StaffInvCommand(this);
        getCommand("staffinv").setExecutor(staffInvCommand);
        getCommand("staffinv").setTabCompleter(staffInvCommand);

        getServer().getPluginManager().registerEvents(new LocationListener(), this);
        getServer().getPluginManager().registerEvents(new StaffInventoryListener(this), this);

        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "-------------------------- READ THIS ----------------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "/////----------- DevToolsX enabled successfully ---------/////");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-------------- Created by HighMark [All rights reserved] ----------------");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Would you like a plugin like this ? https://enderdevelopment.com");
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "-------------------------- READ THIS ----------------------------------");

    }

    @Override
    public void onDisable() {
        StaffInventoryManager manager = StaffInventoryManager.getInstance(this);
        manager.saveAllInventories();
        
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "-------------------------- READ THIS ----------------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "/////----------- DevToolsX disabled successfully ---------/////");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-------------- Created by HighMark [All rights reserved] ----------------");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Would you like a plugin like this ? https://enderdevelopment.com");
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "-------------------------- READ THIS ----------------------------------");
    }
}