package driver;

import java.sql.*;

public class Query implements Runnable {

    private String query;

    private Connection connection;

    public Query() {
        this.connection = null;
        this.query = "";
    }

    public Query(Connection connection) {
        this.connection = connection;
        this.query = "";
    }

    public Query(String query) {
        this.connection = null;
        this.query = query;
    }

    Query(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    public Query(Query q) {
        this.connection = q.connection;
        this.query = q.query;
    }

    @Override
    public void run() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        System.out.println(currentTime);

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                System.out.println(result.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return query;
    }
}
