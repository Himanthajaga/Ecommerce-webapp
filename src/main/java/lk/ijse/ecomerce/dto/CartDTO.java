package lk.ijse.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CartDTO{
    private int cart_id;
    private int user_id;
    private int product_id;
    private int quantity;
}
