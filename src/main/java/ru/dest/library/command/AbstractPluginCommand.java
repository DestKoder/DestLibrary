package ru.dest.library.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPluginCommand<T extends JavaPlugin> implements CommandExecutor, TabCompleter {

    protected final T plugin;
    protected final String name;

    public AbstractPluginCommand(T plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    /**
     * Method executing when Player or Console perform command
     * @param data - {@link CommandData}
     * @param args - command arguments
     * @return true if command complete or false if not
     */
    public abstract void execute(CommandData data, String[] args);

    /**
     * Tab completer for command
     * @param sender - {@link CommandSender}
     * @param cmd - executed {@link Command}
     * @param alias - Alias of command
     * @param args - entered arguments
     * @return {@link List<String>} completiongs for command
     */
    public abstract List<String> tabComplete(CommandSender sender, Command cmd, String alias, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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

        this.execute(new CommandData(sender, label, flags), arguments);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return tabComplete(sender, command, alias, args);
    }

    public String getName() {
        return name;
    }
}
