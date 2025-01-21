package lk.ijse.ecomerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private int order_id;
    private int user_id;
    private String order_date;
    private double total_amount;
    private String status;
}
