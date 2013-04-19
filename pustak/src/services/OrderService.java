package services;

public interface OrderService {
    void storeOrder(String customerName, String email, String phoneNumber, String address);
}
