package ru.dest.library.database;

import ru.dest.library.exception.UnsupportedDatabaseTypeException;

import java.lang.reflect.InvocationTargetException;

public enum ConnectionProviderType {

    MYSQL("ru.dest.library.database.provider.MySQLProvider");

    private final String provider;

    ConnectionProviderType(String provider) {
        this.provider = provider;
    }

    public ConnectionProvider getProvider(ConnectionSettings settings) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (ConnectionProvider) Class.forName(provider).getDeclaredConstructor(ConnectionSettings.class).newInstance(settings);
    }

    public static ConnectionProviderType forType(String type) throws UnsupportedDatabaseTypeException {
        for(ConnectionProviderType connType : values()){
            if(type.equalsIgnoreCase(connType.name())) return connType;
        }

        throw new UnsupportedDatabaseTypeException(type);
    }
}
