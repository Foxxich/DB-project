package org.example;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to create invoices.
 * Also it is an example of Low coupling and High cohesion class.
 */
public class Zamowienie implements DataBaseObject {

    /**
     * clientID is the id of every client.
     * invoiceID is the id of every invoice.
     * state is the state of every invoice,
     * it could be ('Oplacone','Dostarczone','Wyslane','Przygotowane','Zlozone','Odwolane').
     * time is the time when the were provided different operations with this invoice,
     * for example added new items to it or the state was changed.
     * elements is the list of all elements of this invoice.
     */
    private int clientID;
    private int invoiceID;
    private String state;
    private String time;
    private List<ElementZamowienia> elements;

    public Zamowienie(int invoiceID, String time, String state,int clientID) {
        this.clientID = clientID;
        this.invoiceID = invoiceID;
        this.state = state;
        this.time = time;
    }

    public Zamowienie(Map<String, Object> map) {
        this.setFromMap(map);
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getClientId() {
        return clientID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public String getInvoiceState() {
        return state;
    }

    public String getInvoiceTime() {
        return time;
    }

    public void setElements(List<ElementZamowienia> elements) {
        this.elements = elements;
    }

    public String getTotalPrice() {
        double totalPrice = 0;
        for (ElementZamowienia elementZamowienia : elements) {
            totalPrice += Double.parseDouble(elementZamowienia.getTotalPrice());
        }
        String priceToString = new DecimalFormat("#0.00").format(totalPrice);
        return priceToString;
    }

    @Override
    public Object[] getAsObject() {
        return new Object[] {
                this.invoiceID,
                this.time,
                this.state,
                this.clientID,
                this.getTotalPrice()
        };
    }

    @Override
    public String[] getHeaders() {
        return new String[] {
                "ID",
                "Time",
                "State",
                "ClientID",
                "Total Price"
        };
    }

    @Override
    public int getID() {
        return this.invoiceID;
    }

    @Override
    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID",invoiceID);
        map.put("State",state);
        map.put("ClientID",clientID);
        return map;
    }

    @Override
    public void setFromMap(Map<String, Object> map) {
        this.invoiceID = (int) map.get("ID");
        this.state = (String) map.get("State");
        this.clientID = Integer.parseInt((String) map.get("ClientID"));
    }
}
