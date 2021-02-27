package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is used to create JPanel for adding objects on it.
 * It extends JPanel, which will eventually be placed in a JFrame.
 */
public class Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable jTable = null;

    /**
     * This method is used for choosing the position where to put the panel.
     */
    public Panel() {
        jScrollPane.setSize(this.getWidth(),
                this.getHeight() - 100);
        add(jScrollPane, BorderLayout.CENTER);
    }

    /**
     * This method id used to set the table with the given array list of the objects.
     * @param dataBaseObjects is the array list of dataBaseObjects.
     */
    public void setTable(ArrayList<DataBaseObject> dataBaseObjects) {
        String[] columnNames = {};
        Object[][] objects = {};
        this.remove(jScrollPane);
        if(!dataBaseObjects.isEmpty()) {
            columnNames = dataBaseObjects.get(0).getHeaders();
            Collection<Object[]> collection = new ArrayList<>();
            for (DataBaseObject dataBaseObject : dataBaseObjects) {
                if(dataBaseObject.getID() != -1) {
                    collection.add(dataBaseObject.getAsObject());
                }
            }
            objects = collection.toArray(new Object[collection.size()][]);
            System.out.println("Object"+collection);
        }

        System.out.println("Column"+columnNames);
        jTable = new JTable(objects,columnNames){
            /**
             * This method is used to deny the possibility to modify every cell in Java application.
             * @param row is the row in given table.
             * @param column is the column in given table.
             * @return false (for better interaction and usability).
             */
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
       //this.add(jScrollPane);
        add(jScrollPane, BorderLayout.CENTER);
        validate();
        repaint();
    }

    /**
     * This method is used get id of the selected row.
     * @return id of the row if row >= 0. In other case, -1.
     */
    public int getSelectedObjectId() {
        int row = jTable.getSelectedRow();
        if(row != -1) {
            return Integer.parseInt(jTable.getModel().getValueAt(row, 0).toString());
        }

        return -1;
    }
}
