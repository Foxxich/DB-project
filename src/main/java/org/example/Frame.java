package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {

    private JMenu menu, info;
    private JMenuItem klient,zamowienie,elementy,towar,dokumentacja,instrukcja;
    private JMenuBar menuBar = new JMenuBar();
    private JRadioButtonMenuItem rbMenuItem;
    private Panel panel = new Panel();

    private static String dokumentacjaApp = "Created by (A+V)*L";
    private static String instrukcjaApp = "1.Uruchomic\n"+"2.Dodqc dane";

    @SuppressWarnings("unchecked")
    public Frame()
    {

        super("DateBase");
        menu = new JMenu("Menu");
        info = new JMenu("Info");
        klient = new JMenuItem("Klient");
        klient.addActionListener(e -> {
            //panel.setTable(null);
        });
        zamowienie = new JMenuItem("Zamowienie");
        zamowienie.addActionListener(e -> {
           // panel.setTable(null);
        });
        elementy = new JMenuItem("Elementy");
        elementy.addActionListener(e -> {
            //panel.setTable(null);
        });
        towar = new JMenuItem("Towar");
        towar.addActionListener(e -> {
            //panel.setTable(null);
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
        menu.add(klient); menu.add(zamowienie); menu.add(elementy); menu.add(towar);
        info.add(dokumentacja); info.add(instrukcja);
        this.add(menuBar);
        this.setJMenuBar(menuBar);
        this.setSize(700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        panel.setZamowienieTable();
        this.setContentPane(panel);
        this.setVisible(true);


    }

    //TODO - add method dla wylowania metody z DataBaseSQL

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
