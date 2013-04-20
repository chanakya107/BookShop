package views;

import model.DataBase;
import model.Order;

import java.util.List;

public interface ViewOrderService {
    List<Order> getOrders();

    void bindDB(DataBase db);
}
