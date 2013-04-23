package services.impl;

import model.Book;
import model.DataBase;
import services.BookService;

public class BookServiceImpl implements BookService {

    DataBase db;

    @Override
    public void bindDB(DataBase db) {
        if (db == null) throw new IllegalArgumentException("Cannot Bind DataBase : " + db);
        this.db = db;
    }

    @Override
    public boolean addBook(Book book) {
        db.connectTo("pustak.db");
        String insertQuery = "INSERT INTO books " + "values ('" + book.getISBN() + "','" + book.getTitle() + "','" + book.getAuthor1() + "','" + book.getAuthor2() + "'," + book.getPrice() + "," + book.getNewQuantity() + "," + book.getUsedQuantity() + ")";
        return db.insertQuery(insertQuery);
    }

//    @Override
//
//    public Book[] searchBookByTitle(String searchkey, String category) {
//        Book[] books;
//        db.connectTo("pustak.db");
//        if (searchkey == null || searchkey.equals("")) {
////            books = buildResultBooks(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books"));
//            db.closeConnection();
////            return books;
//        }
//        if (category.equals("New")) {
////            books = buildResultBooks(db.selectQuery("select isbn,title,author,price,newbookquantity from books where title like '%" + searchkey + "%'"));
////            return books;
//        } else {
////            books = buildResultBooks(db.selectQuery("select isbn,title,author,price,usedbookquantity from books  where title like '%" + searchkey + "%'"));
////            return books;
//        }
//
//
//    }
//
//    private Book[] buildResultBooks(ResultSet rs) {
//        List<Book> books = new ArrayList<Book>();
//        try {
//            while (rs.next()) {
//                String plusFreeTitle = rs.getString(2).replace("+", " ");
//                String plusFreeAuthorName = rs.getString(3).replace("+", " ");
//                if (rs.getInt(5) != 0)
//                    books.add(createBook(rs.getInt(1), plusFreeTitle, plusFreeAuthorName, rs.getInt(4), rs.getInt(5)));
//
//            }
//            return books.toArray(new Book[books.size()]);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
