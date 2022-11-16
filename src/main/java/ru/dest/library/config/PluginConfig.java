package ru.dest.library.config;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginConfig<T extends JavaPlugin> extends Config<T> {

    public PluginConfig(T plugin) {
        super(plugin, "config");
    }
}
