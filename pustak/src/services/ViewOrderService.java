package services;

import model.DataBase;
import model.Order;

import java.util.List;

public interface ViewOrderService {
    List<Order> getOrders();

    void bindDB(DataBase db);

    List<Order> getOrdersWithBookDetails(List<Order> orders);
}
