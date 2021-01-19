package org.example;

//Example of Low coupling and High cohesion class
public class Towar implements DataBaseObject {

    private String ean;
    private String name;
    private String producent;
    private String warranty;
    private int itemId;
    private int availability;

    public Towar(int itemId, String name, String ean, String producer, String warranty, int availability) {
        this.itemId = itemId;
        this.name = name;
        this.ean = ean;
        this.producent = producer;
        this.warranty = warranty;
        this.availability = availability;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getEan() {
        return ean;
    }

    public String getProducent() {
        return producent;
    }

    public String getWarranty() {
        return warranty;
    }

    public int getAvailability() {
        return availability;
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
