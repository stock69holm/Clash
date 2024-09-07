package net.stockholm.clash.game.npc;

import net.stockholm.clash.game.npc.list.Reincarnator;
import net.stockholm.clash.game.npc.list.Warrior;
import net.stockholm.clash.game.utils.NpcBuilder;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Npc implements NpcManager {

    private final String name;
    private final String skinName;
    private final Double[] xyz;
    private static final Map<String, Npc> npcs = new HashMap<>();

    public static List<Npc> npcList = Arrays.asList(
            new Warrior(),
            new Reincarnator()
    );

    protected Npc(String name, String skinName, Double[] xyz) {
        this.name = name;
        this.skinName = skinName;
        this.xyz = xyz;
        npcs.put(name, this);
    }

    @Override
    public void create(Player player) {
        NpcBuilder.create(player, name, xyz[0], xyz[1], xyz[2], skinName);
    }

    public static Npc getNpc(String name) {
        return npcs.get(name);
    }

    public String getName() {
        return name;
    }

}
