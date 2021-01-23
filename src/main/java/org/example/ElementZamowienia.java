package org.example;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

//Example of Low coupling and High cohesion class
public class ElementZamowienia implements DataBaseObject {
    private int quantity;
    private int invoiceElementID = -1;
    private int invoiceID;
    private double price;
    private int itemID;
    private int discount;

    public ElementZamowienia(int invoiceElementID, int invoiceID, double price, int quantity, int itemID, int discount) {
        this.invoiceElementID = invoiceElementID;
        this.invoiceID = invoiceID;
        this.price = price;
        this.quantity = quantity;
        this.itemID = itemID;
        this.discount = discount;
    }

    public ElementZamowienia(Map<String, Object> map) {
        this.setFromMap(map);
    }

    public void setInvoiceElementID(int invoiceElementID) {
        this.invoiceElementID = invoiceElementID;
    }

    public int getInvoiceElementID() {
        return invoiceElementID;
    }

    public int getInvoiceID() { return invoiceID; }

    public String getTotalPrice() {
        double totalPrice = quantity * price * ((100 - discount) / 100.0 );
        String priceToString = new DecimalFormat("#0.00").format(totalPrice);
        return priceToString;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceAsString() {
        String priceToString = new DecimalFormat("#0.00").format(price);
        return priceToString;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getItemID() { return itemID; }

    public int getDiscount() { return discount; }

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

    @Override
    public int getID() {
        return this.invoiceElementID;
    }

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
