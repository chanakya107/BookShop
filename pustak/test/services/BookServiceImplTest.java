package services;

import model.Book;
import model.DataBase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class BookServiceImplTest {
    @Test
    public void searchBookByTitle_gives_books_that_matches_Alchemist_to_be_searched() {
        BookService bookService = new BookServiceImpl();
        DataBase db = mock(DataBase.class);
        ResultSet rs = mock(ResultSet.class);
        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books where title like '%Alchemist%'")).toReturn(rs);
        bookService.bindDB(db);
        db.connectTo("pustak.db");

        Book[] foundBooks = bookService.searchBookByTitle("Alchemist");

        Assert.assertEquals(new Book[0], foundBooks);
    }

    @Test
    public void searchBookByTitle_gives_books_when_searched_with_empty_is_searched() {
        BookService bookService = new BookServiceImpl();
        DataBase db = mock(DataBase.class);
        ResultSet rs = mock(ResultSet.class);
        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books")).toReturn(rs);
        bookService.bindDB(db);
        db.connectTo("pustak.db");

        Book[] foundBooks = bookService.searchBookByTitle("");

        Assert.assertEquals(new Book[0], foundBooks);
    }

    @Test
    public void searchBookByTitle_gives_books_when_searched_with_null_is_searched() {
        BookService bookService = new BookServiceImpl();
        DataBase db = mock(DataBase.class);
        ResultSet rs = mock(ResultSet.class);
        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books")).toReturn(rs);
        bookService.bindDB(db);
        db.connectTo("pustak.db");

        Book[] foundBooks = bookService.searchBookByTitle(null);

        Assert.assertEquals(new Book[0], foundBooks);
    }

    @Test
    public void createBook_returns_me_the_book() {
        BookService bookService = new BookServiceImpl();
        Book book = bookService.createBook(123, "Prince", "Jain", 123, 123, 123);
        Assert.assertEquals("123,Prince,Jain,123,123,123", book.toString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void binding_null_db_gives_IllegalArgumentException(){
        BookService bookService = new BookServiceImpl();
        bookService.bindDB(null);
    }

}
