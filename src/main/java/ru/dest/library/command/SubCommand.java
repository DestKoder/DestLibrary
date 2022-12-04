package ru.dest.library.command;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * This command represents a Sub command for command managers
 * @param <T> your plugin main class
 */
public abstract class SubCommand<T extends JavaPlugin>{

    protected final T plugin;

    public SubCommand(T plugin){
        this.plugin = plugin;
    }

    public abstract void execute(CommandData sender, String[] args);

    public abstract List<String> getAliases();

}
