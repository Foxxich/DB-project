package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class is used for Low coupling and High cohesion.
 */
public class Controller {

    /**
     * dataBaseObjects is array list of headers and values.
     * dataAccessObject is used to get data from the database.
     */
    ArrayList<DataBaseObject> dataBaseObjects = new ArrayList<>();
    DataAccessObject dataAccessObject = new DataBaseSQL();

    /**
     * This method is used to get dataBaseObjects.
     * @return dataBaseObjects.
     */
    public ArrayList<DataBaseObject> getDataBaseObjects() {
        return dataBaseObjects;
    }

    /**
     * This method is used to log to database.
     * @param login is a user's login which is used to connection to database.
     * @param password is a user's password which is used to connection to database.
     * @return true if the login and password are correct and it is possible to
     * connect to the database. In other case, false.
     */
    public boolean logToDatabase(String login, String password) {
        return dataAccessObject.checkUser(login, password);
    }

    /**
     * This method is used to check existence of the account in the database.
     * @return true if there exists account with given login and MD-5 password.
     * In the other case, false.
     */
    public boolean checkPass() {
        return dataAccessObject.returnHaslo();
    }

    /**
     * This method is used to add the data from Klient table
     * to the array list of dataBaseObjects.
     * @return selected data from Klient table.
     */
    public ArrayList<DataBaseObject> selectKlient() {
        dataBaseObjects = dataAccessObject.selectKlient();
        return dataBaseObjects;
    }

    /**
     * This method is used to add the data from Zamowienia table
     * to the array list of dataBaseObjects.
     * @return selected data from Zamowienia table.
     */
    public ArrayList<DataBaseObject> selectZamowienie() {
        dataBaseObjects = dataAccessObject.selectZamowienie();
        return dataBaseObjects;
    }

    /**
     * This method is used to add the data from ElementZamowienia table
     * to the array list of dataBaseObjects.
     * @return selected data from ElementZamowienia table.
     */
    public ArrayList<DataBaseObject> selectElementZamowienia() {
        dataBaseObjects = dataAccessObject.selectElementZamowienia();
        return dataBaseObjects;
    }

    /**
     * This method is used to add the data from Towar table
     * to the array list of dataBaseObjects.
     * @return selected data from Towar table.
     */
    public ArrayList<DataBaseObject> selectTowar() {
        dataBaseObjects = dataAccessObject.selectTowar();
        return dataBaseObjects;
    }

    /**
     * This method is used refresh table depending on the 0 element of the dataBaseObjects.
     * @return different types of dataBaseObjects depending on instance of object.
     * In other case, null.
     */
    public ArrayList<DataBaseObject> refreshTable() {
        if(dataBaseObjects.get(0) instanceof Klient) {
            return dataAccessObject.selectKlient();
        } else if (dataBaseObjects.get(0) instanceof Towar) {
            return dataAccessObject.selectTowar();
        } else if (dataBaseObjects.get(0) instanceof Zamowienie) {
            return dataAccessObject.selectZamowienie();
        } if (dataBaseObjects.get(0) instanceof ElementZamowienia) {
            return dataAccessObject.selectElementZamowienia();
        }
        return null;
    }

    /**
     * This method is used to check if it is possible to add/modify row(object).
     * @param map is the map of headers and values.
     * @throws SQLException
     */
    public void addOrModifyDataBaseObject(Map<String, Object> map) throws SQLException {
        if(dataBaseObjects.get(0) instanceof Klient) {
            Klient klient = new Klient(map);
            System.out.println(klient.toString());
            if(klient.getClientID() == -1) {
                dataAccessObject.addKlient(klient);
            } else {
                System.out.println(klient.getClientID()+"IS");
                dataAccessObject.updateKlient(klient);
            }
        } else if (dataBaseObjects.get(0) instanceof Towar) {
            Towar towar = new Towar(map);
            System.out.println(towar.toString());
            if(towar.getItemId() == -1) {
                dataAccessObject.addTowar(towar);
            } else {
                System.out.println(towar.getItemId()+"IS");
                dataAccessObject.updateTowar(towar);
            }
        } else if (dataBaseObjects.get(0) instanceof Zamowienie) {
            Zamowienie zamowienie = new Zamowienie(map);
            System.out.println(zamowienie.toString());
            if(zamowienie.getInvoiceID() == -1) {
                dataAccessObject.addZamowienie(zamowienie);
            } else {
                System.out.println(zamowienie.getInvoiceID()+"IS");
                dataAccessObject.updateZamowienie(zamowienie);
            }
        } if (dataBaseObjects.get(0) instanceof ElementZamowienia) {
            ElementZamowienia elementZamowienia = new ElementZamowienia(map);
            System.out.println(elementZamowienia.toString());
            if(elementZamowienia.getInvoiceElementID() == -1) {
                dataAccessObject.addElement(elementZamowienia);
            } else {
                System.out.println(elementZamowienia.getInvoiceElementID()+"IS");
                dataAccessObject.updateElement(elementZamowienia);
            }
        }
    }

    /**
     * This method is used to check if it is possible to delete row(object).
     * @param objectID is the unique id of every object in given table.
     * @throws SQLException
     */
    public void deleteObject(int objectID) throws SQLException {
        if(dataBaseObjects.get(0) instanceof Klient) {
            dataAccessObject.deleteKlient(objectID);
        } else if(dataBaseObjects.get(0) instanceof Zamowienie) {
            dataAccessObject.deleteZamowienie(objectID);
        } else if(dataBaseObjects.get(0) instanceof ElementZamowienia) {
            dataAccessObject.deleteElementZamowienia(objectID);
        } else if (dataBaseObjects.get(0) instanceof Towar) {
            dataAccessObject.deleteTowar(objectID);
        }
    }

    /**
     * This method is used get every object depending on the id.
     * @param id is the unique id of every object in given table.
     * @return dataBaseObject if dataBaseObject id is equal to id.
     * In other case, null.
     */
    public DataBaseObject getObjectById(int id) {
        for(DataBaseObject dataBaseObject : dataBaseObjects) {
            if(dataBaseObject.getID() == id) {
                return dataBaseObject;
            }
        }

        return null;
    }

}

