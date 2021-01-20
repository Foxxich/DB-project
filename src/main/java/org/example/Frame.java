package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {

    private JMenu menu, info;
    private JMenuItem klient,zamowienie,elementy,towarMenu,dokumentacja,instrukcja;
    private JMenuBar menuBar = new JMenuBar();
    private JRadioButtonMenuItem rbMenuItem;
    private Panel panel = new Panel();
    DataAccessObject dataAccessObject = null;

    private String user;
    private String password;

    private static String dokumentacjaApp = "Created by (A+V)*L";
    private static String instrukcjaApp = "1.Uruchomic\n"+"2.Dodqc dane";

    @SuppressWarnings("unchecked")
    public Frame()
    {
        super("DateBase");
        user = JOptionPane.showInputDialog( null, "Enter User Name");
        password = JOptionPane.showInputDialog(null, "Enter Password" );
        dataAccessObject = new DataBaseSQL(user,password);
        if(dataAccessObject.checkUser(user,password)) {
            JOptionPane.showMessageDialog(this,"U r logged to database");
        } else {
            JOptionPane.showMessageDialog(this,"Incorrect parameters");
            user = JOptionPane.showInputDialog( null, "Enter User Name one more time");
            password = JOptionPane.showInputDialog(null, "Enter Password one more time" );
        }
        menu = new JMenu("Menu");
        info = new JMenu("Info");
        klient = new JMenuItem("Klient");
        klient.addActionListener(e -> {
            panel.setTable(dataAccessObject.selectKlient());
        });
        zamowienie = new JMenuItem("Zamowienie");
        zamowienie.addActionListener(e -> {
            panel.setTable(dataAccessObject.selectZamowienie());
        });
        elementy = new JMenuItem("Elementy");
        elementy.addActionListener(e -> {
            panel.setTable(dataAccessObject.selectElementZamowienia());
        });
        towarMenu = new JMenuItem("Towar");
        towarMenu.addActionListener(e -> {
            panel.setTable(dataAccessObject.selectTowar());
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
        info.add(dokumentacja); info.add(instrukcja);
        this.add(menuBar);
        this.setJMenuBar(menuBar);
        this.setSize(700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        this.setContentPane(panel);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
