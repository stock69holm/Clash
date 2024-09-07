package net.stockholm.clash.game.utils;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.stockholm.clash.Main;
import net.stockholm.clash.game.item.weapon.Weapon;
import net.stockholm.clash.game.player.Handle;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.*;

public class Utils {

    public static Location lookAt(Location loc, Location lookat) {
        loc = loc.clone();
        double dx = lookat.getX() - loc.getX();
        double dy = lookat.getY() - loc.getY();
        double dz = lookat.getZ() - loc.getZ();
        if (dx != 0) {
            if (dx < 0) {
                loc.setYaw((float) (1.5 * Math.PI));
            } else {
                loc.setYaw((float) (0.5 * Math.PI));
            }
            loc.setYaw(loc.getYaw() - (float) Math.atan(dz / dx));
        } else if (dz < 0) {
            loc.setYaw((float) Math.PI);
        }
        double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));
        loc.setPitch((float) -Math.atan(dy / dxz));
        loc.setYaw(-loc.getYaw() * 180f / (float) Math.PI);
        loc.setPitch(loc.getPitch() * 180f / (float) Math.PI);
        return loc;
    }

    public static String rare(Rare rare) {
        switch (rare) {
            case LOWEST:
                return "Очень низкий";
            case RARE:
                return "Редкий";
            case EPIC:
                return "Эпический";
            case LEGENDRAY:
                return "Легендарный";
            case MYTHICAL:
                return "Мифический";
        }
        return null;
    }

    public static Rare randomRare() {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 50) {
            return Rare.LOWEST;
        } else if (chance < 80) {
            return Rare.RARE;
        } else if (chance < 90) {
            return Rare.EPIC;
        } else if (chance < 98) {
            return Rare.LEGENDRAY;
        } else {
            return Rare.MYTHICAL;
        }
    }

    public static Boolean worse(Weapon old, Weapon neW) {
        if (old.getDamage() < neW.getDamage()) {
            return false;
        } else {
            return true;
        }
    }

    public static Double calculate(Player player, Double multiplier, Double amount) {
        Random random = new Random();
        return multiplier * amount * random.nextInt(2, 5) * (Handle.strength().get(player) / 10) * (Handle.rebirth().get(player).getAmount() + 1);
    }

    public static String shorten(Double number) {
        String[] suffixes = {"", "тыс", "млн", "млрд", "трлн", "квдрлн", "квнтлн", "скстлн", "сптлн", "октлн", "нинлн", "дцлн", "ундцлн", "дуодцлн", "тредцлн", "кваутрдцлн", "квндцлн", "сксдцлн", "сптндцлн", "октдцлн", "нвмдцлн"};
        int thousand = 1000;
        int magnitude = 0;
        while (number >= thousand && magnitude < suffixes.length - 1) {
            number /= thousand;
            magnitude++;
        }
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(number) + suffixes[magnitude];
    }

    public static boolean isNpc(org.bukkit.entity.Entity entity) {
        return !((CraftServer) Bukkit.getServer()).getServer().getPlayerList().players.contains(((CraftPlayer) entity).getHandle());
    }

    public static ServerPlayer asServerPlayer(Entity entity) {
        return ((CraftPlayer) entity).getHandle();
    }

    public static ServerLevel asServerLevel(World world) {
        return ((CraftWorld) world).getHandle();
    }

    public static String asColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
