package net.stockholm.clash.game.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface MenuManager {
    void open(Player player);
    void setItem(int slot, ItemStack item);
    void addItem(ItemStack... items);
    void onClick(int slot, ItemStack item, Player player);
}
