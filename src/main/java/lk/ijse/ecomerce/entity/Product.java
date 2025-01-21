package lk.ijse.ecomerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private int product_id;
    private String name;
    private int category_id;
    private double price;
    private String description;
    private String image_url;

    public Product(String name, int categoryId, double price, String description, String imageUrl) {
        this.name = name;
        this.category_id = categoryId;
        this.price = price;
        this.description = description;
        this.image_url = imageUrl;
    }
}