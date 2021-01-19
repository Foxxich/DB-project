package org.example;

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

    public Klient(String name, String surname, String sex, String dateBirth, String email, String phone) {
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
        return new Object[0];
    }

    @Override
    public String[] getHeaders() {
        return new String[0];
    }
}

