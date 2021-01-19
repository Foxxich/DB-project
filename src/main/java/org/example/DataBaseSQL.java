package org.example;

import java.sql.*;

public class DataBaseSQL implements DataAccessObject
//implements DataAccessObject
{

    private static final String dbURL = "jdbc:mariadb://localhost/zakupy";
    private String user;
    private String password;

    public DataBaseSQL(String user, String password) {
        this.user = user;
        this.password = password;
    }


    @Override
    public void selectKlient() {
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
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void selectZamowienie() {
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
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void selectElementZamowienia() {
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
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void selectTowar() {
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
            }
            rs.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void addTowar(Towar towar) {
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
        }
    }

    @Override
    public void addKlient(Klient klient) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call addTowar(?, ?, ?, ?, ?, ?}");
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
        }
    }

    @Override
    public void addZamowienie(Zamowienie zamowienie) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call addTowar(?, ?}");
        ) {
            statement.setString(1, zamowienie.getInvoiceState());
            statement.setInt(2, zamowienie.getClientId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addElement(ElementZamowienia elementZamowienia) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call addTowar(?, ?, ?, ?, ?}");
        ) {
            statement.setInt(1, elementZamowienia.getInvoiceID());
            statement.setDouble(2, elementZamowienia.getPrice());
            statement.setInt(3, elementZamowienia.getQuantity());
            statement.setInt(4, elementZamowienia.getItemID());
            statement.setInt(5, elementZamowienia.getDiscount());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateTowar(Towar towar) {
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
        }
    }

    @Override
    public void updateKlient(Klient klient) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call updateTowar(?, ?, ?, ?, ?, ?, ?)}");
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
        }
    }

    @Override
    public void updateZamowienie(Zamowienie zamowienie) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call updateTowar(?, ?, ?)}");
        ) {
            statement.setInt(1, zamowienie.getClientId());
            statement.setString(2, zamowienie.getInvoiceState());
            statement.setInt(3, zamowienie.getClientId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateElement(ElementZamowienia elementZamowienia) {
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call updateTowar(?, ?, ?, ?, ?, ?)}");
        ) {
            statement.setInt(1, elementZamowienia.getInvoiceID());
            statement.setInt(2, elementZamowienia.getInvoiceElementID());
            statement.setDouble(3, elementZamowienia.getPrice());
            statement.setInt(4, elementZamowienia.getQuantity());
            statement.setInt(5, elementZamowienia.getItemID());
            statement.setInt(6, elementZamowienia.getDiscount());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
}
