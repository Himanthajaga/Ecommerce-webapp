package lk.ijse.ecomerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    private int cart_id;
    private int user_id;
    private int product_id;
    private int quantity;
    private double price;
    private String status;
}
