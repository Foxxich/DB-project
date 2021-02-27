package org.example;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This interface is used for protected variation.
 */
public interface DataAccessObject {

    /**
     * This method is used if the user can get access to the database.
     * @param user is user's login.
     * @param password is user's password.
     * @return true if user's login and password are correct and he is allowed to connect to the database.
     * In other case, false.
     */
    boolean checkUser(String user, String password);

    /**
     * This method is used to get clients as DataBaseObject.
     * @return DataBaseObject.
     */
    ArrayList<DataBaseObject> selectKlient();

    /**
     * This method is used to get invoice as DataBaseObject.
     * @return DataBaseObject.
     */
    ArrayList<DataBaseObject> selectZamowienie();

    /**
     * This method is used to get invoice element as DataBaseObject.
     * @return DataBaseObject.
     */
    ArrayList<DataBaseObject> selectElementZamowienia();

    /**
     * This method is used to get item as DataBaseObject.
     * @return DataBaseObject.
     */
    ArrayList<DataBaseObject> selectTowar();

    /**
     * This method is used for inserting of products.
     * @param towar is the item.
     * @throws SQLException in case user cannot add new data to the database.
     */
    void addTowar(Towar towar) throws SQLException;

    /**
     * This method is used for inserting of clients.
     * @param klient is the client.
     * @throws SQLException in case user cannot add new data to the database.
     */
    void addKlient(Klient klient) throws SQLException;

    /**
     * This method is used for inserting of invoices.
     * @param zamowienie is the invoice.
     * @throws SQLException in case user cannot add new data to the database.
     */
    void addZamowienie(Zamowienie zamowienie) throws SQLException;

    /**
     * This method is used for inserting of elements of invoices.
     * @param elementZamowienia is the element of the invoice.
     * @throws SQLException in case user cannot add new data to the database.
     */
    void addElement(ElementZamowienia elementZamowienia) throws SQLException;

    /**
     * This method is used for updating of products.
     * @param towar is the item.
     * @throws SQLException in case user cannot update data in the database.
     */
    void updateTowar(Towar towar) throws SQLException;

    /**
     * This method is used for updating of clients.
     * @param klient is the client.
     * @throws SQLException in case user cannot update data in the database.
     */
    void updateKlient(Klient klient) throws SQLException;
    
    /**
     * This method is used for updating of invoices.
     * @param zamowienie is the invoice.
     * @throws SQLException in case user cannot update data in the database.
     */
    void updateZamowienie(Zamowienie zamowienie) throws SQLException;

    /**
     * This method is used for updating of elements of invoices.
     * @param elementZamowienia is the element of the invoice.
     * @throws SQLException in case user cannot update data in the database.
     */
    void updateElement(ElementZamowienia elementZamowienia) throws SQLException;

    /**
     * This method is used to delete the client in database, depending on the client id.
     * @param klientID is the id of client.
     * @throws SQLException in case user cannot delete data in the database.
     */
    void deleteKlient(int klientID) throws SQLException;

    /**
     * This method is used to delete the invoice in database, depending on the invoice id.
     * @param zamowienieID is the id of invoice.
     * @throws SQLException in case user cannot delete data in the database.
     */
    void deleteZamowienie(int zamowienieID) throws SQLException;

    /**
     * This method is used to delete elements of the invoice in database, depending on the elements id.
     * @param elementZamowieniaID is the id of elements of the invoice.
     * @throws SQLException in case user cannot delete data in the database.
     */
    void deleteElementZamowienia(int elementZamowieniaID) throws SQLException;

    /**
     * This method is used to delete item in database, depending on the item id.
     * @param towarID is the id of item.
     * @throws SQLException in case user cannot delete data in the database.
     */
    void deleteTowar(int towarID) throws SQLException;

    /**
     * This method is used to check up the existence of given user and his password in special table of database.
     * @return true if the user and his password in MD5 exists in database. In other cases, false.
     */
    boolean returnHaslo();
}