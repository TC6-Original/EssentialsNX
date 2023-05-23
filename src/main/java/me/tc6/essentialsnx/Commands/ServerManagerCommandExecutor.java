package me.tc6.essentialsnx.Commands;

import me.tc6.essentialsnx.Resources.Lag;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;


public class ServerManagerCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("servermanager")) {
            long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
            long totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
            long freeMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024;
            long usedMemory = totalMemory - freeMemory;
            long freeMemory2 = maxMemory - usedMemory;
            OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

            double cpuUsage = osBean.getSystemLoadAverage();
            if (cpuUsage < 0) {
                cpuUsage = getProcessCpuLoad();
            }
            cpuUsage = cpuUsage < 0 ? 0 : cpuUsage * 100;

            double usagePercentage = (double) usedMemory / maxMemory * 100;

            ChatColor memoryColor;

            if (usagePercentage >= 90) {
                memoryColor = ChatColor.RED;
            } else if (usagePercentage >= 75) {
                memoryColor = ChatColor.YELLOW;
            } else if (usagePercentage >= 50) {
                memoryColor = ChatColor.GREEN;
            } else {
                memoryColor = ChatColor.DARK_GREEN;
            }

            sender.sendMessage(ChatColor.GOLD + "----------------------------------------------------------------");
            sender.sendMessage(ChatColor.GREEN.BOLD +  "Server Performance:");
            sender.sendMessage(ChatColor.GRAY + "Memory: " + memoryColor + usedMemory + "MB" + " / " + maxMemory + "MB");
            sender.sendMessage(ChatColor.GRAY + "Free Memory: " + ChatColor.WHITE + freeMemory2 + "MB");
            sender.sendMessage(ChatColor.GRAY + "CPU Usage: " + ChatColor.WHITE + String.format("%.2f", cpuUsage) + "%");
            sender.sendMessage(ChatColor.GRAY + "TPS: " + ChatColor.WHITE + Lag.getTPS());
            sender.sendMessage(ChatColor.GOLD + "----------------------------------------------------------------");
            return true;
        }
        return false;
    }

    private double getProcessCpuLoad() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        if (osBean instanceof com.sun.management.OperatingSystemMXBean) {
            com.sun.management.OperatingSystemMXBean sunOsBean = (com.sun.management.OperatingSystemMXBean) osBean;
            return sunOsBean.getProcessCpuLoad();
        }
        return -1;
    }
}





