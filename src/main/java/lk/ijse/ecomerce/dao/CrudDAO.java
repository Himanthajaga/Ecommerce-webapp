package lk.ijse.ecomerce.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T>extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public  boolean add(T entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public boolean exist(String id) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException;
    public <T> T searchByEmail(String name) throws SQLException, ClassNotFoundException;
    public <T> ArrayList<T> getEmails(String email) throws SQLException, ClassNotFoundException;

    public <T> T searchById(String id) throws SQLException, ClassNotFoundException;

    public <T> T searchByName(String name) throws SQLException, ClassNotFoundException;
    public String getLastId() throws SQLException, ClassNotFoundException;

    }
