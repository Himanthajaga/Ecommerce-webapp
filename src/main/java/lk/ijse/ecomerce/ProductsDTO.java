package lk.ijse.ecomerce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductsDTO {
    private int product_id;
    private String name;
    private int category_id;
    private double price;
    private String description;
    private String image_url;


    public ProductsDTO(int id, String name, double price, int qty) {
    }
}
