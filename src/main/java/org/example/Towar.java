package org.example;

import java.util.Map;

//Example of Low coupling and High cohesion class
public class Towar implements DataBaseObject {

    private String ean;
    private String name;
    private String producer;
    private String warranty;
    private int itemId;
    private int availability;

    public Towar(int itemId, String name, String ean, String producer, String warranty, int availability) {
        this.itemId = itemId;
        this.name = name;
        this.ean = ean;
        this.producer = producer;
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

    public String getProducer() {
        return producer;
    }

    public String getWarranty() {
        return warranty;
    }

    public int getAvailability() {
        return availability;
    }


    @Override
    public Object[] getAsObject() {
        return new Object[] {
                this.itemId,
                this.name,
                this.ean,
                this.producer,
                this.warranty,
                this.availability
        };
    }

    @Override
    public String[] getHeaders() {
        return new String[] {
                "ItemID",
                "Name",
                "EAN",
                "Producer",
                "Warranty",
                "Availability"
        };
    }

    @Override
    public int getID() {
        return this.itemId;
    }

    @Override
    public Map<String, Object> getAsMap() {
        return null;
    }

    @Override
    public void setFromMap(Map<String, Object> map) {

    }
}
