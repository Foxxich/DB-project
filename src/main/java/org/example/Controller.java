package org.example;

import java.util.ArrayList;

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
        return dataAccessObject.selectKlient();
    }

    public ArrayList<DataBaseObject> selectZamowienie() {
        return dataAccessObject.selectZamowienie();
    }

    public ArrayList<DataBaseObject> selectElementZamowienia() {
        return dataAccessObject.selectElementZamowienia();
    }

    public ArrayList<DataBaseObject> selectTowar() {
        return dataAccessObject.selectTowar();
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
}

