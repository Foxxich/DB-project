package org.example;

import java.util.Map;

/**
 * This interface is used for protected variation.
 */
public interface DataBaseObject {

    /**
     * getAsObject is used to return the whole object as a table.
     * getHeaders  is used to return the headers of a table.
     * getID is used to get id of the object.
     * getAsMap is a map of objects and headers.
     * @return getAsObject,getHeaders,getID,getAsMap
     */
    Object[] getAsObject();//zwraca caly obiekt w formie tablicy
    String[] getHeaders();//zwraca nazwy kolumn
    int getID();
    Map<String,Object> getAsMap();

    /**
     * This method is used to set table using given headers and objects.
     * @param map is a map of headers and objects.
     */
    void setFromMap(Map<String,Object> map);
}
