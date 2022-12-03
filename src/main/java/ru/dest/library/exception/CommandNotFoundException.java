package ru.dest.library.exception;

public class CommandNotFoundException extends RuntimeException{

    public CommandNotFoundException(String name) {
        super("Command with name " + name + " doesn't exists in plugin.yml commands section");
    }
}
