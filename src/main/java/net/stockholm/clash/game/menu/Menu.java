package net.stockholm.clash.game.menu;

import net.stockholm.clash.game.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements MenuManager {

    private final String title;
    private final int size;
    private final Inventory inventory;
    private static final Map<String,Menu> menus = new HashMap<>();

    protected Menu(String title, int size) {
        this.title = title;
        this.size = size;
        this.inventory = Bukkit.createInventory(null,size, Utils.asColor(title));
        menus.put(title,this);
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        this.inventory.setItem(slot,item);
    }

    @Override
    public void addItem(ItemStack... items) {
        this.inventory.addItem(items);
    }

    @Override
    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public static Menu getMenu(String title) {
        return menus.get(title);
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }
}
