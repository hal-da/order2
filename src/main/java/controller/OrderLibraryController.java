package controller;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Order;
import repository.OrderRepository;
import repository.OrderRepositoryJDBC;

import java.io.IOException;
import java.sql.SQLException;


public class OrderLibraryController {


    private final OrderRepository repository = new OrderRepositoryJDBC();
    private final ObservableList<Order> orders = FXCollections.observableArrayList(repository.readAll());
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Order,String> columnName;
    @FXML
    private TableColumn<Order, String> columnFood;
    @FXML
    private TableView<Order> tableView;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    public OrderLibraryController() throws SQLException {
    }

    @FXML
    void handleAdd(ActionEvent event) throws IOException {
        Order orderFromOrderController = initOrderController(null);
        if(orderFromOrderController != null) orders.add(orderFromOrderController);
    }
    public void handleDelete(ActionEvent actionEvent) throws SQLException {
        //TODO IMPLEMENT ARE YOU SURE??
        Order order = tableView.getSelectionModel().getSelectedItem();
        repository.delete(order);
        orders.remove(order);
    }

    @FXML
    void handleEdit(ActionEvent event) throws IOException {
        Order order = tableView.getSelectionModel().getSelectedItem();
        Order orderFromOrderController = initOrderController(order);
        if(orderFromOrderController != null) {
            int index = orders.indexOf(order);
            orders.set(index,orderFromOrderController);
        }

    }

    private Order initOrderController(Order order) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Order.fxml"));
        Parent parent = fxmlLoader.load();
        OrderController orderController = fxmlLoader.getController();
        orderController.setOrder(order);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();

        return orderController.getOrder();

    }



    @FXML
    void initialize() {
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'OrderLibrary.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'OrderLibrary.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'OrderLibrary.fxml'.";

        // deactivate edit button
        btnEdit.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        btnDelete.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());

        tableView.setItems(orders);
        columnName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        columnFood.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFood()));
    }
}

