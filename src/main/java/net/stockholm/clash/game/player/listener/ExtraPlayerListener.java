package net.stockholm.clash.game.player.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.util.Vector;

public class ExtraPlayerListener implements Listener {

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        Location location = player.getLocation();

        double y = location.getY();

        if (!player.hasPermission("stockholm.upanddown")) {
            if (y >= 130) {
                player.setVelocity(new Vector(0, -1, 0));
                player.sendMessage(ChatColor.RED + "Падаем!");
            }
            if (y <= 75) {
                player.setVelocity(new Vector(0, +1, 0));
                player.sendMessage(ChatColor.RED + "Сюда нельзя..");
            }
        }

    }

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        if (!player.hasPermission("stockholm.placeblocks")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onDropItem(PlayerDropItemEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        if (!player.hasPermission("stockholm.dropitems")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onDamageItem(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onFoodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onHurt(EntityDamageEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onPickup(PlayerPickupItemEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        if (!player.hasPermission("stockholm.pickupitems")) {
            event.setCancelled(true);
        }
    }

}
