package org.example;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This class is used to create each item.
 * Also it is an example of Low coupling and High cohesion class.
 * It implements the methods from  interface DataBaseObject.
 */
public class Towar implements DataBaseObject {

    /**
     * ean is European Article Number.
     * name is the name of item.
     * producer is the company/person that produced chosen item.
     * warranty is the time of guarantee.
     * itemId is an unique id for each item.
     * availability  is a number of available items.
     */
    private String ean;
    private String name;
    private String producer;
    private String warranty;
    private int itemId;
    private int availability;

    /**
     * This method is used to get the ean, name, producer,
     * time of warranty, id of item and the number of available items.
     * @param itemId
     * @param name
     * @param ean
     * @param producer
     * @param warranty
     * @param availability
     */
    public Towar(int itemId, String name, String ean, String producer, String warranty, int availability) {
        this.itemId = itemId;
        this.name = name;
        this.ean = ean;
        this.producer = producer;
        this.warranty = warranty;
        this.availability = availability;
    }

    /**
     * This method is used to set the map.
     * @param map is a map of Strings(headers) and Objects(rows) of the item.
     */
    public Towar(Map<String, Object> map) {
        this.setFromMap(map);
    }

    /**
     * This method is used to get the id of item.
     * @return the item id.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * This method is used to get the name of item.
     * @return the item name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to get the ean of item.
     * @return the item ean.
     */
    public String getEan() {
        return ean;
    }

    /**
     * This method is used to get the producer of item.
     * @return the item producer.
     */
    public String getProducer() {
        return producer;
    }

    /**
     * This method is used to get the warranty of item.
     * @return the item warranty.
     */
    public String getWarranty() {
        return warranty;
    }

    /**
     * This method is used to get the availability of item.
     * @return the item availability.
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * This method is used to get every row of values as a new object.
     * @return new object(in this case item) which consists of itemId,
     * name, ean, producer, warranty and the availability of chosen item.
     */
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

    /**
     * This method is used to get headers of the item table.
     * @return headers.
     */
    @Override
    public String[] getHeaders() {
        return new String[] {
                "ID",
                "Name",
                "EAN",
                "Producer",
                "Warranty",
                "Availability"
        };
    }

    /**
     * This method is used to get the id, in this case of item.
     * It is done to make the program more generic.
     * @return the id of item.
     */
    @Override
    public int getID() {
        return this.itemId;
    }

    /**
     * This method is used to get a map of headers and values in the given table.
     * @return header and value in the chosen column.
     */
    @Override
    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID",itemId);
        map.put("Name",name);
        map.put("EAN",ean);
        map.put("Producer",producer);
        map.put("Warranty",warranty);
        map.put("Availability",availability);
        return map;
    }

    /**
     * This method is used to set specified value from the correct column, depending on the name of header.
     * @param map of headers and specified vales.
     */
    @Override
    public void setFromMap(Map<String, Object> map) {
        this.itemId = (int) map.get("ID");
        this.name = (String) map.get("Name");
        this.ean = (String) map.get("EAN");
        this.producer = (String) map.get("Producer");
        this.warranty = (String) map.get("Warranty");
        this.availability = Integer.parseInt((String) map.get("Availability"));
    }
}
