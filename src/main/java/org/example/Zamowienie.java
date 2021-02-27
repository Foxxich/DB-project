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
 * It implements the methods from  interface DataBaseObject.
 */
public class Zamowienie implements DataBaseObject {

    /**
     * clientID is the id of client.
     * invoiceID is the id of the chosen invoice.
     * state is the state of invoice,
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

    /**
     * This method is used to get the id of client,
     * id of invoice, state of invoice
     * and time of the chosen invoice.
     * @param invoiceID is the id of chosen invoice.
     * @param time is the time when there were provided manipulations with the invoice.
     * @param state is the state of invoice.
     * @param clientID is the id of client.
     */
    public Zamowienie(int invoiceID, String time, String state,int clientID) {
        this.clientID = clientID;
        this.invoiceID = invoiceID;
        this.state = state;
        this.time = time;
    }

    /**
     * This method is used to set the map.
     * @param map is a map of Strings(headers) and Objects(rows) of the invoice.
     */
    public Zamowienie(Map<String, Object> map) {
        this.setFromMap(map);
    }

    /**
     * This method is used to set the id of the invoice.
     * @param invoiceID is id of invoice.
     */
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    /**
     * This method is used to get the id of client.
     * @return the client id.
     */
    public int getClientId() {
        return clientID;
    }

    /**
     * This method is used to get the id of invoice.
     * @return the invoice id.
     */
    public int getInvoiceID() {
        return invoiceID;
    }

    /**
     * This method is used to get the state of invoice.
     * @return the state of invoice.
     */
    public String getInvoiceState() {
        return state;
    }

    /**
     * This method is used to get the time when there were any changes of invoice.
     * @return the time of any changes with invoice.
     */
    public String getInvoiceTime() {
        return time;
    }

    /**
     * This method is used to set the list of elements of current invoice.
     * @param elements is a list of all the rows related to the invoice.
     */
    public void setElements(List<ElementZamowienia> elements) {
        this.elements = elements;
    }

    /**
     * This method is used to get the price of all the items related to current invoice.
     * @return the price of chosen items in String.
     */
    public String getTotalPrice() {
        double totalPrice = 0;
        for (ElementZamowienia elementZamowienia : elements) {
            totalPrice += Double.parseDouble(elementZamowienia.getTotalPrice());
        }
        String priceToString = new DecimalFormat("#0.00").format(totalPrice);
        return priceToString;
    }

    /**
     * This method is used to get every row of values as a new object.
     * @return new object(in this case invoice) which consists of invoiceID,
     * time, state, clientID and the price of chosen items related to this invoice.
     */
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

    /**
     * This method is used to get headers of the invoice table.
     * @return headers.
     */
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

    /**
     * This method is used to get the id, in this case of invoice.
     * It is done to make the program more generic.
     * @return the id of invoice.
     */
    @Override
    public int getID() {
        return this.invoiceID;
    }

    /**
     * This method is used to get a map of headers and values in the given table.
     * @return header and value in the chosen column.
     */
    @Override
    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID",invoiceID);
        map.put("State",state);
        map.put("ClientID",clientID);
        return map;
    }

    /**
     * This method is used to set specified value from the correct column, depending on the name of header.
     * @param map of headers and specified vales.
     */
    @Override
    public void setFromMap(Map<String, Object> map) {
        this.invoiceID = (int) map.get("ID");
        this.state = (String) map.get("State");
        this.clientID = Integer.parseInt((String) map.get("ClientID"));
    }
}
