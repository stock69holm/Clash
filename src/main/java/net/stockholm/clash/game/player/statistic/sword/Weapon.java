package net.stockholm.clash.game.player.statistic.sword;

import net.stockholm.clash.Main;
import net.stockholm.clash.game.item.weapon.list.Broken;
import org.bukkit.entity.Player;

import java.util.Map;

public class Weapon implements WeaponManager {

    private Map<Player, net.stockholm.clash.game.item.weapon.Weapon> sword;

    public Weapon(Map<Player, net.stockholm.clash.game.item.weapon.Weapon> sword) {
        this.sword = sword;
    }

    @Override
    public net.stockholm.clash.game.item.weapon.Weapon get(Player player) {
        return sword.getOrDefault(player, new Broken());
    }

    @Override
    public void set(Player player, net.stockholm.clash.game.item.weapon.Weapon futureWeapon) {
        sword.put(player, futureWeapon);
        Main.getInstance().getScoreboard().update(player);
        Main.getInstance().getDatabase().save(player);
    }

}