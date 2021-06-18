package repository;

import model.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    long insert(Order order) throws SQLException;
    List<Order> readAll() throws SQLException;
    Optional<Order> read(long id) throws SQLException;
    Order update(Order order) throws SQLException;
    void delete(Order order) throws SQLException;

}
