package model;

public class Customer {
    private final String customerName;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String pinCode;

    public Customer(String customerName, String email, String phoneNumber, String address, String pinCode) {

        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pinCode = pinCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        return !(address != null ? !address.equals(customer.address) : customer.address != null) && !(customerName != null ? !customerName.equals(customer.customerName) : customer.customerName != null) && !(email != null ? !email.equals(customer.email) : customer.email != null) && !(phoneNumber != null ? !phoneNumber.equals(customer.phoneNumber) : customer.phoneNumber != null);

    }

    @Override
    public int hashCode() {
        int result = customerName != null ? customerName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
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

    public String getPinCode() {
        return pinCode;
    }
}
