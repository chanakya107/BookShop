package model;

import junit.framework.Assert;
import org.junit.Test;

public class BookTest {
    @Test
    public void getISBN_gives_the_ISBN_of_a_book() {
        Book alchemist = new Book();
        alchemist.setISBN(123);

        Assert.assertEquals(123, alchemist.getISBN());
    }

    @Test
    public void getTitle_gives_the_title_of_a_book() {
        Book alchemist = new Book();
        alchemist.setTitle("Alchemist");

        Assert.assertEquals("Alchemist", alchemist.getTitle());
    }

    @Test
    public void getAuthor_gives_the_author_of_a_book() {
        Book alchemist = new Book();
        alchemist.setAuthor("Paulo Coelho");

        Assert.assertEquals("Paulo Coelho", alchemist.getAuthor());
    }

    @Test
    public void getPrice_gives_the_price_of_a_book() {
        Book alchemist = new Book();
        alchemist.setPrice(300);

        Assert.assertEquals(300, alchemist.getPrice());
    }

    @Test
    public void getUsedQuantity_gives_the_quantity_of_used_book() {
        Book alchemist = new Book();
        alchemist.setQuantity_used(3);

        Assert.assertEquals(3, alchemist.getQuantity_used());
    }

    @Test
    public void getNewQuantity_gives_the_quantity_of_new_books() {
        Book alchemist = new Book();
        alchemist.setQuantity_new(3);

        Assert.assertEquals(3, alchemist.getQuantity_New());
    }
}
