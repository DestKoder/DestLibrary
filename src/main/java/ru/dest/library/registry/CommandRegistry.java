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

/**
 * This Class is designed to register various types of commands. <br>
 *
 * An object of this class has already been created for each BukkitPlugin, you can get it via BukkitPlugin.commandRegistry();
 * @param <T> - your main class
 *
 * @since 1.0
 * @author DestKoder
 */
public final class CommandRegistry<T extends JavaPlugin> {
    private BukkitPlugin<T> plugin;

    private SimpleCommandMap scm;

    public CommandRegistry(@NotNull final BukkitPlugin<T> plugin) throws RegistratorInitException {
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

    /**
     * Method for registering runtime commands
     * @param cmd {@link RuntimePluginCommand} to register
     */
    public void registerCommand(@NotNull final RuntimePluginCommand<T> cmd){
        scm.register(plugin.getName(), cmd);
    }

    /**
     * Method for registering several {@link RuntimePluginCommand} in one call
     * @param commands list of commands to register
     */
    public void registerCommands(RuntimePluginCommand<T> @NotNull ... commands){
        for (RuntimePluginCommand<T> cmd : commands) {
            this.registerCommand(cmd);
        }
    }


    /**
     * Method for registering plugin.yml-commands with ready functional
     * @param cmd {@link AbstractPluginCommand} to register
     */
    public void registerCommand(@NotNull final AbstractPluginCommand<T> cmd){
        this.registerCommand(new CommandInfo(cmd, cmd.getName(), cmd));
    }

    /**
     * Method for registering several plugin.yml-commands in one call
     * @param commands list of commands to register
     */
    public void registerCommands(AbstractPluginCommand<T> @NotNull ... commands) {
        for(AbstractPluginCommand<T> cmd : commands){
            this.registerCommand(cmd);
        }
    }

    /**
     * Method for registering raw plugin.yml-command, with your CommandExecutor and TabCompleter
     * @param commandInfo {@link CommandInfo} with command name, executor and tab completer;
     */
    public void registerCommand(@NotNull CommandInfo commandInfo){
        PluginCommand command = plugin.getCommand(commandInfo.commandName);

        if(command == null) {
            throw new CommandNotFoundException(commandInfo.commandName);
        }

        command.setExecutor(commandInfo.executor);
        if(commandInfo.completer != null) command.setTabCompleter(commandInfo.completer);
    }

    /**
     * Method for registering several raw plugin.yml-commands
     * @param commandInfos - list of {@link CommandInfo} with command name, executor and tab completer;
     */
    public void registerCommands(CommandInfo... commandInfos){
        if(commandInfos == null) return;

        for(CommandInfo info : commandInfos){
            this.registerCommand(info);
        }
    }


}
