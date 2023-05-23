package me.tc6.essentialsnx;

import me.tc6.essentialsnx.Commands.GamemodeCommandExecutor;
import me.tc6.essentialsnx.Commands.ServerManagerCommandExecutor;
import me.tc6.essentialsnx.Events.ChatListener;
import me.tc6.essentialsnx.Events.PlayerQuitListener;
import me.tc6.essentialsnx.Resources.Lag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public final class EssentialsNX extends JavaPlugin {
    private GamemodeCommandExecutor gamemodeCommandExecutor;
    private PlayerQuitListener playerQuitListener;


    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("-------------------------------------------------");
        System.out.println(" ");
        System.out.println(ChatColor.GREEN + "[Connection] EssentialsNX is currently running!");

        System.out.println(" ");

        System.out.println("+ Working : /gm <0/1/2/3> <player>");
        gamemodeCommandExecutor = new GamemodeCommandExecutor();
        getCommand("gm").setExecutor(gamemodeCommandExecutor);
        playerQuitListener = new PlayerQuitListener(gamemodeCommandExecutor);

        getServer().getPluginManager().registerEvents(new ChatListener(), this);


        System.out.println("+ Working : /servermanager");
        getCommand("servermanager").setExecutor(new ServerManagerCommandExecutor());
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);






        System.out.println(" ");
        System.out.println("-------------------------------------------------");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println(ChatColor.RED + "[Disconnection] EssentialsNX is now shutdown!");

        }
    }

