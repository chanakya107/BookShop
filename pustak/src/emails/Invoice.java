package emails;

import model.Book;

public class Invoice {

    private final Book orderedBook;
    private final String time;
    private final String customerName;
    private final String subject = "Acknowledgement for placed order";

    public Invoice(Book orderedBook, String customerName, String time) {

        this.orderedBook = orderedBook;
        this.customerName = customerName;
        this.time = time;
    }

    public String getContent() {
        return getSalutation() +  getBody();
    }

    private String getBody() {
        return "Your order for the following book has been placed:\n\n" + getBookDetails() +
                "Your order is registered on " + time + ".\nYou can except a mail about its dispsatch soon. \n\n\t" + "Thank you for buying. \n\n" + "Regards,\nPustak.com";

    }

    private String getBookDetails() {
        return "ISBN : " + orderedBook.getISBN() + "\n" +"Book Title : " + orderedBook.getTitle() + "\n" +
                "Author Name : " + orderedBook.getAuthor1() + "\n" +
                "Price : " + orderedBook.getPrice() + "\n\n";
    }

    private String getSalutation() {
        return "Dear " + customerName + ",\n";
    }


    public String getSubject() {
        return subject;
    }
}
