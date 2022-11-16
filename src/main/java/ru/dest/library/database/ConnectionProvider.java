package ru.dest.library.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {

    Connection getConnection() throws SQLException;

    void closeConnection() throws SQLException;

}
