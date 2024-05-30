package paymentService.model;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String product;
    private int quantity;
    private String status;
}
