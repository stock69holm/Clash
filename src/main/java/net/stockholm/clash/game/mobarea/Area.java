package net.stockholm.clash.game.mobarea;

import net.minecraft.world.entity.Entity;
import net.stockholm.clash.game.mob.FightMob;

import java.util.ArrayList;
import java.util.List;

public class Area {

    List<FightMob> mobsInArea = new ArrayList<>();
    Double[] start = new Double[]{-4D, 101D, -18D};
    Double[] end = new Double[]{-45D, 101D, -52D};

    public Area() {
    }

    public List<FightMob> getMobsInArea() {
        return mobsInArea;
    }

    public Double[] getStart() {
        return start;
    }

    public Double[] getEnd() {
        return end;
    }

}
