package com.ulxsth.police_wanted.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimerScheduler extends BukkitRunnable {
    private int limit;
    private int now;

    public GameTimerScheduler(int limit) {
        this.limit = limit;
        this.now = limit;
    }

    @Override
    public void run() {
        if(now <= 0) {
            this.cancel();
        }

        // ボスバーに表示
        BossBar bossBar = Bukkit.createBossBar("残り時間: " + now + "秒", BarColor.BLUE, BarStyle.SEGMENTED_12);
        bossBar.setProgress((double) now / limit);
        now--;
    }
}
