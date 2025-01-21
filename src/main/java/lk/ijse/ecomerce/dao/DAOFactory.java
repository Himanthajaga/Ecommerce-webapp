package lk.ijse.ecomerce.dao;

import lk.ijse.ecomerce.dao.custom.impl.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        Users, Products, Orders, OrderDetails, Category, Cart
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case Users:
                return new UserDAOImpl(getDataSource());
            case Products:
                return new ProductDAOImpl(getDataSource());
            case Orders:
                return new OrderDAOImpl(getDataSource());
            case OrderDetails:
                return new OrderDetailDAOImpl(getDataSource());
            case Category:
                return new CategoryDAOImpl(getDataSource());
            case Cart:
                return new CartDAOImpl();
            default:
                return null;
        }
    }

    private DataSource getDataSource() {
        try {
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:comp/env/jdbc/pool");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}