package net.stockholm.clash.game.utils;

import net.stockholm.clash.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class BossBarBuilder {
    private static final Map<Player, BossBar> bossBarPlayer = new HashMap<>();

    public static void sendNotification(Entity entity, String string) {
        if (entity instanceof Player) {
            Player player = (Player) entity;

            BossBar bossBar = Bukkit.createBossBar(Utils.asColor(string), BarColor.BLUE, BarStyle.SOLID);
            bossBar.setProgress(0);

            if (bossBarPlayer.get(player) != null) {
                bossBarPlayer.getOrDefault(player, null).removePlayer(player);
            }

            bossBar.addPlayer(player);
            bossBarPlayer.put(player, bossBar);

            new BukkitRunnable() {
                @Override
                public void run() {
                    bossBar.removePlayer(player);
                    bossBarPlayer.remove(player, bossBar);
                }
            }.runTaskLater(Main.getInstance(), 20L * 3L);

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1.0f, 1.0f);
        }
    }
}
