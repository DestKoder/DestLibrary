package ru.dest.library.registry;

import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.dest.library.bukkit.BukkitPlugin;
import ru.dest.library.command.AbstractPluginCommand;
import ru.dest.library.command.CommandInfo;
import ru.dest.library.command.RuntimePluginCommand;
import ru.dest.library.exception.CommandNotFoundException;
import ru.dest.library.exception.RegistratorInitException;

import java.lang.reflect.Field;

public final class CommandRegistry<T extends JavaPlugin> {
    private BukkitPlugin<T> plugin;

    private SimpleCommandMap scm;

    public CommandRegistry(@NotNull BukkitPlugin<T> plugin) throws RegistratorInitException {
        this.plugin = plugin;

        SimplePluginManager spm = (SimplePluginManager) plugin.getServer().getPluginManager();
        Field f;
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

    public void registerCommands(RuntimePluginCommand<T>... commands){
        for (RuntimePluginCommand<T> cmd : commands) {
            this.registerCommand(cmd);
        }
    }

    public void registerCommands(AbstractPluginCommand<T>... commands) {
        for(AbstractPluginCommand<T> cmd : commands){
            this.registerCommand(cmd);
        }
    }

    public void registerCommand(@NotNull CommandInfo commandInfo){
        PluginCommand command = plugin.getCommand(commandInfo.commandName);

        if(command == null) {
            throw new CommandNotFoundException(commandInfo.commandName);
        }

        command.setExecutor(commandInfo.executor);
        if(commandInfo.completer != null) command.setTabCompleter(commandInfo.completer);
    }

    public void registerCommands(CommandInfo... commandInfos){
        if(commandInfos == null) return;

        for(CommandInfo info : commandInfos){
            this.registerCommand(info);
        }
    }

    public void registerCommand(@NotNull AbstractPluginCommand<T> cmd){
        this.registerCommand(new CommandInfo(cmd, cmd.getName(), cmd));
    }


}
