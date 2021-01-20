package org.example;

import javax.swing.*;
import java.awt.*;
;
import java.util.ArrayList;
import java.util.Collection;

public class Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Row rows = new Row();
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable jTable = null;

    public Panel() {
        jScrollPane.setSize(this.getWidth(),
                this.getHeight() - 100);
        add(jScrollPane, BorderLayout.CENTER);
    }

    public void setTable(ArrayList<DataBaseObject> dataBaseObjects) {
        String[] columnNames = dataBaseObjects.get(0).getHeaders();
        Collection<Object[]> collection = new ArrayList<>();
        for(DataBaseObject dataBaseObject : dataBaseObjects) {
            collection.add(dataBaseObject.getAsObject());
        }
        Object[][] objects = collection.toArray(new Object[collection.size()][]);
        jTable = new JTable(objects,columnNames);
        this.add(new JScrollPane(jTable));
    }

}
