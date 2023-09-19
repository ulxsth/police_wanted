package com.ulxsth.police_wanted.command;

import com.ulxsth.police_wanted.PoliceWantedPlugin;
import com.ulxsth.police_wanted.scheduler.GameTimerScheduler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PwCommand implements CommandExecutor {
    private static final String COMMAND_HELP = "USAGE:\n" +
            "/pw start <time> : ゲームを開始します\n" +
            "/pw stop : ゲームを終了します\n"
            ;

    private static final PoliceWantedPlugin plugin = PoliceWantedPlugin.getInstance();
    GameTimerScheduler gameTimerScheduler = null;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // 引数なし -> ヘルプを表示
        if(args.length == 0) {
            sender.sendMessage(COMMAND_HELP);
            return true;
        }

        // start
        if(args[0].equalsIgnoreCase("start")) {
            // 引数が足りない
            if(args.length < 2) {
                sender.sendMessage(COMMAND_HELP);
                return true;
            }

            // 引数が数字でない
            if(!args[1].matches("^[0-9]+$")) {
                sender.sendMessage("時間は数字で指定してください");
                return true;
            }

            // 既にゲームが開始されている
            if(gameTimerScheduler != null) {
                sender.sendMessage("既にゲームが開始されています");
                return true;
            }

             // ゲームを開始
             this.gameTimerScheduler = new GameTimerScheduler(Integer.parseInt(args[1]));
             gameTimerScheduler.runTaskTimer(plugin, 0, 20);
             return true;
        }

        // stop
        if(args[0].equalsIgnoreCase("stop")) {
            // ゲームを終了
            gameTimerScheduler.cancel();
            this.gameTimerScheduler = null;

            sender.sendMessage("ゲームを終了しました");
            return true;
        }

        return true;
    }
}
