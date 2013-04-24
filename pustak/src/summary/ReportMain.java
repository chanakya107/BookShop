package summary;

import mail.Mail;
import model.DataBase;
import services.impl.OrderServiceImpl;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportMain {
    public static void main(String[] args) throws MessagingException {
        Report report = new Report(new OrderServiceImpl(new DataBase()));
        String content = report.getReport();
        String date = "Date:" + new SimpleDateFormat("yyyy-MM-dd ").format(Calendar.getInstance().getTime());
        Mail mail = new Mail("Transaction details on " + date, content);
        mail.sendMail("abhilashfeb30@gmail.com");
    }
}
