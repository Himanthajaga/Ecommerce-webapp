package lk.ijse.ecomerce.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CartDTO{
    // Getters and setters
    @Getter
    @Setter
    private int cart_id;
    @Setter
    @Getter
    private int user_id;
    @Setter
    @Getter
    private int product_id;
    @Setter
    @Getter
    private int quantity;
    @Getter
    @Setter
    private double price;
    private String status;
    // Assuming price is needed to calculate total

    public double getTotal() {
        return price * quantity;
    }

    public int getProductId() {
        return product_id;
    }
}
