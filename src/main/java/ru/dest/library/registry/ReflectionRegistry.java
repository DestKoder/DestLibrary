package ru.dest.library.registry;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import ru.dest.library.bukkit.BukkitPlugin;
import ru.dest.library.bukkit.PluginListener;
import ru.dest.library.command.RuntimePluginCommand;

import java.lang.reflect.InvocationTargetException;

/**
 * This class is designed for multi-register handlers from package
 *
 * @param <T> - your main class
 *
 * @since 1.0
 * @author DestKoder
 */
public final class ReflectionRegistry<T extends JavaPlugin> {

    private final BukkitPlugin<T> plugin;

    public ReflectionRegistry(BukkitPlugin<T> plugin){
        this.plugin = plugin;
    }

    /**
     * Register all listeners from package;
     * @param subPackage package with holds {@link PluginListener}s
     */
    public void registerListeners(String subPackage) {
        new Reflections(plugin.getClass().getPackage().getName() + "."+subPackage).getSubTypesOf(PluginListener.class).forEach(clazz -> {
            try {
                plugin.getServer().getPluginManager().registerEvents(clazz.getDeclaredConstructor(plugin.getClass()).newInstance(plugin), plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
