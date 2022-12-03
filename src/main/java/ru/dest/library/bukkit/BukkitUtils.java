package ru.dest.library.bukkit;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitUtils<T extends JavaPlugin> {

    private final BukkitPlugin<T> plugin;

    public BukkitUtils(BukkitPlugin<T> plugin) {
        this.plugin = plugin;
    }

    public void registerHandlers(PluginListener<T>... handlers){
        for(PluginListener<T> listener : handlers) plugin.getPluginManager().registerEvents(listener, plugin);
    }

    public void registerHandlers(Listener... handlers){
        for(Listener listener : handlers)plugin.getPluginManager().registerEvents(listener, plugin);
    }

    public void callEvent(Event event){
        plugin.getPluginManager().callEvent(event);
    }

    public void registerPermissions(Permission... permissions){
        for(Permission perm : permissions) plugin.getPluginManager().addPermission(perm);
    }
}
