package net.stockholm.clash.game.player;

import net.stockholm.clash.game.item.weapon.Weapon;
import net.stockholm.clash.game.player.statistic.money.Money;
import net.stockholm.clash.game.player.statistic.rebirth.Rebirth;
import net.stockholm.clash.game.player.statistic.strength.Strength;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Handle {

    private static final Map<Player, Double> money = new HashMap<>();
    private static final Map<Player, Double> strength = new HashMap<>();
    private static final Map<Player, Weapon> weapon = new HashMap<>();
    private static final Map<Player, net.stockholm.clash.game.rebirth.Rebirth> rebirth = new HashMap<>();

    public static Money money() {
        return new Money(money);
    }

    public static Strength strength() {
        return new Strength(strength);
    }

    public static net.stockholm.clash.game.player.statistic.sword.Weapon weapon() {
        return new net.stockholm.clash.game.player.statistic.sword.Weapon(weapon);
    }

    public static Rebirth rebirth() {
        return new Rebirth(rebirth);
    }

}
