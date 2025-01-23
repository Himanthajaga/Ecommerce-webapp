package lk.ijse.ecomerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private int order_id;
    private int user_id;
    private String order_date;
    private double total_amount;
    private String status;

    public Order(int orderId, Date date, double total, String status) {
        this.order_id = orderId;
        this.order_date = date.toString();
        this.total_amount = total;
        this.status = status;

    }
}
