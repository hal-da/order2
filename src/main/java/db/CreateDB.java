package db;
import java.sql.*;

public class CreateDB {
    private static final String CONNECTION_STRING = "jdbc:derby:orderDB; user=dan; password=123; create=true";
    private static final String CREATE_ORDER_TABLE = "CREATE TABLE orders (" +
            "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
            "name VARCHAR(255)," +
            "food VARCHAR(255)" +
            ")";
    private static final String ADD_ORDER = "INSERT INTO orders (name, food) VALUES (?,?)";
    private static final String DROP_ALL = "DROP TABLE orders";
    private static final String GET_ALL = "SELECT * FROM orders";


    public static void main() {
        System.out.println("db main started");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("established connection");
            Statement statement = connection.createStatement();
            statement.execute(CREATE_ORDER_TABLE);
            System.out.println("created orders");
        } catch (SQLException e) {
            System.out.println("table orders already exists");
        }

    }
}
