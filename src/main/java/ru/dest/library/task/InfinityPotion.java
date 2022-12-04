package ru.dest.library.task;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Task class for Infinity potion effect
 *
 * @since 1.0
 * @author DestKoder
 */
public class InfinityPotion extends BukkitRunnable {

    protected PotionEffectType potion;
    protected List<Player> players;
    protected int level;

    public InfinityPotion(Plugin plugin, PotionEffectType potion) {
        this.potion = potion;
        this.level = 1;
        players = new ArrayList<>();

        this.runTaskTimerAsynchronously(plugin, 0, 20);
    }

    public InfinityPotion(Plugin plugin, PotionEffectType potion, int level) {
        this.potion = potion;
        this.level = level;
        players = new ArrayList<>();
    }

    /**
     * Add a player to this task
     * @param player - {@link Player} to add
     */
    public void addPlayer(Player player){
        this.players.add(player);
    }

    /**
     * Remove the player from this task
     * @param player - {@link Player} to remove;
     */
    public void removePlayer(Player player){
        this.players.remove(player);
    }

    @Override
    public void run() {
        for(Player p : players){
            p.addPotionEffect(new PotionEffect(potion, 60, level), true);
        }
    }
}
