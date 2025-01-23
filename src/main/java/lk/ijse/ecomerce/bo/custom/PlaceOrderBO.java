package lk.ijse.ecomerce.bo.custom;

import lk.ijse.ecomerce.bo.SuperBO;
import lk.ijse.ecomerce.dto.OrderDTO;

import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    List<OrderDTO> getOrderHistory();

    void placeOrder();
}
