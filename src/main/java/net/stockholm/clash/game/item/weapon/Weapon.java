package net.stockholm.clash.game.item.weapon;

import net.stockholm.clash.game.item.weapon.list.*;
import net.stockholm.clash.game.utils.ItemBuilder;
import net.stockholm.clash.game.utils.Rare;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class Weapon {

    public static List<Weapon> weapons = Arrays.asList(
            new Broken(),
            new Fired(),
            new Hammer(),
            new Scythe(),
            new Staff(),
            new Wind()
    );

    String name;
    Double damage;
    Rare rare;

    public Weapon(String name, Double damage, Rare rare) {
        this.name = name;
        this.damage = damage;
        this.rare = rare;
    }

    public String getName() {
        return name;
    }

    public Double getDamage() {
        return damage;
    }

    public Rare getRare() {
        return rare;
    }

    public ItemStack getItemStack() {
        return ItemBuilder.create(Material.IRON_SWORD, Utils.asColor("&x&8&1&A&F&F&F" + name),
                List.of(
                        "",
                        Utils.asColor(" &7+&x&8&1&A&F&F&F" + damage + " &7к урону"),
                        Utils.asColor(" &7Имеет редкость: &x&8&1&A&F&F&F" + Utils.rare(rare)),
                        ""
                ));
    }

}
