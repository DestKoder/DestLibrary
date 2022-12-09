package ru.dest.library.bukkit;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.dest.library.DLibrary;
import ru.dest.library.MinecraftPlugin;
import ru.dest.library.PInfo;
import ru.dest.library.exception.RegistratorInitException;
import ru.dest.library.registry.CommandRegistry;
import ru.dest.library.task.TaskManager;

/**
 * Entry point in your plugin
 * @param <T> - Your plugin class
 *
 * @since 1.0
 * @author DestKoder
 */
public class BukkitPlugin<T extends JavaPlugin> extends JavaPlugin {
    private TaskManager<T> taskManager;
    private BukkitUtils<T> utils;
    private CommandRegistry<T> commandRegister;
    protected PluginLogger logger;

    @Override
    public final void onLoad() {
        logger = new PluginLogger("&7[&b%pluginName%&7]&r", getDescription());
        this.i();

        this.onLoading();
    }

    public void onLoading(){};
    public void onEnabling() {};
    public void onDisabling() {};
    @Override
    public final void onEnable() {
        this.taskManager = new TaskManager<>(this);
        this.utils = new BukkitUtils<>(this);
        try {
            this.commandRegister = new CommandRegistry<>(this);
        } catch (RegistratorInitException e) {
            logger.warning("&cError occupied during initializing a CommandRegistry for this plugin. CommandRegistration isn't supported!");
            logger.error(e);
        }

        this.onEnabling();
    }

    @Override
    public final void onDisable() {
        taskManager.onDisable();
        HandlerList.unregisterAll(this);

        this.onDisabling();
    }

    /**
     * @return {@link TaskManager} object for your plugin;
     */
    public TaskManager<T> getTaskManager(){return taskManager;}

    /**
     * @return server {@link PluginManager}
     */
    public PluginManager getPluginManager() {return getServer().getPluginManager();}
    public BukkitUtils<T> utils() {return utils;}
    public CommandRegistry<T> commandRegistry() {return commandRegister;}

    private void i() {
        MinecraftPlugin info = getClass().getDeclaredAnnotation(MinecraftPlugin.class);

        if(info != null) {
            DLibrary.r(new PInfo(info.name(), info.version(), info.author()));
        }
    }
}
