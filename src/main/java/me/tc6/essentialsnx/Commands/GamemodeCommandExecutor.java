package me.tc6.essentialsnx.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GamemodeCommandExecutor implements CommandExecutor {
    private Map<UUID, GameMode> lastGamemode = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            // No arguments provided, show usage
            return false;
        }

        // Get the target player
        Player targetPlayer = player;
        if (args.length > 1) {
            targetPlayer = player.getServer().getPlayer(args[1]);
            if (targetPlayer == null) {
                player.sendMessage("Player not found.");
                return true;
            }
        }

        // Parse the gamemode argument
        GameMode gameMode;
        try {
            int gamemodeInt = Integer.parseInt(args[0]);
            gameMode = GameMode.getByValue(gamemodeInt);
        } catch (IllegalArgumentException e) {
            player.sendMessage("Invalid gamemode specified.");
            return true;
        }

        // Save the last gamemode of the target player
        lastGamemode.put(targetPlayer.getUniqueId(), targetPlayer.getGameMode());

        // Set the gamemode of the target player
        targetPlayer.setGameMode(gameMode);
        player.sendMessage("Gamemode set to " + gameMode.toString() + " for player " + targetPlayer.getName());

        return true;
    }

    // Save the last gamemode when a player logs off
    public void saveLastGamemode(Player player) {
        lastGamemode.put(player.getUniqueId(), player.getGameMode());
    }

    // Get the last gamemode of a player
    public GameMode getLastGamemode(Player player) {
        return lastGamemode.getOrDefault(player.getUniqueId(), GameMode.SURVIVAL);
    }
}

