package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to manipulate and modify the rows.
 */
public class Row implements Serializable {
    /**
     * valueMap is a map which contains String headers and String values related to the same column.
     */
    private Map<String, String> valueMap = new HashMap<>();

    /**
     * This method is used to unite every row with the headers.
     * @param headers are headers of the chosen table.
     */
    Row(List<String> headers){
        for (String header : headers) {
            valueMap.put(header, "");
        }
    }

    /**
     * This method is used to throw exceptions.
     * @param headers are headers of the chosen table.
     * @param values are values related to the same column as headers.
     */
    Row(String[] headers, String[] values){
        throw new IllegalStateException();
    }

    /**
     * This method is used to set every value which is at the headers column.
     * @param header are headers of the chosen table.
     * @param value are values related to the same column as headers.
     */
    void setValue(String header, String value) {
        valueMap.put(header, value);
    }

    /**
     * This method is used to remove specified value which depends on the column(header).
     * @param header are headers of the chosen table.
     */
    void removeValue(String header) {
        valueMap.remove(header);
    }

    /**
     * This method is used to edit specified value which depends on the column(header).
     * @param header are headers of the chosen table.
     * @param value value are values related to the same column as headers.
     */
    void editValue(String header, String value) {
        valueMap.replace(header, value);
    }

    /**
     * This method is used to get specified value which depends on the column(header).
     * @param header are headers of the chosen table.
     * @return specified value.
     */
    String getValue(String header) {
        return valueMap.get(header);
    }

    /**
     * This method is used to cehck up if the given header exists in the chosen table.
     * @param header header  are headers of the chosen table
     * @return true if the table contains given header. In other case - false.
     */
    boolean containsHeader(String header) {
        return valueMap.containsKey(header);
    }
}
