package net.stockholm.clash.game.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.stockholm.clash.game.mob.list.*;
import net.stockholm.clash.game.utils.MobBuilder;

import java.util.Arrays;
import java.util.List;

public abstract class FightMob implements FightMobManager {

    public static List<FightMob> fightMobs = Arrays.asList(
            new Reaper(),
            new SaintsKiller(),
            new Guard(),
            new Destroyer(),
            new Magician(),
            new Sun()
    );

    String name;
    EntityType type;
    Float health;

    public FightMob(String name, EntityType type, Float health) {
        this.name = name;
        this.type = type;
        this.health = health;
    }

    public Mob getMob() {
        return MobBuilder.create(name, type, health);
    }

}
