package lk.ijse.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
private int order_id;
private int user_id;
private String order_date;
private double totalAmount;
private String status;
    private String paymentMethod;
    private List<OrderDetailDTO> orderItems = new ArrayList<>();

    public OrderDTO(int id, int userId, Timestamp orderDate, double total, String status) {
    }

    public OrderDTO(int orderId, String orderDate, double totalAmount, String status) {
        this.order_id = orderId;
        this.order_date = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public OrderDTO(int orderId, int userId, Timestamp orderDate, double total, String status, String paymentMethod) {
        this.order_id = orderId;
        this.user_id = userId;
        this.order_date = orderDate.toString();
        this.totalAmount = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public OrderDTO(int orderId, Integer userId, String orderDate, double totalAmount, String status, String paymentMethod, List<OrderDetailDTO> orderItems) {
        this.order_id = orderId;
        this.user_id = userId;
        this.order_date = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.orderItems = orderItems;
    }
}
