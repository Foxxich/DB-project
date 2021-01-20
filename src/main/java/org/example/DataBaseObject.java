package org.example;

import java.util.Map;

public interface DataBaseObject {
    public Object[] getAsObject();//zwraca caly obiekt w formie tablicy
    public String[] getHeaders();//zwraca nazwy kolumn
    public int getID();
    public Map<String,Object> getAsMap();
    public void setFromMap(Map<String,Object> map);
}
