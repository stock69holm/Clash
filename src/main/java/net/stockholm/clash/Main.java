package net.stockholm.clash;

import net.stockholm.clash.game.listener.ExtraGameListener;
import net.stockholm.clash.game.menu.MenuListener;
import net.stockholm.clash.game.npc.NpcListener;
import net.stockholm.clash.game.player.listener.ExtraPlayerListener;
import net.stockholm.clash.game.player.listener.PlayerListener;
import net.stockholm.clash.game.mobarea.Area;
import net.stockholm.clash.game.player.scoreboard.Scoreboard;
import net.stockholm.clash.game.player.sql.Database;
import net.stockholm.clash.game.utils.MobSpawner;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Area area;
    private Database database;
    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        instance = this;
        area = new Area();
        database = new Database();
        scoreboard = new Scoreboard();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ExtraPlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ExtraGameListener(), this);
        getServer().getPluginManager().registerEvents(new NpcListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        MobSpawner.run();

    }

    @Override
    public void onDisable() {
        MobSpawner.killAll();
    }

    public static Main getInstance() {
        return instance;
    }

    public Area getArea() {
        return area;
    }

    public Database getDatabase() {
        return database;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

}
