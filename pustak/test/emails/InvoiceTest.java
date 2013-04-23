package emails;

import model.Book;
import org.junit.Assert;
import org.junit.Test;

public class InvoiceTest {

    @Test
    public void getMailContent_gives_the_mail_data_with_the_given_customerName_and_book_details() {
        String expectedMailContent = "Dear Abhi,\nYour order for the following book has been placed:\n\n" +
                "ISBN : 123456\nBook Title : Ignited Minds\nAuthor Name : chanu\nPrice : 500" + "\n\n" +
                "Your order is registered on " + "2013-04-22 10:41:23" + ".\nYou can except a mail about its dispsatch soon. \n\n\t" +
                "Thank you for buying. \n\n" + "Regards,\nPustak.com";

        Book book = new Book("123456", "Ignited Minds", "chanu", "abhi", 500, 10, 0);
        Invoice invoice = new Invoice(book, "Abhi", "2013-04-22 10:41:23");
        Assert.assertEquals(expectedMailContent, invoice.getContent());
    }
}
