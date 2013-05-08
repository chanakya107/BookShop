package mail;

import junit.framework.Assert;
import org.junit.Test;
import services.OrderService;
import summary.Report;
import summary.Transaction;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReportTest {

    @Test
    public void method_getReport_calls_OrderService_getDaysOrder() {
        OrderService service = mock(OrderService.class);
        Report report = new Report(service);
        report.getReport();
        verify(service).getTodaySales();
    }

//
//    @Test
//    public void getReport_gives_No_transaction_for_today_when_no_transaction_done() {
//        String reportDetails = "<html>\n" +
//                "<body>\n" +
//                "<h2>DailyReport</h2>\n" +
//                "    <h3>No transaction for today</h3>\n" +
//                "</body>\n" +
//                "</html>";
//        OrderService service = mock(OrderService.class);
//        List<Transaction> transaction = mock(ArrayList.class);
//        stub(service.getTodaySales()).toReturn(transaction);
//        Report report = new Report(service);
//        Assert.assertEquals(report.getReport(), report.getReport());
//    }
//
//    @Test
//    public void getReport_gives_report_with_one_sale() {
//        String reportDetails = "<html>\n" +
//                "<body>\n" +
//                "<h2>DailyReport</h2>\n" +
//                "<table border=\"1\">\n" +
//                "    <tr>\n" +
//                "        <td>ISBN</td>\n" +
//                "        <td>Title</td>\n" +
//                "        <td>Price</td>\n" +
//                "        <td>Quantity</td>\n" +
//                "        <td>Total</td>\n" +
//                "    </tr>\n" +
//                "\t        <tr>\n" +
//                "            <td >12345</td>\n" +
//                "            <td >The wall</td>\n" +
//                "            <td >10</td>\n" +
//                "            <td >10</td>\n" +
//                "            <td >100</td>\n" +
//                "\t\t</tr>\n" +
//                "\t</table>\n" +
//                "</body>\n" +
//                "</html>";
//        OrderService service = mock(OrderService.class);
//        List<Transaction> transaction = new ArrayList<Transaction>();
//        transaction.add(new Transaction("12345", "The wall", 10, 10, 100));
//        stub(service.getTodaySales()).toReturn(transaction);
//        Report report = new Report(service);
//        Assert.assertEquals(report.getReport(), report.getReport());
//    }
//
//    @Test
//    public void getReport_gives_report_with_multiple_sales() {
//        String reportDetails = "<html>\n" +
//                "<body>\n" +
//                "<h2>DailyReport</h2>\n" +
//                "<table border=\"1\">\n" +
//                "    <tr>\n" +
//                "        <td>ISBN</td>\n" +
//                "        <td>Title</td>\n" +
//                "        <td>Price</td>\n" +
//                "        <td>Quantity</td>\n" +
//                "        <td>Total</td>\n" +
//                "    </tr>\n" +
//                "\t        <tr>\n" +
//                "            <td >12345</td>\n" +
//                "            <td >The wall</td>\n" +
//                "            <td >10</td>\n" +
//                "            <td >10</td>\n" +
//                "            <td >100</td>\n" +
//                "\t\t</tr>\n" +
//                "\t        <tr>\n" +
//                "            <td >12345</td>\n" +
//                "            <td >The Prince</td>\n" +
//                "            <td >10</td>\n" +
//                "            <td >10</td>\n" +
//                "            <td >100</td>\n" +
//                "\t\t</tr>\n" +
//                "\t</table>\n" +
//                "</body>\n" +
//                "</html>\n";
//        OrderService service = mock(OrderService.class);
//        List<Transaction> transaction = new ArrayList<Transaction>();
//        transaction.add(new Transaction("12345", "The wall", 10, 10, 100));
//        transaction.add(new Transaction("12345", "The Prince", 10, 10, 100));
//        stub(service.getTodaySales()).toReturn(transaction);
//        Report report = new Report(service);
//        Assert.assertEquals(report.getReport(), report.getReport());
//    }
}
