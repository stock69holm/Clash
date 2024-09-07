package net.stockholm.clash.game.npc;

import org.bukkit.entity.Player;

public interface NpcManager {
    void create(Player player);
    void onInteract(Player player);
}
