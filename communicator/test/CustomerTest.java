

import org.junit.Assert;
import org.junit.Test;

/**
 * Ensures the Correctness of customer.
 */

public class CustomerTest {


    @Test
    public void getName_gets_you_the_client_name() {
        Customer customer = Customer.createClient("Abhilash", "abhilashfeb30@gmail.com");
        Assert.assertEquals("Abhilash", customer.getName());
    }

    @Test
    public void getEmailId_gets_you_the_client_name() {
        Customer customer = Customer.createClient("Abhilash", "abhilashfeb30@gmail.com");
        Assert.assertEquals("abhilashfeb30@gmail.com", customer.getEmailID());
    }

    @Test(expected = IllegalArgumentException.class)
    public void name_as_null_throws_IllegalArgumentException() {
        Customer.createClient(null, "abhilashfeb30@gmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void emailId_as_null_throws_IllegalArgumentException() {
        Customer.createClient("Abhilash", null);
    }
}
