package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Order;
import repository.OrderRepository;
import repository.OrderRepositoryJDBC;

import java.sql.SQLException;

public class OrderController {

    Order order = null;
    OrderRepository repository = new OrderRepositoryJDBC();

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldFood;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    public OrderController() throws SQLException {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        if(order != null){
            textFieldFood.setText(order.getFood());
            textFieldName.setText(order.getName());
        }

    }

    @FXML
    void handlerAdd(ActionEvent event) throws SQLException {
        if (!textFieldName.getText().isBlank() && !textFieldFood.getText().isBlank()) {
            if (order == null) {
                order = new Order(textFieldName.getText(), textFieldFood.getText());
                order.setId(repository.insert(order));
                System.out.println(order);
            } else {
                order.setName(textFieldName.getText());
                order.setFood(textFieldFood.getText());
                repository.update(order);
            }
            closeStage();
        }
    }

    @FXML
    void handlerCancel(ActionEvent event) {
        order = null;
        closeStage();
    }

    private void closeStage(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert textFieldName != null : "fx:id=\"textFieldName\" was not injected: check your FXML file 'Order.fxml'.";
        assert textFieldFood != null : "fx:id=\"textFieldFood\" was not injected: check your FXML file 'Order.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'Order.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Order.fxml'.";

        btnAdd.disableProperty().bind(
                textFieldFood.textProperty().isEmpty().or(
                        textFieldName.textProperty().isEmpty()
                )
        );
    }
}

