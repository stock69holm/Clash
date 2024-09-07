package net.stockholm.clash.game.player.statistic.rebirth;

import net.stockholm.clash.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class Rebirth implements RebirthManager {

    private Map<Player, net.stockholm.clash.game.rebirth.Rebirth> rebirth;

    public Rebirth(Map<Player, net.stockholm.clash.game.rebirth.Rebirth> rebirth) {
        this.rebirth = rebirth;
    }

    @Override
    public net.stockholm.clash.game.rebirth.Rebirth get(Player player) {
        return rebirth.getOrDefault(player, new net.stockholm.clash.game.rebirth.Rebirth(0D, 140000D));
    }

    @Override
    public void set(Player player, net.stockholm.clash.game.rebirth.Rebirth futureRebirth) {
        rebirth.put(player, futureRebirth);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }


}