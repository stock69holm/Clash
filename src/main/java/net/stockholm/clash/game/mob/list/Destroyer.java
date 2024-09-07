package net.stockholm.clash.game.mob.list;

import net.minecraft.world.entity.EntityType;
import net.stockholm.clash.game.mob.FightMob;
import net.stockholm.clash.game.utils.Utils;

public class Destroyer extends FightMob {

    public Destroyer() {
        super(Utils.asColor("&9⚡ &fРазрушитель"), EntityType.IRON_GOLEM, 310F);
    }

}
