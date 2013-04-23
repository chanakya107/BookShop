package services.impl;

import model.DataBase;
import org.junit.Before;
import org.junit.Test;
import services.AddBookService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddBookServiceImplTest {
    DataBase db;
    AddBookService service;

    @Before
    public void setUp() {
        db = mock(DataBase.class);
        service = new AddBookServiceImpl();
        service.bindDB(db);
    }

    @Test
    public void addBook_will_connect_to_database() {
        service.addBook("isbn", "title", "author", 23, 4, "New");
        verify(db).connectTo("pustak.db");
    }

    @Test
    public void service_calls_insertBookToDatabase_in_db_with_zero_old_book_quantity_if_the_book_is_new() {
        String sql = "values ('isbn','title','author'," + 23 + "," + 4 + "," + 0 + ")";
        String queryString = "INSERT INTO books " + sql;
        service.addBook("isbn", "title", "author", 23, 4, "New");
        verify(db).insertBooksToDataBase(queryString);
    }

    @Test
    public void service_calls_insertBookToDatabase_in_db_with_zero_new_book_quantity_if_the_book_is_used() {
        String sql = "values ('isbn','title','author'," + 23 + "," + 0 + "," + 4 + ")";
        String queryString = "INSERT INTO books " + sql;
        service.addBook("isbn", "title", "author", 23, 4, "Used");
        verify(db).insertBooksToDataBase(queryString);
    }

    @Test
    public void insertBookToDataBase_will_close_connection_after_added_to_database() {
        service.addBook("isbn", "title", "author", 23, 4, "Used");
        verify(db).closeConnection();
    }
}
