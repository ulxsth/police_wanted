package com.ulxsth.police_wanted.usecase;

import org.bukkit.Bukkit;

public class BroadcastMessageUseCase {
    public static void execute(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }
}
