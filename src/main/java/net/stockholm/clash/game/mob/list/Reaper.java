package net.stockholm.clash.game.mob.list;

import net.minecraft.world.entity.EntityType;
import net.stockholm.clash.game.mob.FightMob;
import net.stockholm.clash.game.utils.Utils;

public class Reaper extends FightMob {

    public Reaper() {
        super(Utils.asColor("&c⚡ &fЖнец"), EntityType.WITHER_SKELETON, 20F);
    }

}
