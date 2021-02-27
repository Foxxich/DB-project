package org.example;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DataBaseSQL implements DataAccessObject
//implements DataAccessObject
{
    private static final String dbURL = "jdbc:mariadb://localhost/zakupy";
    private String user;
    private String password;

    @Override
    public boolean checkUser(String user, String password) {
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            if(conn != null) {
                this.user = user;
                this.password = password;
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean returnHaslo() {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{ ? = call returnHaslo(?,?)}");
        ) {
            statement.registerOutParameter(1,Types.VARCHAR);
            statement.setString(2, password);
            statement.setString(3, user);
            statement.execute();
            if(statement.getString(1) != null) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<DataBaseObject> selectKlient() {
        ArrayList<DataBaseObject> dataBaseObjects = new ArrayList<>();
        dataBaseObjects.add(new Klient(-1,null,null,null,null,null,null));
        try
        {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT Id, Imie, Nazwisko, Plec, Data_urodzenia, Email, Telefon FROM Klient";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Imie");
                String surname = rs.getString("Nazwisko");
                String sex = rs.getString("Plec");
                String date = rs.getString("Data_urodzenia");
                String email = rs.getString("Email");
                String phone = rs.getString("Telefon");
                dataBaseObjects.add(new Klient(id,name,surname,sex,date,email,phone));
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return dataBaseObjects;
    }

    @Override
    public ArrayList<DataBaseObject> selectZamowienie() {
        ArrayList<DataBaseObject> dataBaseObjects = new ArrayList<>();
        dataBaseObjects.add(new Zamowienie(-1,null,null,0));
        try
        {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT Id, Czas_zlozenia, Stan, Id_klienta FROM Zamowienia";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("Id");
                String time = rs.getString("Czas_zlozenia");
                String state = rs.getString("Stan");
                int clientID = rs.getInt("Id_klienta");
                dataBaseObjects.add(new Zamowienie(id,time,state,clientID));
            }
            rs.close();
            for(DataBaseObject dataBaseObject : dataBaseObjects) {
                if(dataBaseObject.getID() != -1) {
                    ArrayList<ElementZamowienia> dataBaseObjectArrayList = selectElementZamowieniaByZamowieniaId(dataBaseObject.getID());
                    ((Zamowienie) dataBaseObject).setElements(dataBaseObjectArrayList);
                }
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return dataBaseObjects;
    }

    private ArrayList<ElementZamowienia> selectElementZamowieniaByZamowieniaId(int idZamowienia) {
        ArrayList<ElementZamowienia> dataBaseObjects = new ArrayList<>();
        try
        {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT Id, Id_zamowienia, Cena_produktu, Ilosc_produktow, Id_produktu, Znizka FROM Element_zamowienia WHERE Id_zamowienia = "+idZamowienia;
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("Id");
                int invoiceID = rs.getInt("Id_zamowienia");
                double price = rs.getDouble("Cena_produktu");
                int quantity = rs.getInt("Ilosc_produktow");
                int itemID = rs.getInt("Id_produktu");
                int discount = rs.getInt("Znizka");
                dataBaseObjects.add(new ElementZamowienia(id,invoiceID,price,quantity,itemID,discount));
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return dataBaseObjects;
    }


    @Override
    public ArrayList<DataBaseObject> selectElementZamowienia() {
        ArrayList<DataBaseObject> dataBaseObjects = new ArrayList<>();
        dataBaseObjects.add(new ElementZamowienia(-1,0,0,0,0,0));
        try
        {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT Id, Id_zamowienia, Cena_produktu, Ilosc_produktow, Id_produktu, Znizka FROM Element_zamowienia";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("Id");
                int invoiceID = rs.getInt("Id_zamowienia");
                double price = rs.getDouble("Cena_produktu");
                int quantity = rs.getInt("Ilosc_produktow");
                int itemID = rs.getInt("Id_produktu");
                int discount = rs.getInt("Znizka");
                dataBaseObjects.add(new ElementZamowienia(id,invoiceID,price,quantity,itemID,discount));
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return dataBaseObjects;
    }

    @Override
    public ArrayList<DataBaseObject> selectTowar() {
        ArrayList<DataBaseObject> dataBaseObjects = new ArrayList<>();
        dataBaseObjects.add(new Towar(-1,null,null,null,null,0));
        try
        {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT Id, Nazwa, EAN, Producent, Gwarancja, Dostepnosc FROM Towar";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Nazwa");
                String ean = rs.getString("EAN");
                String producent = rs.getString("Producent");
                String warranty = rs.getString("Gwarancja");
                int availability = rs.getInt("Dostepnosc");
                dataBaseObjects.add(new Towar(id,name,ean,producent,warranty,availability));
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return dataBaseObjects;
    }

    @Override
    public void addTowar(Towar towar) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call addTowar(?, ?, ?, ?, ?)}");
        ) {
            statement.setString(1, towar.getName());
            statement.setString(2, towar.getEan());
            statement.setString(3, towar.getProducer());
            statement.setString(4, towar.getWarranty());
            statement.setInt(5, towar.getAvailability());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void addKlient(Klient klient) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call addKlient(?, ?, ?, ?, ?, ?)}");
        ) {
            statement.setString(1, klient.getName());
            statement.setString(2, klient.getSurname());
            statement.setString(3, klient.getSex());
            statement.setString(4, klient.getDateBirth());
            statement.setString(5, klient.getEmail());
            statement.setString(6, klient.getPhone() );
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void addZamowienie(Zamowienie zamowienie) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call addZamowienie(?, ?)}");
        ) {
            statement.setString(1, zamowienie.getInvoiceState());
            statement.setInt(2, zamowienie.getClientId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void addElement(ElementZamowienia elementZamowienia) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call addElement(?, ?, ?, ?, ?)}");
        ) {
            statement.setInt(1, elementZamowienia.getInvoiceID());
            statement.setDouble(2, elementZamowienia.getPrice());
            statement.setInt(3, elementZamowienia.getQuantity());
            statement.setInt(4, elementZamowienia.getItemID());
            statement.setInt(5, elementZamowienia.getDiscount());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void updateTowar(Towar towar) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call updateTowar(?, ?, ?, ?, ?, ?)}");
        ) {
            statement.setInt(1, towar.getItemId());
            statement.setString(2, towar.getName());
            statement.setString(3, towar.getEan());
            statement.setString(4, towar.getProducer());
            statement.setString(5, towar.getWarranty());
            statement.setInt(6, towar.getAvailability());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void updateKlient(Klient klient) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call updateKlient(?, ?, ?, ?, ?, ?, ?)}");
        ) {
            statement.setInt(1, klient.getClientID());
            statement.setString(2, klient.getName());
            statement.setString(3, klient.getSurname());
            statement.setString(4, klient.getSex());
            statement.setString(5, klient.getDateBirth());
            statement.setString(6, klient.getEmail());
            statement.setString(7, klient.getPhone() );
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void updateZamowienie(Zamowienie zamowienie) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call updateZamowienie(?, ?, ?)}");
        ) {
            statement.setInt(1, zamowienie.getInvoiceID());
            statement.setString(2, zamowienie.getInvoiceState());
            statement.setInt(3, zamowienie.getClientId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void updateElement(ElementZamowienia elementZamowienia) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call updateElement(?, ?, ?, ?, ?, ?)}");
        ) {
            statement.setInt(1, elementZamowienia.getInvoiceElementID());
            statement.setInt(2, elementZamowienia.getInvoiceID());
            statement.setDouble(3, elementZamowienia.getPrice());
            statement.setInt(4, elementZamowienia.getQuantity());
            statement.setInt(5, elementZamowienia.getItemID());
            statement.setInt(6, elementZamowienia.getDiscount());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void returnZamowienia(Klient klient) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{ ? = call returnZamowienia(?)}");
        ) {
            statement.registerOutParameter(1,Types.VARCHAR);
            statement.setInt(2, klient.getClientID());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void returnElementyZamowienia(Zamowienie zamowienie) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{ ? = call returnElementyZamowienia(?)}");
        ) {
            statement.registerOutParameter(1,Types.VARCHAR);
            statement.setInt(2, zamowienie.getInvoiceID());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void returnElementyZeZnizka(ElementZamowienia elementZamowienia) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{ ? = call returnElementyZeZnizka(?)}");
        ) {
            statement.registerOutParameter(1,Types.VARCHAR);
            statement.setInt(2, elementZamowienia.getDiscount());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void returnZamowieniaZTowarem(Towar towar) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{ ? = call returnZamowieniaZTowarem(?)}");
        ) {
            statement.registerOutParameter(1,Types.VARCHAR);
            statement.setInt(2, towar.getItemId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteKlient(int klientID) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM Klient WHERE Id = "+klientID;
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void deleteZamowienie(int zamowienieID) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM Zamowienia WHERE Id = "+zamowienieID;
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void deleteElementZamowienia(int elementZamowieniaID) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM Element_zamowienia WHERE Id = "+elementZamowieniaID;
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void deleteTowar(int towarID) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM Towar WHERE Id = " + towarID;
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
