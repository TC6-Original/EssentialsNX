package me.tc6.essentialsnx.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true); // Stop players from typing in chat

        // Custom chat handling
        Player player = event.getPlayer();
        String playerName = player.getName();
        String message = event.getMessage();
        int experienceLevel = player.getLevel();

        String nlevel;
        if (experienceLevel >= 100) {
            nlevel = ChatColor.GOLD + "[" + experienceLevel + "✫] ";
        } else if (experienceLevel >= 50) {
            nlevel = ChatColor.YELLOW + "[" + experienceLevel + "✫] ";
        } else if (experienceLevel >= 20) {
            nlevel = ChatColor.WHITE + "[" + experienceLevel + "✫] ";
        } else {
            nlevel = ChatColor.GRAY + "[" + experienceLevel + "✫] ";
        }

        String prefix = getPrefix(playerName);

        // Modify the message or perform any custom logic
        String modifiedMessage = nlevel + prefix + playerName + ChatColor.WHITE + ": " + message;

        // Broadcast the modified message to all players
        event.getRecipients().forEach(recipient -> recipient.sendMessage(modifiedMessage));
    }

    private String getPrefix(String playerName) {
        if (playerName.equalsIgnoreCase("TC6")) {
            return ChatColor.DARK_RED + "[" + ChatColor.DARK_RED.BOLD + "Owner" + ChatColor.DARK_RED + "] ";
        } else {
            return ChatColor.GRAY + "[" + ChatColor.GRAY.BOLD + "Player" + ChatColor.GRAY + "] ";
        }
    }
}




