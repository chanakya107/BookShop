package model;

public class Customer {
    private final String customerName;
    private final String email;
    private final String phoneNumber;
    private final String address;

    public Customer(String customerName, String email, String phoneNumber, String address) {

        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
