package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;

/**
 * This class is used to create JFrame with embedded functions and parameters.
 */
public class Frame extends JFrame implements ActionListener {

    /**
     * panel is JPanel with table of information from the database.
     * dokumentacjaApp is used as documentation of this application.
     * instrukcjaApp is used as instruction of this application.
     */
    private Panel panel = new Panel();
    Controller controller;

    private static final String dokumentacjaApp = "Ten program jest potrzebny dla operacje bazodanowych. Autorzy : V.L && M.B";
    private static final String instrukcjaApp = "1.Uruchamiamy aplikacje\n"+
                                                "2.Podajemy login oraz haslo\n"+
                                                "3.Czekamy na pozwolenie logowanie\n"+
                                                "4.Wybieramy w menu potrzebna tablice\n"+
                                                "5.Oczekujemy na wyświetlania danych w panelu\n"+
                                                "6.Wybieramy potrzebna operacje w Edycje\n"+
                                                "7.Gdy jest potrzebne, podajemy dane w nowo utworzonym okienku\n"+
                                                "8.Takze mozemy zrobic lub wczytac backup bazy\n";

    /**
     * This method is used to create JFrame.
     */
    public Frame()
    {
        super("DateBase");
        controller = new Controller();
        String user = JOptionPane.showInputDialog( null, "Enter User Name");
        String password = JOptionPane.showInputDialog(null, "Enter Password" );
        while(true) {
            if(user == null && password == null) {
                this.dispose();
                return;
            }
            if(controller.logToDatabase(user,password) && controller.checkPass()) {
                JOptionPane.showMessageDialog(this,"U r logged to database");
                break;
            } else {
                JOptionPane.showMessageDialog(this,"Incorrect parameters");
                user = JOptionPane.showInputDialog( null, "Enter User Name one more time");
                password = JOptionPane.showInputDialog(null, "Enter Password one more time" );
            }
        }
        JMenu menu = new JMenu("Menu");
        JMenu edycja = new JMenu("Edycja");
        JMenu info = new JMenu("Info");
        JMenu backup = new JMenu("BackUp");
        JMenuItem klient = new JMenuItem("Klient");
        klient.addActionListener(e -> panel.setTable(controller.selectKlient()));
        JMenuItem zamowienie = new JMenuItem("Zamowienie");
        zamowienie.addActionListener(e -> panel.setTable(controller.selectZamowienie()));
        JMenuItem elementy = new JMenuItem("Elementy");
        elementy.addActionListener(e -> panel.setTable(controller.selectElementZamowienia()));
        JMenuItem towarMenu = new JMenuItem("Towar");
        towarMenu.addActionListener(e -> {
            panel.setTable(controller.selectTowar());
            this.pack();
        });
        JMenuItem dodanie = new JMenuItem("Dodanie");
        dodanie.addActionListener(e -> {
            try {
                Map<String, Object> map = openModifyFrame(controller.getDataBaseObjects().get(0).getAsMap());
                if(map == null) {
                    return;
                }
                controller.addOrModifyDataBaseObject(map);
                panel.setTable(controller.refreshTable());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                handleException(throwables);
            } catch (IndexOutOfBoundsException exception) {
                handleException(new Exception("No table selected"));
            }
        });
        JMenuItem edytowanie = new JMenuItem("Edytowanie");
        edytowanie.addActionListener(e-> {
            try {
                int id = panel.getSelectedObjectId();
                if(id == -1) {
                    System.out.println("No row selected");
                } else {
                    Map<String, Object> map = openModifyFrame(controller.getObjectById(id).getAsMap());
                    if(map == null) {
                        return;
                    }
                    controller.addOrModifyDataBaseObject(map);
                    panel.setTable(controller.refreshTable());
                }
            } catch(SQLException throwables) {
                throwables.printStackTrace();
                handleException(throwables);
            } catch (NullPointerException exception) {
                handleException(new Exception("No row selected"));
            }
        });
        JMenuItem usuniecie = new JMenuItem("Usuniecie");
        usuniecie.addActionListener(e -> {
            try {
                int id = panel.getSelectedObjectId();
                if(id == -1) {
                    System.out.println("No row selected");
                } else {
                    controller.deleteObject(id);
                    panel.setTable(controller.refreshTable());
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                handleException(throwables);
            } catch (NullPointerException exception) {
                handleException(new Exception("No row selected"));
            }
        });
        JMenuItem dokumentacja = new JMenuItem("Dokumentacja");
        dokumentacja.addActionListener(e -> JOptionPane.showMessageDialog(this,dokumentacjaApp));
        JMenuItem instrukcja = new JMenuItem("Instrukcja");
        instrukcja.addActionListener(e -> JOptionPane.showMessageDialog(this,instrukcjaApp));
        JMenuItem saveBackUp = new JMenuItem("DoBackUp");
        saveBackUp.addActionListener(e -> {
            BaseCommand baseCommand = new BaseCommand();
            baseCommand.doBackUp();
        });
        JMenuItem loadBackUp = new JMenuItem("LoadBackUp");
        loadBackUp.addActionListener(e -> {
            BaseCommand baseCommand = new BaseCommand();
            baseCommand.loadBackUp();
        });
        this.setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(edycja);
        menuBar.add(info);
        menuBar.add(backup);
        menu.add(klient); menu.add(zamowienie); menu.add(elementy); menu.add(towarMenu);
        edycja.add(dodanie); edycja.add(edytowanie); edycja.addSeparator(); edycja.add(usuniecie);
        info.add(dokumentacja); info.add(instrukcja);
        backup.add(saveBackUp);
        backup.add(loadBackUp);
        this.add(menuBar);
        this.setJMenuBar(menuBar);
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();

        this.panel.setPreferredSize(new Dimension(1000,1000));
        this.setContentPane(panel);

        this.setVisible(true);
    }

    /**
     * This method is used to handle exceptions from database.
     * @param exception is used when operation cannot be provided and
     * user should get detailed information about the error.
     */
    private void handleException(Exception exception) {
        JOptionPane.showMessageDialog(null,exception.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is used to create ModifyFrame.
     * @param map is a map of strings(headers) and objects(rows).
     * @return map with values.
     */
    public Map<String, Object> openModifyFrame(Map<String,Object> map) {
        ModifyFrame modifyFrame = new ModifyFrame(map, this);
        return modifyFrame.showDialog();
    }

    /**
     * This method is implemented from ActionListener to check
     * if there were provided any operations on the panel.
     * @param e is ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
