package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Frame extends JFrame implements ActionListener {

    //TODO - refactor variables
    private JMenu menu, info,edycja;
    private JMenuItem klient,zamowienie,elementy,towarMenu,dokumentacja,instrukcja,dodanie,usuniecie;
    private JMenuBar menuBar = new JMenuBar();
    private JRadioButtonMenuItem rbMenuItem;
    private Panel panel = new Panel();
    DataAccessObject dataAccessObject = null;
    Controller controller = null;

    private static String dokumentacjaApp = "Created by (A+V)*L";
    private static String instrukcjaApp = "1.Uruchomic\n"+"2.Dodqc dane";

    @SuppressWarnings("unchecked")
    public Frame()
    {
        super("DateBase");
        controller = new Controller();
        String user = JOptionPane.showInputDialog( null, "Enter User Name");
        String password = JOptionPane.showInputDialog(null, "Enter Password" );
        while(true) {
            if(controller.logToDatabase(user,password)) {
                JOptionPane.showMessageDialog(this,"U r logged to database");
                break;
            } else {
                JOptionPane.showMessageDialog(this,"Incorrect parameters");
                user = JOptionPane.showInputDialog( null, "Enter User Name one more time");
                password = JOptionPane.showInputDialog(null, "Enter Password one more time" );
            }
        }
        menu = new JMenu("Menu");
        edycja = new JMenu("Edycja");
        info = new JMenu("Info");
        klient = new JMenuItem("Klient");
        klient.addActionListener(e -> {
            panel.setTable(controller.selectKlient());
        });
        zamowienie = new JMenuItem("Zamowienie");
        zamowienie.addActionListener(e -> {
            panel.setTable(controller.selectZamowienie());
        });
        elementy = new JMenuItem("Elementy");
        elementy.addActionListener(e -> {
            panel.setTable(controller.selectElementZamowienia());
        });
        towarMenu = new JMenuItem("Towar");
        towarMenu.addActionListener(e -> {
            panel.setTable(controller.selectTowar());
            this.pack();
        });
        dodanie = new JMenuItem("Dodanie");
        dodanie.addActionListener(e -> {
            panel.addDataBaseObject(controller.getDataBaseObjects().get(0));
            panel.setTable(controller.refreshTable());
        });
        usuniecie = new JMenuItem("Usuniecie");
        usuniecie.addActionListener(e -> {

        });
        dokumentacja = new JMenuItem("Dokumentacja");
        dokumentacja.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,dokumentacjaApp);
        });
        instrukcja = new JMenuItem("Instrukcja");
        instrukcja.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,instrukcjaApp);
        });
        this.setLayout(new BorderLayout());
        //this.setContentPane(panel);
        menuBar.add(menu);
        menuBar.add(info);
        menu.add(klient); menu.add(zamowienie); menu.add(elementy); menu.add(towarMenu);
        edycja.add(dodanie); edycja.add(usuniecie);
        info.add(dokumentacja); info.add(instrukcja);
        this.add(menuBar);
        this.setJMenuBar(menuBar);
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();

        this.panel.setPreferredSize(new Dimension(1000,1000));
        this.setContentPane(panel);

        this.setVisible(true);
    }

    public Map<String, Object> openModifyFrame(Map<String,Object> map) {
        //TODO - return that value
        ModifyFrame modifyFrame = new ModifyFrame(map);
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
