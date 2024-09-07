package net.stockholm.clash.game.npc.list;

import net.stockholm.clash.Main;
import net.stockholm.clash.game.npc.Npc;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.entity.Player;

public class Reincarnator extends Npc {
    public Reincarnator() {
        super(Utils.asColor("&7Реинкарнатор"), "Gargofield", Main.getInstance().getArea().getEnd());
    }

    @Override
    public void onInteract(Player player) {
        new net.stockholm.clash.game.menu.list.Rebirth(player).open(player);
    }
}
