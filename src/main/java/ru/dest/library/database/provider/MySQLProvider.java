package ru.dest.library.database.provider;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import ru.dest.library.database.ConnectionProvider;
import ru.dest.library.database.ConnectionSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLProvider implements ConnectionProvider {

    private final String CONNECTION_URL;
    private final String userName;
    private final String password;

    private Connection connection;

    public MySQLProvider(@NotNull ConnectionSettings settings) throws ClassNotFoundException{
        Validate.notNull(settings.HOST);
        Validate.notNull(settings.DATABASE);
        Validate.notNull(settings.USERNAME);
        Validate.notNull(settings.PASSWORD);

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println(settings.PARAMS);

        this.CONNECTION_URL = "jdbc:mysql://"+settings.HOST+":"+settings.PORT+"/"+settings.DATABASE + "?" + settings.PARAMS;
        this.userName = settings.USERNAME;
        this.password = settings.PASSWORD;

        System.out.println(CONNECTION_URL);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) connection = DriverManager.getConnection(CONNECTION_URL, userName, password);

        return connection;
    }

    @Override
    public void closeConnection() throws SQLException{
        if(connection != null && !connection.isClosed()) connection.close();
    }
}
