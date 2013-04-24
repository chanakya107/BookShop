package emails;

import model.Book;

public class DispatchMail {
    private final Book orderedBook;
    private final String customerName;
    private final String address;
    private final String time;

    public DispatchMail(Book orderedBook, String customerName, String address, String time) {

        this.orderedBook = orderedBook;
        this.customerName = customerName;
        this.address = address;
        this.time = time;
    }

    public String getSubject() {
        return "Confirmation on book dispatched";
    }

    public String getContent() {
        return getSalutation() + getBody();
    }

    private String getBody() {
        return "Your order for the book:\n\n" + getBookDetails() +
                "has been dispatched "+
                "to:\n" + address + "\non " + time + ".\n" + "\n\nThank you for buying. \n\n" + "Regards,\nPustak.com";
    }

    private String getBookDetails() {
        return "Book Title : " + orderedBook.getTitle() + "\n" +
                "Author Name : " + orderedBook.getAuthor1() + "\n";
    }

    public String getSalutation() {
        return "Dear " + customerName + ",\n";
    }
}
