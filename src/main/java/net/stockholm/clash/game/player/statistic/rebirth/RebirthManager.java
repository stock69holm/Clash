package net.stockholm.clash.game.player.statistic.rebirth;

import net.stockholm.clash.game.rebirth.Rebirth;
import org.bukkit.entity.Player;

public interface RebirthManager {
    net.stockholm.clash.game.rebirth.Rebirth get(Player player);
    void set(Player player, Rebirth rebirth);
}
