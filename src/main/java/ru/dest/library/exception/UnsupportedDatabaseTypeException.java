package ru.dest.library.exception;

public class UnsupportedDatabaseTypeException extends Exception{

    public UnsupportedDatabaseTypeException(String type) {
        super("Database type " + type + " doesn't supported");
    }
}
