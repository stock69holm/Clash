package net.stockholm.clash.game.player.scoreboard;

import net.stockholm.clash.game.player.Handle;
import net.stockholm.clash.game.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class Scoreboard implements ScoreboardManager {

    @Override
    public void set(Player player) {

        org.bukkit.scoreboard.Scoreboard scoreboard = createScoreboard(Utils.asColor("     &x&8&1&A&F&F&F&lClash     "));
        setLine(scoreboard, " &1", 9);
        setLine(scoreboard, " &fБаланс: &7" + Utils.shorten(Handle.money().get(player)) + " ⭐", 8);
        setLine(scoreboard, " &fСила: &7" + Utils.shorten(Handle.strength().get(player)) + " ⚡", 7);
        setLine(scoreboard, " &2", 6);
        setLine(scoreboard, " &fТекущее оружие: &7" + Handle.weapon().get(player).getName() + " ⚔", 5);
        setLine(scoreboard, " &3", 4);

        player.setScoreboard(scoreboard);

    }

    @Override
    public void update(Player player) {
        set(player);
    }

    private void setLine(org.bukkit.scoreboard.Scoreboard scoreboard, String text, int slot) {
        for (Objective objective : scoreboard.getObjectives()) {
            Score line = objective.getScore(Utils.asColor(text));
            line.setScore(slot);
        }
    }

    private org.bukkit.scoreboard.Scoreboard createScoreboard(String title) {
        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("board", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Utils.asColor(title));
        return scoreboard;
    }

}
