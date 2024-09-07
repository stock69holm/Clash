package net.stockholm.clash.game.utils;

import net.stockholm.clash.Main;
import net.stockholm.clash.game.mob.FightMob;
import net.stockholm.clash.game.mobarea.Area;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MobSpawner {
    public static void spawnRandomMob() {
        Random random = new Random();

        Area area = Main.getInstance().getArea();
        Double[] start = area.getStart();
        Double[] end = area.getEnd();

        int x = random.nextInt(end[0].intValue(), start[0].intValue());
        int y = start[1].intValue();
        int z = random.nextInt(end[2].intValue(), start[2].intValue());

        List<FightMob> fightMobs = FightMob.fightMobs;
        Collections.shuffle(fightMobs);
        FightMob fightMob = fightMobs.get(0);
        fightMob.getMob().setPos(x,y,z);

        Main.getInstance().getArea().getMobsInArea().add(fightMob);

    }

    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Main.getInstance().getArea().getMobsInArea().size() < 10) {
                    spawnRandomMob();
                }
            }
        }.runTaskTimer(Main.getInstance(), 20L * 5L, 20L * 5L);
    }

    public static void killAll() {
        for (Entity entity : Bukkit.getWorld("world").getEntities()) {
            if (!(entity instanceof Player)) {
                entity.remove();
            }
        }
    }

}
