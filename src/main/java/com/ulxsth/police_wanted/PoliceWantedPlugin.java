package com.ulxsth.police_wanted;

import com.ulxsth.police_wanted.command.PwCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PoliceWantedPlugin extends JavaPlugin {
    private static PoliceWantedPlugin instance;

    public static PoliceWantedPlugin getInstance() {
        if(instance == null) {
            throw new IllegalStateException("instance is not initialized");
        }

        return instance;
    }

    @Override
    public void onEnable() {
        // インスタンスの初期化
        if(instance != null) {
            throw new IllegalStateException("instance is already initialized");
        }
        instance = this;

        // コマンドを登録
        Objects.requireNonNull(this.getCommand("pw")).setExecutor(new PwCommand());
    }
}
