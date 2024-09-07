package net.stockholm.clash.game.player.statistic.strength;

import org.bukkit.entity.Player;

public interface StrengthManager {
    Double get(Player player);
    void set(Player player, double amount);
    void add(Player player, double amount);
    void take(Player player, double amount);
}
