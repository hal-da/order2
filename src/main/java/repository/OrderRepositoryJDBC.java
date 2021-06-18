package repository;

import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryJDBC implements OrderRepository{
    private static final String CONNECTION_STRING = "jdbc:derby:orderDB; user=dan; password=123; create=true";
    private static final String ADD_ORDER = "INSERT INTO orders (name, food) VALUES (?,?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET name=?,food=? WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM orders";
    private static final String DELETE_ONE = "DELETE FROM orders WHERE id =?";
    private final Connection connection;

    public OrderRepositoryJDBC() throws SQLException {
        this.connection = DriverManager.getConnection(CONNECTION_STRING);
    }

    @Override
    public long insert(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,order.getName());
        preparedStatement.setString(2,order.getFood());
        System.out.println(preparedStatement.executeUpdate() + " rows added");
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public List<Order> readAll() throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL);

        while (resultSet.next()){
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String food = resultSet.getString(3);
            Order order = new Order(id, name, food);
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Optional<Order> read(long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Order update(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
        preparedStatement.setString(1, order.getName());
        preparedStatement.setString(2, order.getFood());
        preparedStatement.setLong(3, order.getId());
        preparedStatement.executeUpdate();
        return null;
    }

    @Override
    public void delete(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE);
        preparedStatement.setLong(1,order.getId());
        preparedStatement.executeUpdate();

    }
}
