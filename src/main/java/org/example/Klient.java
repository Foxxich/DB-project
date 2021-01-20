package org.example;

import java.util.HashMap;
import java.util.Map;

//Example of Low coupling and High cohesion class
public class Klient implements DataBaseObject {
    private int clientID;
    private String name;
    private String surname;
    private String sex;
    private String email;
    private String phone;
    private String dateBirth;

    public Klient(int clientID, String name, String surname, String sex, String dateBirth, String email, String phone) {
        this.clientID = clientID;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateBirth = dateBirth;
        this.email = email;
        this.phone = phone;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getClientID() {
        return clientID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() { return sex; }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    @Override
    public String toString() {
        return clientID + " " + name + " " + surname;
    }

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

    @Override
    public int getID() {
        return this.clientID;
    }

    @Override
    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("ID",clientID);
        map.put("Name",name);
        map.put("Surname",surname);
        map.put("Sex",sex);
        map.put("DateBirth",dateBirth);
        map.put("Email",email);
        map.put("PhoneNumber",phone);
        return map;
    }

    @Override
    public void setFromMap(Map<String, Object> map) {

    }
}

