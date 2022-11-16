package ru.dest.library.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;

public class DatabaseWorker {

    protected final ConnectionProvider provider;

    public DatabaseWorker(ConnectionProvider provider) {
        this.provider = provider;
    }

    public int update(String query,  Consumer<PreparedStatement> consumer) throws SQLException {
        int result;

        PreparedStatement stmt = provider.getConnection().prepareStatement(query);

        consumer.accept(stmt);

        result = stmt.executeUpdate();

        stmt.close();

        return result;
    }

    public void select() {

    }
}
