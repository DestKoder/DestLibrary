package ru.dest.library.exception;

/**
 * This exception indicates, that command pointed in {@link ru.dest.library.command.CommandInfo} isn't exists in plugin.yml
 *
 * @since 1.0
 * @author DestKoder
 */
public class CommandNotFoundException extends RuntimeException{

    public CommandNotFoundException(String name) {
        super("Command with name " + name + " doesn't exists in plugin.yml commands section");
    }
}
