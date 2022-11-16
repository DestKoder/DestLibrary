package ru.dest.library.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class RuntimePluginCommand<T extends JavaPlugin> extends Command implements PluginIdentifiableCommand {
    protected final T plugin;

    public RuntimePluginCommand(T plugin, String name) {
        super(name);
        this.plugin = plugin;
    }

    public RuntimePluginCommand(T plugin, String name, String description, String usageMessage, String[] aliases) {
        super(name, description, usageMessage, Arrays.asList(aliases));
        this.plugin = plugin;
        this.setAliases(Arrays.asList(aliases));
    }

    public abstract void execute(CommandData data, String[] arguments);

    @Override
    public final boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        int counter = 0;

        Map<String,String> flags = new HashMap<>();

        for(String arg : args){
            if(!arg.startsWith("-")) break;
            if(!arg.contains("=")){
                flags.put(arg.substring(1), "true");
                counter++;
                continue;
            }

            String[] data = arg.substring(1).split("=");

            flags.put(data[0], data[1]);
            counter++;
        }

        String[] arguments = new String[args.length - counter];

        if (args.length - counter >= 0) System.arraycopy(args, counter, arguments, 0, args.length - counter);

        this.execute(new CommandData(sender, commandLabel, flags), arguments);

        return true;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
