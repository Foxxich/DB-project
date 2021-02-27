package org.example;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is used to create elements of invoice.
 * Also it is an example of Low coupling and High cohesion class.
 * It implements the methods from  interface DataBaseObject.
 */
public class ElementZamowienia implements DataBaseObject {

    /**
     * quantity is the number of chosen same items.
     * invoiceElementID is the id of each invoice element.
     * invoiceID is the id of invoice.
     * price is the price of 1 item.
     * itemID is the id of every item.
     * discount is the percent which is makes price total lower.
     */
    private int quantity;
    private int invoiceElementID = -1;
    private int invoiceID;
    private double price;
    private int itemID;
    private int discount;

    /**
     * This method is used to get all these parameters.
     * @param invoiceElementID is the id of invoice element.
     * @param invoiceID is the id of invoice.
     * @param price is the price of 1 item.
     * @param quantity is the number of chosen same items.
     * @param itemID is the id of every item.
     * @param discount is the percent which is makes price total lower.
     */
    public ElementZamowienia(int invoiceElementID, int invoiceID, double price, int quantity, int itemID, int discount) {
        this.invoiceElementID = invoiceElementID;
        this.invoiceID = invoiceID;
        this.price = price;
        this.quantity = quantity;
        this.itemID = itemID;
        this.discount = discount;
    }

    /**
     * This method is used to set the map.
     * @param map is a map of Strings(headers) and Objects(rows) of the invoice element.
     */
    public ElementZamowienia(Map<String, Object> map) {
        this.setFromMap(map);
    }

    /**
     * This method is used to get the id of invoice element.
     * @return invoiceElementID.
     */
    public int getInvoiceElementID() {
        return invoiceElementID;
    }

    /**
     * This method is used to get the id of invoice.
     * @return invoiceID.
     */
    public int getInvoiceID() { return invoiceID; }

    /**
     * This method is used to get price of all items in the invoice element and refactor it in given format.
     * It depends on the quantity, price and discount.
     * In this case, x.00.
     * @return priceToString.
     */
    public String getTotalPrice() {
        double totalPrice = quantity * price * ((100 - discount) / 100.0 );
        String priceToString = new DecimalFormat("#0.00").format(totalPrice);
        return priceToString;
    }

    /**
     * This method is used to get price of one item in the invoice element.
     * @return price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method is used to get price of one item in the invoice element and refactor it in given format.
     * In this case, x.00.
     * @return priceToString.
     */
    public String getPriceAsString() {
        String priceToString = new DecimalFormat("#0.00").format(price);
        return priceToString;
    }

    /**
     * This method is used to get quantity of items in the invoice element.
     * @return quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method is used to get id of item.
     * @return itemID.
     */
    public int getItemID() { return itemID; }

    /**
     * This method is used to get discount.
     * @return discount.
     */
    public int getDiscount() { return discount; }

    /**
     * This method is used to get every row of values as a new object.
     * @return new object(in this case invoice element) which consists of invoiceElementID, invoiceID,
     * price of one item, quantity, itemID, discount and the total price of chosen items related to this invoice element.
     */
    @Override
    public Object[] getAsObject() {
        return new Object[] {
                this.invoiceElementID,
                this.invoiceID,
                this.getPriceAsString(),
                this.quantity,
                this.itemID,
                this.discount,
                this.getTotalPrice()
        };
    }

    /**
     * This method is used to get headers of the invoice table.
     * @return headers.
     */
    @Override
    public String[] getHeaders() {
        return new String[] {
                "InvoiceElementID",
                "InvoiceID",
                "Price",
                "Quantity",
                "ItemID",
                "Discount",
                "Total Price"
        };
    }

    /**
     * This method is used to get the id, in this case of invoice element.
     * It is done to make the program more generic.
     * @return the id of invoice element.
     */
    @Override
    public int getID() {
        return this.invoiceElementID;
    }

    /**
     * This method is used to get a map of headers and values in the given table.
     * @return header and value in the chosen column.
     */
    @Override
    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID",invoiceElementID);
        map.put("InvoiceID",invoiceID);
        map.put("Price",getPriceAsString());
        map.put("Quantity",quantity);
        map.put("ItemID",itemID);
        map.put("Discount",discount);
        return map;
    }

    /**
     * This method is used to set specified value from the correct column, depending on the name of header.
     * @param map of headers and specified vales.
     */
    @Override
    public void setFromMap(Map<String, Object> map) {
        this.invoiceElementID = (int) map.get("ID");
        this.invoiceID = Integer.parseInt(map.get("InvoiceID").toString());
        this.price = Double.parseDouble((String) map.get("Price"));
        this.quantity = Integer.parseInt((String) map.get("Quantity"));
        this.itemID = Integer.parseInt((String) map.get("ItemID"));
        this.discount = Integer.parseInt((String) map.get("Discount"));
    }
}
