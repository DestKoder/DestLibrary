package ru.dest.library.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CommandData {

    private CommandSender sender;
    private String commandLabel;

    private Map<String, String> commandFlags;

    public CommandData(CommandSender sender, String commandLabel, Map<String, String> commandFlags) {
        this.sender = sender;
        this.commandLabel = commandLabel;
        this.commandFlags = commandFlags;
    }

    /**
     * Check's who perform command, Player or Console
     */
    public boolean isSenderPlayer() {return sender instanceof Player;}

    /**
     * <p>
     *     Return {@link CommandSender} as a {@link Player}.
     * </p>
     * @return {@link Player} if sender is Player or null if not
     */
    @Nullable
    public Player getSenderAsPlayer() {
        if(!isSenderPlayer()) return null;
        return (Player) sender;
    }

    /**
     * Get command sender
     * @return {@link CommandSender}, who perform command
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     * @return Alias of performed command
     */
    @NotNull
    public String getCommandLabel() {
        return commandLabel;
    }

    /**
     * Check's has flag with given name in command args
     * @param flagName - name of the flag
     * @return true if has or false if not
     */
    public boolean hasFlag(@NotNull String flagName){
        return commandFlags.containsKey(flagName);
    }

    /**
     * Get the value of command flag
     * @param flagName name of the flag
     * @return if flag with name found - value of flag another way - null
     */
    @Nullable
    public String getFlagValue(@NotNull String flagName){
        return commandFlags.getOrDefault(flagName, null);
    }
}
