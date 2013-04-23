package services.impl;

import org.junit.Test;
import services.BookService;

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

//    @Test
//    public void searchBookByTitle_gives_books_when_searched_with_empty_is_searched() {
//        BookService bookService = new BookServiceImpl();
//        DataBase db = mock(DataBase.class);
//        ResultSet rs = mock(ResultSet.class);
//        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books")).toReturn(rs);
//        bookService.bindDB(db);
//        db.connectTo("pustak.db");
//
//        String category="New";
//        Book[] foundBooks = bookService.searchBookByTitle("", category);
//
//        Assert.assertEquals(new Book[0], foundBooks);
//    }


//    @Test
//    public void searchBookByTitle_gives_books_when_searched_with_null_is_searched() {
//        BookService bookService = new BookServiceImpl();
//        DataBase db = mock(DataBase.class);
//        ResultSet rs = mock(ResultSet.class);
//        stub(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books")).toReturn(rs);
//        bookService.bindDB(db);
//        db.connectTo("pustak.db");
//
//        String category="New";
//        Book[] foundBooks = bookService.searchBookByTitle(null, category);
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
//    @Test(expected = IllegalArgumentException.class)
//    public void binding_null_db_gives_IllegalArgumentException() {
//        BookService bookService = new BookServiceImpl();
//        bookService.bindDB(null);
//    }
}
