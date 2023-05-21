package me.tc6.essentialsnx;

import me.tc6.essentialsnx.Commands.GamemodeCommandExecutor;
import me.tc6.essentialsnx.Commands.ServerManagerCommandExecutor;
import me.tc6.essentialsnx.Events.ChatListener;
import me.tc6.essentialsnx.Events.PlayerQuitListener;
import me.tc6.essentialsnx.Resources.Lag;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class EssentialsNX extends JavaPlugin {
    private GamemodeCommandExecutor gamemodeCommandExecutor;
    private PlayerQuitListener playerQuitListener;


    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("\u001B-------------------------------------------------\u001B[0m");
        System.out.println(" ");
        System.out.println("\u001B[32m[Connection] EssentialsNX is now running!\u001B[0m");

        System.out.println(" ");

        System.out.println("\u001B+ Working : /gm <0/1/2/3> <player>\u001B[0m");
        gamemodeCommandExecutor = new GamemodeCommandExecutor();
        getCommand("gm").setExecutor(gamemodeCommandExecutor);
        playerQuitListener = new PlayerQuitListener(gamemodeCommandExecutor);

        getServer().getPluginManager().registerEvents(new ChatListener(), this);


        System.out.println("\u001B+ Working : /servermanager\u001B[0m");
        getCommand("servermanager").setExecutor(new ServerManagerCommandExecutor());
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);






        System.out.println(" ");
        System.out.println("\u001B-------------------------------------------------\u001B[0m");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("\u001B[31m[Disconnection] EssentialsNX is now shutdown!\u001B[0m");

        }
    }

