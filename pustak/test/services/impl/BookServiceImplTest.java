package services.impl;

import model.Book;
import model.DataBase;
import org.junit.Test;
import services.BookService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BookServiceImplTest {


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
