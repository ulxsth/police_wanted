package com.ulxsth.police_wanted.command;

import com.ulxsth.police_wanted.PoliceWantedPlugin;
import com.ulxsth.police_wanted.scheduler.GameTimerScheduler;
import com.ulxsth.police_wanted.usecase.BroadcastMessageUseCase;
import com.ulxsth.police_wanted.usecase.CreateItemStackUseCase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class PwCommand implements CommandExecutor {
    private static final String COMMAND_HELP = "USAGE:\n" +
            "/pw start <time> : ゲームを開始します\n" +
            "/pw stop : ゲームを終了します\n" +
            "/pw give <item> <amount> : アイテムを入手します\n" +
            "/pw give <item> <amount> <player> : 指定したプレイヤーにアイテムを入手させます\n";

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

            BroadcastMessageUseCase.execute("ゲームを終了しました");
            return true;
        }

        // give
        if(args[0].equalsIgnoreCase("give")) {
            String itemName = args[1];
            int amount = Integer.parseInt(args[2]);
            String playerName = args[3];

            // 引数が足りない
            if(args.length < 2) {
                sender.sendMessage(COMMAND_HELP);
                return true;
            }

            // 引数が数字でない
            if(!args[2].matches("^[0-9]+$")) {
                sender.sendMessage("個数は数字で指定してください");
                return true;
            }

            // アイテムを渡すプレイヤーを取得（指定がなければコマンド実行者）
            Player target = args.length == 4 ? plugin.getServer().getPlayer(args[2]) : (Player) sender;
            if(target == null) {
                sender.sendMessage("指定したプレイヤーは存在しません");
                return true;
            }

            // アイテムを渡す
            ItemStack itemStack = null;
            try {
                itemStack = CreateItemStackUseCase.execute(itemName, amount);
            } catch (IllegalArgumentException e) {
                sender.sendMessage("指定したidのアイテムは存在しません");
                plugin.logger.info(e.getMessage());
                return true;
            }

            target.getInventory().addItem(itemStack);

            // 指定したプレイヤーにアイテムを入手させる
            sender.sendMessage("アイテムを渡しました");
            return true;
        }

        return true;
    }
}
