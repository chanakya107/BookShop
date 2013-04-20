package services;

import model.DataBase;

public interface OrderService {
    void storeOrder(String customerName, String email, String phoneNumber, String address);

    void bindDB(DataBase db);
}
