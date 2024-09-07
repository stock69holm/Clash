package net.stockholm.clash.game.player.scoreboard;

import org.bukkit.entity.Player;

public interface ScoreboardManager {
    void set(Player player);
    void update(Player player);
}

