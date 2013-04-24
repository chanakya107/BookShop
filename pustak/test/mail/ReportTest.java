package mail;

import junit.framework.Assert;
import org.junit.Test;
import services.OrderService;
import summary.Report;
import summary.Transaction;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class ReportTest {

    @Test
    public void method_getReport_calls_OrderService_getDaysOrder() {
        OrderService service = mock(OrderService.class);
        Report report = new Report(service);
        report.getReport();
        verify(service).getTodaySales();
    }

    @Test
    public void getReport_gives_No_transaction_for_today_when_no_transaction_done() {
        String reportDetails = "No transaction for today";
        OrderService service = mock(OrderService.class);
        List<Transaction> transaction = mock(ArrayList.class);
        stub(service.getTodaySales()).toReturn(transaction);
        Report report = new Report(service);
        Assert.assertEquals(reportDetails, report.getReport());
    }

    @Test
    public void getReport_gives_report_with_one_sale() {
        String reportDetails = "Isbn\tTitle\tPrice\tQuantity\tTotal\n" + "12345\tThe wall\t10\t10\t100\n";
        OrderService service = mock(OrderService.class);
        List<Transaction> transaction = new ArrayList<Transaction>();
        transaction.add(new Transaction("12345", "The wall", 10, 10, 100));
        stub(service.getTodaySales()).toReturn(transaction);
        Report report = new Report(service);
        Assert.assertEquals(reportDetails, report.getReport());
    }

    @Test
    public void getReport_gives_report_with_multiple_sales() {
        String reportDetails = "Isbn\tTitle\tPrice\tQuantity\tTotal\n" + "12345\tThe wall\t10\t10\t100\n12345\tThe Prince\t10\t10\t100\n";
        OrderService service = mock(OrderService.class);
        List<Transaction> transaction = new ArrayList<Transaction>();
        transaction.add(new Transaction("12345", "The wall", 10, 10, 100));
        transaction.add(new Transaction("12345", "The Prince", 10, 10, 100));
        stub(service.getTodaySales()).toReturn(transaction);
        Report report = new Report(service);
        Assert.assertEquals(reportDetails, report.getReport());
    }
}
