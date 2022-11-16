package ru.dest.library.registry;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import ru.dest.library.bukkit.PluginListener;

import java.lang.reflect.InvocationTargetException;

public final class ReflectionRegistry<T extends JavaPlugin> {

    private final T plugin;

    public ReflectionRegistry(T plugin){
        this.plugin = plugin;
    }

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
