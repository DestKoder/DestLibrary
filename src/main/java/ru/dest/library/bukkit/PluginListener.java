package ru.dest.library.bukkit;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents a listener which hold Plugin
 * @param <T> your plugin main class
 *
 * @since 0.6
 * @author DestKoder
 */
public class PluginListener<T extends JavaPlugin> implements Listener {

    protected final T plugin;

    public PluginListener(T plugin) {
        this.plugin = plugin;
    }
}
