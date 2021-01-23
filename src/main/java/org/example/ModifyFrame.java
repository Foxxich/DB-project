package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModifyFrame extends JDialog {
    //TODO - add action listenetr to "Cancel"
    //TODO - handle dispose by exit button(make value map null) //outDispose method if using key or not exist X in frame
    JButton ok,cancel;
    JPanel panel;

    Map<String,Object> valueMap = null;
    Map<String, JTextField> inputMap;

    public ModifyFrame(Map<String, Object> valueMap, Frame parent) throws HeadlessException {
        super(parent,"Modify", true);
        this.setSize(250, valueMap.keySet().size() * 62);
        this.setResizable(false);
        ok = new JButton("Ok");
        ok.addActionListener(e -> {
            for(String key : valueMap.keySet()) {
                if(!key.equals("ID")) {
                    Object replaceValue = null;
                    if(!inputMap.get(key).getText().isEmpty()) {
                        replaceValue = inputMap.get(key).getText();
                    }
                    valueMap.replace(key, replaceValue);
                }
            }

            this.dispose();
        });
        cancel = new JButton("Cancel");
        panel = new JPanel();
        panel.setLayout(new FlowLayout());


        this.inputMap = new LinkedHashMap<>();
        this.valueMap = valueMap;
        createForm();

        panel.add(ok);
        panel.add(cancel);

        setContentPane(panel);
    }

    private void createForm() {
        Dimension preferredDimension = new Dimension(this.getWidth() - 30, 20);
        for(String key : valueMap.keySet()) {           //TODO dodać obsługę datepickera, albo wskazać format w nagłówku

            JLabel label = new JLabel(key);
            label.setPreferredSize(preferredDimension);
            panel.add(label);

            JTextField textField = new JTextField();
            textField.setPreferredSize(preferredDimension);
            if(valueMap.get(key) != null) {
                if (!(key.equals("ID") && Integer.parseInt(valueMap.get(key).toString()) == -1)) {
                    textField.setText(valueMap.get(key).toString());
                }
            }
            if(key.equals("ID")) {
                textField.setEnabled(false);
            }
            panel.add(textField);
            inputMap.put(key, textField);
        }
    }

    public Map<String, Object> showDialog() {
        setVisible(true);
        return valueMap;
    }

}
