package net.stockholm.clash.game.menu.list;

import net.stockholm.clash.game.menu.Menu;
import net.stockholm.clash.game.utils.ItemBuilder;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Warrior extends Menu {
    public Warrior() {
        super("Воин", 54);

        for (int i = 0; i < 54; i++) {
            if (i != 0) {
                setItem(i, ItemBuilder.create(Material.GRAY_STAINED_GLASS_PANE, Utils.asColor("&f"), Arrays.asList("")));
            }
        }

        setItem(0, ItemBuilder.create(Material.PAPER, Utils.asColor("&fГде я нахожусь?"), Arrays.asList(
                Utils.asColor(" &7Вы попали на поле боя!"),
                Utils.asColor(" &7убивайте врагов и получайте"),
                Utils.asColor(" &7за них справедливую плату."),
                Utils.asColor(""),
                Utils.asColor(" &7С мобов выпадают различные,"),
                Utils.asColor(" &7виды холодного оружия."),
                Utils.asColor(""),
                Utils.asColor(" &7Тренируйте свою силу"),
                Utils.asColor(" &7ведь от неё зависит доход с мобов.")
        )));
    }

    @Override
    public void onClick(int slot, ItemStack item, Player player) {

    }
}
