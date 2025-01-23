package lk.ijse.ecomerce.bo.custom;

import lk.ijse.ecomerce.bo.SuperBO;
import lk.ijse.ecomerce.dto.CartDTO;

import java.util.List;

public interface CartBO extends SuperBO {
    void addToCart(int productId, int quantity);

    List<CartDTO> getCartItems();

    void updateCart(int productId, int quantity);

    void removeFromCart(int productId);
}
