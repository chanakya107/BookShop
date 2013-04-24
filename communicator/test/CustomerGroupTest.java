

import org.junit.Assert;
import org.junit.Test;

public class CustomerGroupTest {
    @Test
    public void toString_gets_collection_of_customer_details() {
        CustomerGroup customerGroup = new CustomerGroup();
        Customer abhilash = Customer.createClient("Abhilash", "abhilashfeb30@gmail.com");
        customerGroup.addCustomer(abhilash);
        Assert.assertEquals(true, customerGroup.isCustomerPresent(abhilash));
    }

    @Test
    public void buildCustomers_build_customer_using_the_details() {
        CustomerGroup customerGroup = new CustomerGroup();
        String customers = "Vinod,Kumar,vk-abcd-1234@gmail.com,9845712345" + "\n";
        customerGroup.buildCustomer(customers);
        Customer customer = Customer.createClient("Vinod Kumar", "vk-abcd-1234@gmail.com");
        Assert.assertEquals(true, customerGroup.isCustomerPresent(customer));
    }

}
