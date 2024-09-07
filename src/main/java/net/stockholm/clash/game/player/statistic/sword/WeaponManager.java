package net.stockholm.clash.game.player.statistic.sword;

import net.stockholm.clash.game.item.weapon.Weapon;
import org.bukkit.entity.Player;

public interface WeaponManager {
    Weapon get(Player player);
    void set(Player player, Weapon futureWeapon);
}
