package ru.dest.library.bukkit;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginListener<T extends JavaPlugin> implements Listener {

    protected final T plugin;

    public PluginListener(T plugin) {
        this.plugin = plugin;
    }
}
