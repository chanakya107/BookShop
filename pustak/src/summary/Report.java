package summary;

import services.OrderService;

import java.util.List;


public class Report {

    private final OrderService orderService;


    public Report(OrderService orderService) {
        this.orderService = orderService;
    }

    public String getReport() {
        List<Transaction> todaySales = orderService.getTodaySales();
        if(todaySales.size()==0) {
            return "No transaction for today";
        }
        String content = "Isbn\tTitle\tPrice\tQuantity\tTotal\n";
        for (Transaction todaySale : todaySales) {
                content += todaySale.getIsbn() + "\t" + todaySale.getTitle() + "\t" + todaySale.getPrice() + "\t" + todaySale.getQuantity() + "\t" + todaySale.getTotal() + "\n";
        }
        return content;
    }
}
