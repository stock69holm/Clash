package net.stockholm.clash.game.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.Bukkit;

public class MobBuilder {
    public static Mob create(String name, EntityType<Mob> type, Float health) {
        Mob mob = new Mob(type, Utils.asServerLevel(Bukkit.getWorld("world"))) {};

        AttributeInstance healthAttribute = mob.getAttribute(Attributes.MAX_HEALTH);
        if (healthAttribute != null) {
            healthAttribute.setBaseValue(health);
        }
        mob.setNoAi(true);
        mob.setHealth(health);
        mob.setCustomName(Component.nullToEmpty(name));
        mob.setCustomNameVisible(true);
        Utils.asServerLevel(Bukkit.getWorld("world")).addFreshEntity(mob);

        return mob;
    }
}
