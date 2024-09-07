package net.stockholm.clash.game.npc.list;

import net.stockholm.clash.Main;
import net.stockholm.clash.game.npc.Npc;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.entity.Player;

public class Warrior extends Npc {
    public Warrior() {
        super(Utils.asColor("&7Воин"), "_Winnd", Main.getInstance().getArea().getStart());
    }

    @Override
    public void onInteract(Player player) {
        new net.stockholm.clash.game.menu.list.Warrior().open(player);
    }
}
