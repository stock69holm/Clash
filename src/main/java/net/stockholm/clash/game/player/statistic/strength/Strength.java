package net.stockholm.clash.game.player.statistic.strength;

import net.stockholm.clash.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class Strength implements StrengthManager {

    private Map<Player, Double> strength;

    public Strength(Map<Player, Double> strength) {
        this.strength = strength;
    }

    @Override
    public Double get(Player player) {
        return strength.getOrDefault(player, 0D);
    }

    @Override
    public void set(Player player, double amount) {
        strength.put(player, amount);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }

    @Override
    public void add(Player player, double amount) {
        strength.put(player, get(player) + amount);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }

    @Override
    public void take(Player player, double amount) {
        strength.put(player, get(player) - amount);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }

}