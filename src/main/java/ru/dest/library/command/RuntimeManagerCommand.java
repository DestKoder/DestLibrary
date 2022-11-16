package ru.dest.library.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class RuntimeManagerCommand<T extends JavaPlugin> extends RuntimePluginCommand<T> {

    private final List<SubCommand<T>> subCommands;
    private String[] helpMessage = null;
    private String msgSubCommandNotExists = null;

    public RuntimeManagerCommand(T plugin, String name) {
        super(plugin, name);
        subCommands = new ArrayList<>();
    }

    public RuntimeManagerCommand(T plugin, String name, String description, String usageMessage, String[] aliases) {
        super(plugin, name, description, usageMessage, aliases);
        subCommands = new ArrayList<>();
    }

    @Override
    public final void execute(CommandData data, String[] args) {
        if((args.length < 1 || args[0].equalsIgnoreCase("help"))){
            if(helpMessage != null) data.getSender().sendMessage(helpMessage);
            return;
        }

        SubCommand<T> sub = getSubCommand(args[0]);

        if(sub == null) {
            if(msgSubCommandNotExists != null) data.getSender().sendMessage(msgSubCommandNotExists);
            else if(helpMessage != null) data.getSender().sendMessage(helpMessage);
            return;
        }

        String[] arguments = new String[args.length-1];

        System.arraycopy(args, 1, arguments, 0, args.length - 1);

        sub.execute(data, arguments);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        List<String> toReturn = new ArrayList<>();

        if(args.length == 1){
            for(SubCommand<T> cmd : subCommands){
                toReturn.addAll(cmd.getAliases());
            }
            return toReturn;
        }

        SubCommand<T> sub = getSubCommand(args[0]);
        if(sub instanceof TabCompleter){
            String[] arguments = new String[args.length-1];

            System.arraycopy(args, 1, arguments, 0, args.length - 1);

            return ((TabCompleter)sub).onTabComplete(sender, this, alias, arguments);
        }

        return toReturn;
    }

    protected void addSubCommand(SubCommand<T> command){
        this.subCommands.add(command);
    }

    public void setHelp(String[] help){
        this.helpMessage = help;
    }

    public void setSubCommandNotExistsMessage(String message){
        this.msgSubCommandNotExists = message;
    }

    private SubCommand<T> getSubCommand(String name) {
        for(SubCommand<T> cmd : subCommands){
            if(cmd.getAliases().contains(name)) return cmd;
        }
        return null;
    }

}
