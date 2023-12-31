package com.ulxsth.police_wanted.scheduler;

import com.ulxsth.police_wanted.usecase.DisplayBossBarForEveryoneUseCase;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimerScheduler extends BukkitRunnable {
    private BossBar bossBar;

    private int limit;
    private int now;

    public GameTimerScheduler(int limit) {
        this.limit = limit;
        this.now = limit;
    }

    @Override
    public void run() {
        // ボスバーに表示
        if(this.bossBar == null) {
            this.bossBar = Bukkit.createBossBar("残り時間: " + this.now + "秒", BarColor.BLUE, BarStyle.SEGMENTED_12);
        }
        this.bossBar.setTitle("残り時間: " + this.now + "秒");
        this.bossBar.setProgress((double) this.now / limit);
        this.bossBar.setVisible(true);
        DisplayBossBarForEveryoneUseCase.execute(bossBar);
        this.now--;

        if(this.now < 0) {
            this.cancel();
        }
    }

    @Override
    public void cancel() {
        super.cancel();
        this.bossBar.setVisible(false);
    }
}
