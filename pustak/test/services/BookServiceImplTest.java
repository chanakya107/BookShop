package services;

import model.Book;
import org.junit.Assert;
import org.junit.Test;
public class BookServiceImplTest {
//    @Test
//    public void searchBookByTitle_gives_books_that_matches_the_given_title_to_be_searched() {
//        BookService bookService = new BookServiceImpl();
//        DataBase db = new DataBase();
//        db.connectTo()
//
//        ResultSet rs = mock(ResultSet.class);
//        stub(db.selectQuery("select isbn,title,author,price,newbookqunatity,usedbookqunatity from books where title like 'Alchemist'")).toReturn(rs);
//
//        Book[] foundBooks = bookService.searchBookByTitle("Alchemist");
//
//        Assert.assertEquals(new Book[0], foundBooks);
//    }
    @Test
    public void createBook_returns_me_the_book(){
        BookService bookService=new BookServiceImpl();
        Book book = bookService.createBook(123, "Prince", "Jain", 123, 123, 123);
        Assert.assertEquals("123,Prince,Jain,123,123,123",book.toString());
    }

}
