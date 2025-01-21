package lk.ijse.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductsDTO {
    private int product_id;
    private String name;
    private int category_id;
    private double price;
    private String description;
    private String image_url;
    public ProductsDTO(String name, int categoryId, double price, String description, String imageUrl) {
        this.name = name;
        this.category_id = categoryId;
        this.price = price;
        this.description = description;
        this.image_url = imageUrl;
    }

    public ProductsDTO(int productId, int categoryId, double price, String description, String imageUrl) {
        this.product_id = productId;
        this.category_id = categoryId;
        this.price = price;
        this.description = description;
        this.image_url = imageUrl;
    }
}
