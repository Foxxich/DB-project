package org.example;

import javax.swing.*;
import java.awt.*;
;
import java.util.ArrayList;
import java.util.Collection;

public class Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable jTable = null;

    public Panel() {
        jScrollPane.setSize(this.getWidth(),
                this.getHeight() - 100);
        add(jScrollPane, BorderLayout.CENTER);
    }

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
        jTable = new JTable(objects,columnNames);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
       //this.add(jScrollPane);
        add(jScrollPane, BorderLayout.CENTER);
        validate();
        repaint();
    }

    public int getSelectedObjectId() {
        int row = jTable.getSelectedRow();
        if(row != -1) {
            return Integer.parseInt(jTable.getModel().getValueAt(row, 0).toString());
        }

        return -1;
    }
}
