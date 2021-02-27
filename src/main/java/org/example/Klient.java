package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is used to create clients.
 * Also it is an example of Low coupling and High cohesion class.
 * It implements the methods from  interface DataBaseObject.
 */
public class Klient implements DataBaseObject {

    /**
     * clientID is the id of client.
     * name is the name of client.
     * surname is the surname of client.
     * sex is the sex of client.
     * email is the email of client.
     * phone is the phone number of client.
     * dateBirth is the date of birth of client.
     */
    private int clientID;
    private String name;
    private String surname;
    private String sex;
    private String email;
    private String phone;
    private String dateBirth;

    /**
     * This method is used to get the id, name, surname,
     * sex, email, phone number and date of birth of the client.
     * @param clientID is the id of client.
     * @param name is the name of client.
     * @param surname is the surname of client.
     * @param sex is the sex of client.
     * @param dateBirth is the date of birth of client.
     * @param email is the email of client.
     * @param phone is the phone number of client.
     */
    public Klient(int clientID, String name, String surname, String sex, String dateBirth, String email, String phone) {
        this.clientID = clientID;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateBirth = dateBirth;
        this.email = email;
        this.phone = phone;
    }

    /**
     * This method is used to set the map.
     * @param map is a map of Strings(headers) and Objects(rows) of the client.
     */
    public Klient(Map<String, Object> map) {
        this.setFromMap(map);
    }

    /**
     *  This method is used to set the id of the client.
     * @param clientID is id of client.
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * This method is used to get the id of client.
     * @return the id of client.
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * This method is used to get the name of client.
     * @return the name of client.
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to get the surname of client.
     * @return the surname of client.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * This method is used to get the sex of client.
     * @return the sex of client.
     */
    public String getSex() { return sex; }

    /**
     * This method is used to get the email of client.
     * @return the email of client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method is used to get the phone number of client.
     * @return the phone number client.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method is used to get the date of birth of client.
     * @return the date of birth of client.
     */
    public String getDateBirth() {
        return dateBirth;
    }

    /**
     * This method is used to get every row of values as a new object.
     * @return new object(in this case client) which consists of clientID,
     * name, surname, sex, dateBirth, email and the phone of the client.
     */
    @Override
    public Object[] getAsObject() {
        return new Object[] {
                this.clientID,
                this.name,
                this.surname,
                this.sex,
                this.dateBirth,
                this.email,
                this.phone,
        };
    }

    /**
     * This method is used to get headers of the client table.
     * @return headers.
     */
    @Override
    public String[] getHeaders() {
        return new String[] {
                "ID",
                "Name",
                "Surname",
                "Sex",
                "DateBirth",
                "Email",
                "PhoneNumber"
        };
    }

    /**
     * This method is used to get the id, in this case of client.
     * It is done to make the program more generic.
     * @return the id of client.
     */
    @Override
    public int getID() {
        return this.clientID;
    }

    /**
     * This method is used to get a map of headers and values in the given table.
     * @return header and value in the chosen column.
     */
    @Override
    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID",clientID);
        map.put("Name",name);
        map.put("Surname",surname);
        map.put("Sex",sex);
        map.put("DateBirth(YYYY.MM.DD)",dateBirth);
        map.put("Email",email);
        map.put("PhoneNumber",phone);
        return map;
    }

    /**
     * This method is used to set specified value from the correct column, depending on the name of header.
     * @param map of headers and specified vales.
     */
    @Override
    public void setFromMap(Map<String, Object> map) {
        this.clientID = (int) map.get("ID");
        this.name = (String) map.get("Name");
        this.surname = (String) map.get("Surname");
        this.sex = (String) map.get("Sex");
        this.dateBirth = (String) map.get("DateBirth(YYYY.MM.DD)");
        this.email = (String) map.get("Email");
        this.phone = (String) map.get("PhoneNumber");
    }
}

