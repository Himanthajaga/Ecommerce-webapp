package lk.ijse.ecomerce.bo.custom.impl;

import lk.ijse.ecomerce.bo.custom.CartBO;
import lk.ijse.ecomerce.dto.CartDTO;

import java.util.List;

public class CartBOImpl implements CartBO {
    @Override
    public void addToCart(int productId, int quantity) {

    }

    @Override
    public List<CartDTO> getCartItems() {
        return null;
    }

    @Override
    public void updateCart(int productId, int quantity) {

    }

    @Override
    public void removeFromCart(int productId) {

    }
}
