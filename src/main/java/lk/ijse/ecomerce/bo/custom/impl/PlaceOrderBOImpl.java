package lk.ijse.ecomerce.bo.custom.impl;

import lk.ijse.ecomerce.bo.custom.PlaceOrderBO;
import lk.ijse.ecomerce.dao.DAOFactory;
import lk.ijse.ecomerce.dao.custom.OrderDAO;
import lk.ijse.ecomerce.dto.OrderDTO;
import lk.ijse.ecomerce.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Orders);

    @Override
    public List<OrderDTO> getOrderHistory() {
        try {
            List<Order> orders = orderDAO.getAll();
            return orders.stream().map(order -> new OrderDTO(order.getOrder_id(), order.getOrder_date(), order.getTotal_amount(), order.getStatus())).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void placeOrder() {
        // Implement order placement logic here
    }
}