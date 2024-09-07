package net.stockholm.clash.game.menu.list;

import net.stockholm.clash.game.menu.Menu;
import net.stockholm.clash.game.player.Handle;
import net.stockholm.clash.game.utils.BossBarBuilder;
import net.stockholm.clash.game.utils.ItemBuilder;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Rebirth extends Menu {
    public Rebirth(Player player) {
        super("Воин", 27);

        for (int i = 0; i < 27; i++) {
            if (i != 13) {
                setItem(i, ItemBuilder.create(Material.GRAY_STAINED_GLASS_PANE, Utils.asColor("&f"), Arrays.asList("")));
            }
        }

        setItem(13, ItemBuilder.create(Material.GOLDEN_APPLE, Utils.asColor("&fНажмите, чтобы &x&8&1&A&F&F&Fпереродиться"), Arrays.asList(
                "",
                Utils.asColor(" &fСтоимость &x&8&1&A&F&F&Fперерождения:"),
                Utils.asColor(" &x&8&1&A&F&F&F" + Utils.shorten(Handle.rebirth().get(player).getCost()) + " ⭐"),
                "",
                Utils.asColor(" &fУвеличивает множитель &x&8&1&A&F&F&Fдохода"),
                Utils.asColor(" &fУвеличивает множитель &x&8&1&A&F&F&Fурона"),
                ""
        )));
    }

    @Override
    public void onClick(int slot, ItemStack item, Player player) {
        player.closeInventory();
        Double rebirthCost = Handle.rebirth().get(player).getCost();
        Double rebirthAmount = Handle.rebirth().get(player).getAmount();
        if (Handle.money().get(player) < rebirthCost) {
            BossBarBuilder.sendNotification(player, Utils.asColor("&fУ вас &x&8&1&A&F&F&Fнедостаточно средств"));
            return;
        }

        Handle.money().set(player, 0);
        Handle.strength().set(player, 0);
        Handle.rebirth().set(player, new net.stockholm.clash.game.rebirth.Rebirth(rebirthAmount + 1, rebirthCost * 8));

        BossBarBuilder.sendNotification(player, Utils.asColor("&fВы &x&8&1&A&F&F&Fпереродились"));
    }
}
