package org.example;

//Example of Low coupling and High cohesion class
public class ElementZamowienia implements DataBaseObject {
    private int quantity;
    private int invoiceElementID = -1;
    private int invoiceID;
    private double price;
    private double totalPrice;
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

    public ElementZamowienia(int invoiceID, double price, int quantity, int itemID, int discount) {
        this.invoiceID = invoiceID;
        this.price = price;
        this.quantity = quantity;
        this.itemID = itemID;
        this.discount = discount;
    }

    public void setInvoiceElementID(int invoiceElementID) {
        this.invoiceElementID = invoiceElementID;
    }

    public int getInvoiceElementID() {
        return invoiceElementID;
    }

    public int getInvoiceID() { return invoiceID; }

    public double getPrice() {
        return  quantity * price * ((100 - discount) / 100.0 );
    }

    public int getQuantity() {
        return quantity;
    }

    public int getItemID() { return itemID; }

    public int getDiscount() { return discount; }

    @Override
    public Object[] getAsObject() {
        return new Object[0];
    }

    @Override
    public String[] getHeaders() {
        return new String[0];
    }
}
