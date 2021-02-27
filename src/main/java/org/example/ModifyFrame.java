package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is used to create JFrame with embedded functions and parameters.
 * Also it extends JDialog.
 */
public class ModifyFrame extends JDialog {
    /**
     * ok is button to "agree" with providing any changes.
     * cancel is button to "disagree" with providing any changes.
     * panel is JPanel with text fields for input.
     * valueMap is a map of headers and objects(row with data).
     * inputMap is used for input from user.
     */
    JButton ok,cancel;
    JPanel panel;

    Map<String,Object> valueMap = null;
    Map<String, JTextField> inputMap;

    /**
     * This method is used to get map of headers and values, Frame parent.
     * @param valueMap is a map with headers and objects.
     * @param parent is used as a parent JFrame.
     * @throws HeadlessException
     */
    public ModifyFrame(Map<String, Object> valueMap, Frame parent) throws HeadlessException {
        super(parent,"Modify", true);
        this.setSize(250, valueMap.keySet().size() * 62);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
        cancel.addActionListener(e -> {
            valueZero();
            this.dispose();
        });
        panel = new JPanel();
        panel.setLayout(new FlowLayout());


        this.inputMap = new LinkedHashMap<>();
        this.valueMap = valueMap;
        createForm();

        panel.add(ok);
        panel.add(cancel);

        setContentPane(panel);
    }

    /**
     *  This method is used to make valueMap equal to null.
     *  Especially program executes it when user chooses "Cancel"
     *  while providing date in a new frame.
     */
    private void valueZero() {
        valueMap = null;
    }

    /**
     * This method is used to get data from text fields and denying
     * possibility to use custom id.
     */
    private void createForm() {
        Dimension preferredDimension = new Dimension(this.getWidth() - 30, 20);
        for(String key : valueMap.keySet()) {

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

    /**
     * This method is used to show a dialog message when data is added/modified.
     * @return valueMap.
     */
    public Map<String, Object> showDialog() {
        setVisible(true);
        return valueMap;
    }

}
