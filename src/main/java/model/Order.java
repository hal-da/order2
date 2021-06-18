package model;

import java.util.Objects;

public class Order {
    private long id;
    private String name;
    private String food;

    public Order(long id, String name, String food) {
        this.id = id;
        this.name = name;
        this.food = food;
    }

    public Order(String name, String food) {
        this.name = name;
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", food='" + food + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(name, order.name) && Objects.equals(food, order.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, food);
    }
}
