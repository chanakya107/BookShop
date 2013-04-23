package mail;

import org.junit.Test;
import services.OrderService;
import summary.Report;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReportTest {
    @Test
    public void method_get_sendMail_calls_OrderService_getDaysOrder() {
        OrderService service = mock(OrderService.class);
        Report report = new Report(service);
        report.getContent();
        verify(service).getTodayOrders();
    }



}
