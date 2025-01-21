package lk.ijse.ecomerce.bo;

import lk.ijse.ecomerce.bo.custom.impl.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        User, Category, Product, Cart, PlaceOrder
    }

    public SuperBO getBO(BOTypes types) {

        DataSource dataSource = getDataSource();
        switch (types) {
            case User:
                return new UserBOImpl(dataSource);
            case Category:
                return new CategoryBOImpl(dataSource);
            case Product:
                return new ProductBOImpl(dataSource);
            case Cart:
                return new CartBOImpl();
            case PlaceOrder:
                return new PlaceOrderBOImpl();
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