package net.stockholm.clash.game.mob.list;

import net.minecraft.world.entity.EntityType;
import net.stockholm.clash.game.mob.FightMob;
import net.stockholm.clash.game.utils.Utils;

public class Sun extends FightMob {

    public Sun() {
        super(Utils.asColor("&e⚡ &fСолнце"), EntityType.MOOSHROOM, 2310F);
    }

}
