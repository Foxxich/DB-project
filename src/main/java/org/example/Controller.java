package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Controller {
    ArrayList<DataBaseObject> dataBaseObjects = new ArrayList<>();
    DataAccessObject dataAccessObject = new DataBaseSQL();

    public ArrayList<DataBaseObject> getDataBaseObjects() {
        return dataBaseObjects;
    }

    public void setDataBaseObjects(ArrayList<DataBaseObject> dataBaseObjects) {
        this.dataBaseObjects = dataBaseObjects;
    }

    public boolean logToDatabase(String login, String password) {
        return dataAccessObject.checkUser(login, password);
    }

    public ArrayList<DataBaseObject> selectKlient() {
        dataBaseObjects = dataAccessObject.selectKlient();
        return dataBaseObjects;
    }

    public ArrayList<DataBaseObject> selectZamowienie() {
        dataBaseObjects = dataAccessObject.selectZamowienie();
        return dataBaseObjects;
    }

    public ArrayList<DataBaseObject> selectElementZamowienia() {
        dataBaseObjects = dataAccessObject.selectElementZamowienia();
        return dataBaseObjects;
    }

    public ArrayList<DataBaseObject> selectTowar() {
        dataBaseObjects = dataAccessObject.selectTowar();
        return dataBaseObjects;
    }

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

    public void deleteObject(int objectID) {
        if(dataBaseObjects.get(0) instanceof Klient) {
            dataAccessObject.deleteKlient(objectID);
        }
    }

    public DataBaseObject getObjectById(int id) {
        for(DataBaseObject dataBaseObject : dataBaseObjects) {
            if(dataBaseObject.getID() == id) {
                return dataBaseObject;
            }
        }

        return null;
    }

}

