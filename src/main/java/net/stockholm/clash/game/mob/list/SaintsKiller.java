package net.stockholm.clash.game.mob.list;

import net.minecraft.world.entity.EntityType;
import net.stockholm.clash.game.mob.FightMob;
import net.stockholm.clash.game.utils.Utils;

public class SaintsKiller extends FightMob {

    public SaintsKiller() {
        super(Utils.asColor("&4⚡ &fУбийца святых"), EntityType.ZOMBIFIED_PIGLIN, 45F);
    }

}
