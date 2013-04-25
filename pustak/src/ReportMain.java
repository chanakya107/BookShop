import model.DataBase;
import services.impl.OrderServiceImpl;
import summary.Report;
import summary.ReportMail;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportMain {
    public static void main(String[] args) throws MessagingException {
        Report report = new Report(new OrderServiceImpl(new DataBase()));
        String content = report.getReport();
        String date = "Date:" + new SimpleDateFormat("yyyy-MM-dd ").format(Calendar.getInstance().getTime());
        ReportMail mail = new ReportMail("Transaction details on " + date, content);
        mail.setContentType("text/html");
        mail.sendMail("abhilasv@thoughtworks.com");
        mail.sendMail("chanakya.ajith@gmail.com");
//      mail.sendMail("srinaray@thoughtworks.com");
    }
}
