package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ModifyFrame extends JFrame {

    //TODO - change dialog
    JButton ok,cancel;

    Map<String,Object> valueMap = null;

    public ModifyFrame(Map<String, Object> valueMap) throws HeadlessException {
        super("Modify");
        ok = new JButton();
        cancel = new JButton();
        this.add(ok,BorderLayout.PAGE_END);
        this.add(cancel,BorderLayout.PAGE_END);
        this.valueMap = valueMap;
    }

    private void createForm() {
        //TODO - create form from map
    }

}
