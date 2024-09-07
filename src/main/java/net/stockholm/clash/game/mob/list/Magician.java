package net.stockholm.clash.game.mob.list;

import net.minecraft.world.entity.EntityType;
import net.stockholm.clash.game.mob.FightMob;
import net.stockholm.clash.game.utils.Utils;

public class Magician extends FightMob {

    public Magician() {
        super(Utils.asColor("&5⚡ &fМаг"), EntityType.WITCH, 999F);
    }

}
