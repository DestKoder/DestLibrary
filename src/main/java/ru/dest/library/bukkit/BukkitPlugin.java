package ru.dest.library.bukkit;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.dest.library.exception.RegistratorInitException;
import ru.dest.library.registry.CommandRegistry;
import ru.dest.library.task.TaskManager;

public class BukkitPlugin<T extends JavaPlugin> extends JavaPlugin {
    private TaskManager<T> taskManager;
    private BukkitUtils<T> utils;
    private CommandRegistry<T> commandRegister;
    protected PluginLogger logger;

    @Override
    public void onLoad() {
        logger = new PluginLogger("&7[&b%pluginName%&7]&r", getDescription());
    }

    @Override
    public void onEnable() {
        this.taskManager = new TaskManager<>(this);
        this.utils = new BukkitUtils<>(this);
        try {
            this.commandRegister = new CommandRegistry<>(this);
        } catch (RegistratorInitException e) {
            logger.warning("&cError occupied during initializing a CommandRegistry for this plugin. CommandRegistration isn't supported!");
            logger.error(e);
        }
    }

    @Override
    public void onDisable() {
        taskManager.onDisable();

        HandlerList.unregisterAll(this);
    }

    public TaskManager<T> getTaskManager(){return taskManager;}
    public PluginManager getPluginManager() {return getServer().getPluginManager();}
    public BukkitUtils<T> utils() {return utils;}
    public CommandRegistry<T> commandRegistry() {return commandRegister;}
}
