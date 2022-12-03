package ru.dest.library.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class provide information for commands in plugin.yml commands section
 *
 * @author DestKoder
 * @since 1.1
 */
public class CommandInfo {

    @NotNull
    public final CommandExecutor executor;
    @NotNull
    public final String commandName;

    @Nullable
    public final TabCompleter completer;

    public CommandInfo(@NotNull CommandExecutor executor,@NotNull String commandName) {
        this.executor = executor;
        this.commandName = commandName;
        this.completer = null;
    }

    public CommandInfo(@NotNull CommandExecutor executor,@NotNull String commandName,@NotNull TabCompleter completer) {
        this.executor = executor;
        this.commandName = commandName;
        this.completer = completer;
    }
}
