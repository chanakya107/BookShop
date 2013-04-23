package services.impl;

import model.Book;
import model.DataBase;
import org.junit.Assert;
import org.junit.Test;
import services.BookService;

import java.sql.ResultSet;

import static org.mockito.Mockito.*;

public class BookServiceImplTest {


//    @Test
//    public void searchBookByTitle_gives_books_that_matches_the_given_title_to_be_searched() throws SQLException, SQLException {
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
//        alchemist.setQuantity_used(1);
//        expectedBook[0] = alchemist;
//
//
//        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books where title like '%Alchemist%'")).toReturn(rs);
//        bookService.bindDB(db);
//        db.connectTo("pustak.db");
//
//        Assert.assertEquals(new Book[0], foundBooks);
//    }

//    @Test
//    public void searchbookbyTitle_gives_an_empty_books_collection_when_no_books_is_found(){
//        DataBase db = mock(DataBase.class);
//        BookService bookService=new BookServiceImpl();
//        db.connectTo("pustak.db");
//        ResultSet rs = mock(ResultSet.class);
//
//        when(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books where title like %Alchemist%")).thenReturn(rs);
//
//        Book[] noBooks = {};
//        Book[] foundBooks = bookService.searchBookByTitle("Alchemist");
//
//        Assert.assertEquals(noBooks, foundBooks);
//    }

    @Test
    public void searchBookByTitle_gives_books_when_searched_with_empty_is_searched() {
        DataBase db = mock(DataBase.class);
        BookService bookService = new BookServiceImpl(db);
        ResultSet rs = mock(ResultSet.class);
        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books")).toReturn(rs);
        db.connectTo("pustak.db");

        Book[] foundBooks = bookService.searchBookByTitle("", "Old");

        Assert.assertEquals(new Book[0], foundBooks);
    }



    @Test
    public void searchBookByTitle_gives_books_when_searched_with_null_is_searched() {
        DataBase db = mock(DataBase.class);
        BookService bookService = new BookServiceImpl(db);
        ResultSet rs = mock(ResultSet.class);
        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books")).toReturn(rs);
        db.connectTo("pustak.db");

        Book[] foundBooks = bookService.searchBookByTitle(null, "Old");

        Assert.assertEquals(new Book[0], foundBooks);
    }



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
}
