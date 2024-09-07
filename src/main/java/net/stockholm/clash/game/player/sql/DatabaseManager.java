package net.stockholm.clash.game.player.sql;

import org.bukkit.entity.Player;

public interface DatabaseManager {
    void save(Player player);
    void load(Player player);
}
