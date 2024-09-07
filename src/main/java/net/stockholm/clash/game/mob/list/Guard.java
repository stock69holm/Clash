package net.stockholm.clash.game.mob.list;

import net.minecraft.world.entity.EntityType;
import net.stockholm.clash.game.mob.FightMob;
import net.stockholm.clash.game.utils.Utils;

public class Guard extends FightMob {

    public Guard() {
        super(Utils.asColor("&6⚡ &fСтражник"), EntityType.ELDER_GUARDIAN, 120F);
    }

}
