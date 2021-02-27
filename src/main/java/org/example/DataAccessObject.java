package org.example;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This interface is used for protected variation.
 */
public interface DataAccessObject {

    boolean checkUser(String user, String password);

    ArrayList<DataBaseObject> selectKlient();

    ArrayList<DataBaseObject> selectZamowienie();

    ArrayList<DataBaseObject> selectElementZamowienia();

    ArrayList<DataBaseObject> selectTowar();

    /*
     *  This method is used for inserting of products.
     */
    void addTowar(Towar towar) throws SQLException;

    /*
     *  This method is used for inserting of clients.
     */
    void addKlient(Klient klient) throws SQLException;

    /*
     *  This method is used for inserting of invoices.
     */
    void addZamowienie(Zamowienie zamowienie) throws SQLException;

    /*
     *  This method is used for inserting of elements of invoices.
     */
    void addElement(ElementZamowienia elementZamowienia) throws SQLException;

    /*
     *  This method is used for updating of products.
     */
    void updateTowar(Towar towar) throws SQLException;

    /*
     *  This method is used for updating of clients.
     */
    void updateKlient(Klient klient) throws SQLException;

    /**
     * This method is used for updating of invoices.
     * @param zamowienie
     * @throws SQLException
     */
    void updateZamowienie(Zamowienie zamowienie) throws SQLException;

    /**
     * This method is used for updating of elements of invoices.
     * @param elementZamowienia is the object which combines a few items in the given invoice.
     * @throws SQLException
     */
    void updateElement(ElementZamowienia elementZamowienia) throws SQLException;

    /**
     * This method is used for getting all invoices with given id of client.
     * @param klient is the object client.
     */
    void returnZamowienia(Klient klient);

    /**
     * This method is used for getting all invoice elements with given id of invoice.
     * @param zamowienie is the object invoice.
     */
    void returnElementyZamowienia(Zamowienie zamowienie);

    /**
     * This method is used for getting all invoice elements with given discount.
     * @param elementZamowienia is the object which combines a few items in the given invoice.
     */
    void returnElementyZeZnizka(ElementZamowienia elementZamowienia);

    /**
     * This method is used for getting all invoices with given id of item.
     * @param towar is the object item.
     */
    void returnZamowieniaZTowarem(Towar towar);

    /**
     * This method is used to delete the client in database, depending on the client id.
     * @param klientID is the id of client.
     * @throws SQLException
     */
    void deleteKlient(int klientID) throws SQLException;

    /**
     * This method is used to delete the invoice in database, depending on the invoice id.
     * @param zamowienieID is the id of invoice.
     * @throws SQLException
     */
    void deleteZamowienie(int zamowienieID) throws SQLException;

    /**
     * This method is used to delete elements of the invoice in database, depending on the elements id.
     * @param elementZamowieniaID is the id of elements of the invoice.
     * @throws SQLException
     */
    void deleteElementZamowienia(int elementZamowieniaID) throws SQLException;

    /**
     * This method is used to delete item in database, depending on the item id.
     * @param towarID is the id of item.
     * @throws SQLException
     */
    void deleteTowar(int towarID) throws SQLException;

    /**
     * This method is used to check up the existence of given user in database.
     * @return true it the user and his password in MD5 exists in database. In other cases, false.
     */
    boolean returnHaslo();
}