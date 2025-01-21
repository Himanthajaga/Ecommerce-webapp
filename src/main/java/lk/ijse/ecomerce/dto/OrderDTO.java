package lk.ijse.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
private int order_id;
private int user_id;
private String order_date;
private double total_amount;
private String status;

    public OrderDTO(int id, int userId, Timestamp orderDate, double total, String status) {
    }
}
