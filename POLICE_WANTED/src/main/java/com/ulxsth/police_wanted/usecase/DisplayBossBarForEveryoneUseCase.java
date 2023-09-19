package com.ulxsth.police_wanted.usecase;

import com.ulxsth.police_wanted.PoliceWantedPlugin;
import org.bukkit.boss.BossBar;

public class DisplayBossBarForEveryoneUseCase {
    private static final PoliceWantedPlugin plugin = PoliceWantedPlugin.getInstance();

    public static void execute(BossBar bossBar) {
        // オンラインプレイヤーを取得してボスバーを表示
        plugin.getServer().getOnlinePlayers().forEach(bossBar::addPlayer);
    }
}
