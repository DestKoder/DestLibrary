package ru.dest.library.registry;

import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.dest.library.command.AbstractPluginCommand;
import ru.dest.library.command.RuntimePluginCommand;
import ru.dest.library.exception.RegistratorInitException;

import java.lang.reflect.Field;

public final class CommandRegistry<T extends JavaPlugin> {
    private T plugin;

    private SimpleCommandMap scm;

    public CommandRegistry(T plugin) throws RegistratorInitException {
        this.plugin = plugin;

        SimplePluginManager spm = (SimplePluginManager) plugin.getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");

            f.setAccessible(true);

            this.scm = (SimpleCommandMap) f.get(spm);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RegistratorInitException("Couldn't setup command map.", e);
        }
    }

    public void registerCommand(RuntimePluginCommand<T> cmd){
        scm.register(plugin.getName(), cmd);
    }

    public void registerCommand(AbstractPluginCommand<T> cmd){
        PluginCommand command = plugin.getCommand(cmd.getName());

        if(command == null) throw new NullPointerException("Command with given name not found");

        command.setExecutor(cmd);
        command.setTabCompleter(cmd);
    }

}
