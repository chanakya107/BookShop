

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CustomerGroup {
    List<Customer> customers = new ArrayList<Customer>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        String resultString = "";
        for (Customer customer : customers) {
            resultString += customer.toString();
        }
        return resultString;
    }

    public void buildCustomer(String contents) {
        String[] customers = contents.split("\n");
        for (String customer : customers) {
            StringTokenizer st = new StringTokenizer(customer, ",");
            addCustomer(Customer.createClient(st.nextToken() + " " + st.nextToken(), st.nextToken()));
        }
    }

    public boolean isCustomerPresent(Customer customerToFind) {
        for (Customer customer : customers) {
            if (customerToFind.equals(customer))
                return true;
        }
        return false;
    }

    public void sendMail(String messageTemplate, String subject) throws MessagingException {
        for (Customer customer : customers) {
            customer.sendMail(messageTemplate, subject);
        }
    }
}
