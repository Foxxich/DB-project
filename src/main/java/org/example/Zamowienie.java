package org.example;

import java.time.LocalDateTime;
import java.util.List;
//Example of Low coupling and High cohesion class
public class Zamowienie implements DataBaseObject {
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

    @Override
    public Object[] getAsObject() {
        return new Object[] {
                this.invoiceID,
                this.time,
                this.state,
                this.clientID
        };
    }

    @Override
    public String[] getHeaders() {
        return new String[] {
                "InvoiceID",
                "Time",
                "State",
                "ClientID"
        };
    }
}
