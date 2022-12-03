package ru.dest.library.task;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;
import ru.dest.library.bukkit.BukkitPlugin;

import java.util.ArrayList;
import java.util.List;

public class TaskManager<T extends JavaPlugin> {

    private final BukkitPlugin<T> plugin;

    private final List<BukkitTask> tasks = new ArrayList<>();
    private final List<InfinityPotion> potionTasks = new ArrayList<>();

    public TaskManager(BukkitPlugin<T> plugin) {
        this.plugin = plugin;
    }


    public void runTask(BukkitRunnable runnable, boolean async){
        if(async) runnable.runTaskAsynchronously(plugin);
        else runnable.runTask(plugin);
    }

    public void runTaskLater(BukkitRunnable runnable, boolean async, int time){
        if(async) {
            this.tasks.add(runnable.runTaskLaterAsynchronously(plugin, time));
        }else {
            this.tasks.add(runnable.runTaskLater(plugin, time));
        }
    }

    public void runRepeatingTask(BukkitRunnable runnable, boolean async, int time){
        if(async) this.tasks.add(runnable.runTaskTimerAsynchronously(plugin, 0, time));
        this.tasks.add(runnable.runTaskTimer(plugin, 0, time));
    }

    public void registerPotionTask(InfinityPotion potion){
        this.potionTasks.add(potion);
        this.runRepeatingTask(potion, true, 20);
    }

    @Nullable
    public InfinityPotion getPotionTask(PotionEffectType type){
        for(InfinityPotion potion : potionTasks){
            if(potion.potion.equals(type)) return potion;
        }
        return null;
    }

    @Nullable
    public InfinityPotion getPotionTask(PotionEffectType type, int level){
        for(InfinityPotion potion : potionTasks){
            if(potion.potion.equals(type) && potion.level == level) return potion;
        }
        return null;
    }

    public void onDisable(){
        tasks.forEach(task -> {
            if(!task.isCancelled()) task.cancel();
        });

        this.tasks.clear();
    }
}
