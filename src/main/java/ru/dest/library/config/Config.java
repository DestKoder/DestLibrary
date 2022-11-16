package ru.dest.library.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Config<T extends JavaPlugin> extends YamlConfiguration {

    private final File file;
    protected final T plugin;
    private final String name;

    /**
     * Constructor
     * @param plugin - {@link T} plugin
     * @param name - name of file without '.yml'
     */
    public Config(T plugin, String name){
        this.plugin = plugin;
        this.name = name + ".yml";
        this.file = new File(plugin.getDataFolder(), this.name );

        loadData();
    }

    private void saveWithOutUpdate(){
        plugin.saveResource(name, false);
    }

    public void loadData(){
        saveWithOutUpdate();

        try {
            load(file);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().warning("Error occupied while loading file " + file.getName());
            e.printStackTrace();
        }
    }

    public void saveData(){
        try {
            save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Error occupied while saving file " + file.getName());
            e.printStackTrace();
        }
    }
}
