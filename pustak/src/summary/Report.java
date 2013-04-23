package summary;

import services.OrderService;

public class Report {

    private final OrderService orderService;

    public Report(OrderService orderService) {
        this.orderService = orderService;
    }

    public void getContent() {
        orderService.getTodayOrders();
    }
}
