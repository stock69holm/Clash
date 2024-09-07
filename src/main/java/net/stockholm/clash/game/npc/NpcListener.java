package net.stockholm.clash.game.npc;

import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;

public class NpcListener implements org.bukkit.event.Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            Player player = event.getPlayer();
            Entity entity = event.getRightClicked();

            if (!(entity instanceof Player)) {
                return;
            }

            if (!Utils.isNpc(entity)) {
                return;
            }

            String npcName = Utils.asServerPlayer(entity).getName().getString();

            Npc npcManager = Npc.getNpc(npcName);
            if (npcManager == null) {
                return;
            }

            npcManager.onInteract(player);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        for (Entity entity : player.getNearbyEntities(150,150,150)) {

            if (entity instanceof Player) {
                ServerPlayer ePlayer = Utils.asServerPlayer(player);
                ServerPlayer npc = Utils.asServerPlayer(entity);

                Location rotated = Utils.lookAt(entity.getLocation(), player.getLocation());
                float yaw = rotated.getYaw();

                if (Utils.isNpc(entity)) {
                    ePlayer.connection.send(new ClientboundMoveEntityPacket.Rot(npc.getId(), (byte) ((yaw % 360) * 256 / 360), (byte) 0, false));
                    ePlayer.connection.send(new ClientboundRotateHeadPacket(npc, (byte) ((yaw % 360) * 256 / 360)));
                }
            }
        }
    }

}
