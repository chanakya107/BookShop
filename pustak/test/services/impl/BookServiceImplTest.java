package services.impl;

import model.Book;
import model.DataBase;
import org.junit.Test;

import services.BookService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {


//    @Test
//    public void searchBookByTitle_gives_books_that_matches_the_given_title_to_be_searched() throws SQLException{
//        BookService bookService = new BookServiceImpl();
//        DataBase db = mock(DataBase.class);
//        db.connectTo("pustak.db");
//
//        ResultSet rs = mock(ResultSet.class);
//
//        when(rs.next()).thenReturn(true);
//        when(rs.getInt(1)).thenReturn(1234);
//        when(rs.getString(2)).thenReturn("Alchemist");
//        when(rs.getString(3)).thenReturn("Paulo Coelho");
//        when(rs.getInt(4)).thenReturn(200);
//        when(rs.getInt(5)).thenReturn(2);
//        when(rs.getInt(6)).thenReturn(1);
//
//        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books where title like %Alchemist%")).toReturn(rs);
//
//        Book[] foundBooks = bookService.searchBookByTitle("Alchemist");
//        Book[] expectedBook = new Book[1];
//
//        Book alchemist = new Book();
//
//        alchemist.setISBN(1234);
//        alchemist.setTitle("Alchemist");
//        alchemist.setAuthor("Paulo Coelho");
//        alchemist.setPrice(200);
//        alchemist.setQuantity_new(2);
//        alchemist.setUsedQuantity(1);
//        expectedBook[0] = alchemist;
//
//
//        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books where title like '%Alchemist%'")).toReturn(rs);
//        bookService.bindDB(db);
//        db.connectTo("pustak.db");
//
//        Assert.assertEquals(new Book[0], foundBooks);
//    }

//
//    @Test
//    public void createBook_returns_me_the_book() {
//        BookService bookService = new BookServiceImpl();
//        Book book = bookService.createBook(123, "Prince", "Jain", 123, 123);
//        Assert.assertEquals("123,Prince,Jain,123,0,123", book.toString());
//    }
//
//

    @Test
    public void addBook_connects_to_dataBase_and_invokes_insertQuery_to_insert_the_book() {
        DataBase dataBase = mock(DataBase.class);
        BookService bookService = new BookServiceImpl(dataBase);
        Book book = new Book("isbn", "title", "author1", "author2", 98, 3, 2);
        bookService.addBook(book);
        String insertQuery = "INSERT INTO books " + "values ('" + book.getISBN() + "','" + book.getTitle() + "','" + book.getAuthor1() + "','" + book.getAuthor2() + "'," + book.getPrice() + "," + book.getNewQuantity() + "," + book.getUsedQuantity() + ")";
        verify(dataBase).connectTo("pustak.db");
        verify(dataBase).insertQuery(insertQuery);
    }

    @Test
    public void searchBookByIsbn_connect_to_database_and_returns_matching_book() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getString(1)).thenReturn("1447229908");
        when(rs.getString(2)).thenReturn("The Hit");
        when(rs.getString(3)).thenReturn("%A0David Baldacci");
        when(rs.getString(4)).thenReturn("null");
        when(rs.getInt(5)).thenReturn(385);
        when(rs.getInt(6)).thenReturn(90);
        when(rs.getInt(7)).thenReturn(0);     DataBase dataBase=mock(DataBase.class);
        String keyIsbn = "1447229908";

        BookService bookService = new BookServiceImpl(dataBase);


        String searchQuery="Select * from books where isbn like '%" + keyIsbn + "%'";
        when(dataBase.selectQuery(searchQuery)).thenReturn(rs);

        bookService.searchBookByIsbn(keyIsbn);


        verify(dataBase).connectTo("pustak.db");
        verify(dataBase).selectQuery(searchQuery);
    }
}
