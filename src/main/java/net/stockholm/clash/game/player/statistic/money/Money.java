package net.stockholm.clash.game.player.statistic.money;

import net.stockholm.clash.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class Money implements MoneyManager {

    private Map<Player, Double> money;

    public Money(Map<Player, Double> money) {
        this.money = money;
    }

    @Override
    public Double get(Player player) {
        return money.getOrDefault(player, 0D);
    }

    @Override
    public void set(Player player, double amount) {
        money.put(player, amount);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }

    @Override
    public void add(Player player, double amount) {
        money.put(player, get(player) + amount);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }

    @Override
    public void take(Player player, double amount) {
        money.put(player, get(player) - amount);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }

}