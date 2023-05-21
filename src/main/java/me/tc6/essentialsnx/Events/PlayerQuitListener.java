package me.tc6.essentialsnx.Events;

import me.tc6.essentialsnx.Commands.GamemodeCommandExecutor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private GamemodeCommandExecutor gamemodeCommandExecutor;

    public PlayerQuitListener(GamemodeCommandExecutor gamemodeCommandExecutor) {
        this.gamemodeCommandExecutor = gamemodeCommandExecutor;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        gamemodeCommandExecutor.saveLastGamemode(player);
    }

    public GameMode getLastGamemode(Player player) {
        return gamemodeCommandExecutor.getLastGamemode(player);
    }
}

