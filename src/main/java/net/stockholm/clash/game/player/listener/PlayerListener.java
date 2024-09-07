package net.stockholm.clash.game.player.listener;

import net.stockholm.clash.Main;
import net.stockholm.clash.game.npc.Npc;
import net.stockholm.clash.game.player.Handle;
import net.stockholm.clash.game.utils.BossBarBuilder;
import net.stockholm.clash.game.utils.ItemBuilder;
import net.stockholm.clash.game.utils.NpcBuilder;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements org.bukkit.event.Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Main.getInstance().getDatabase().load(player);
        Main.getInstance().getScoreboard().set(player);
        ItemBuilder.giveItems(player);
        for (Npc npc : Npc.npcList) {
            npc.create(player);
        }
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Double strength = (Handle.weapon().get(player).getDamage() / 2) * (Handle.rebirth().get(player).getAmount() + 1);
            player.sendActionBar(Utils.asColor("&7+" + Utils.shorten(strength) + " &x&8&1&A&F&F&F⚡"));
            Handle.strength().add(player, strength);
        }
    }

    @EventHandler
    private void onDeath(EntityDamageByEntityEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();
        Entity killer = event.getDamager();
        if (killer instanceof Player && entity.getCustomName() != null) {

            event.setCancelled(true);

            Player player = (Player) killer;

            entity.damage((Handle.weapon().get(player).getDamage()) * (Handle.rebirth().get(player).getAmount() + 1));

            BossBarBuilder.sendNotification(player, Utils.asColor("&fУ моба &7\"" + entity.getCustomName() + "&7\" &fосталось &x&8&1&A&F&F&F" + Utils.shorten(entity.getHealth()) + " &fхп"));

            Double maxHealth = entity.getMaxHealth();

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (entity.isDead()) {
                        Double loot;
                        loot = Utils.calculate(player, maxHealth / 50, 100D);
                        BossBarBuilder.sendNotification(player, Utils.asColor("&fВы &x&8&1&A&F&F&Fубили &fмоба &7\"" + entity.getCustomName() + "&7\" &fваша награда &x&8&1&A&F&F&F" + Utils.shorten(loot) + " ⭐"));
                        Handle.money().add(player, loot);

                        ItemBuilder.luckyGive(player);
                    }
                }
            }.runTaskLater(Main.getInstance(), 0L);
        }
    }

}
