package net.stockholm.clash.game.utils;

import net.stockholm.clash.game.item.weapon.Weapon;
import net.stockholm.clash.game.player.Handle;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ItemBuilder {
    public static ItemStack create(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static void giveItems(Player player) {
        PlayerInventory inventory = player.getInventory();
        inventory.clear();
        inventory.addItem(Handle.weapon().get(player).getItemStack());
    }

    public static void luckyGive(Player player) {
        Random random = new Random();
        if (random.nextInt(4) == 2) {
            Rare rare = Utils.randomRare();
            List<Weapon> weapons = Weapon.weapons;
            Collections.shuffle(weapons);
            for (Weapon weapon : weapons) {
                if (weapon.getRare().equals(rare)) {
                    if (!Utils.worse(Handle.weapon().get(player), weapon)) {
                        Handle.weapon().set(player, weapon);
                        ItemBuilder.giveItems(player);
                        player.sendActionBar(Utils.asColor("&fВам выпал &7- " + weapon.getName() + " &f⚔"));
                    }
                    break;
                }
            }
        }
    }

}
