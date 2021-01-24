package org.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BaseCommand {
    public BaseCommand() {

    }

    public void doBackUp() {
        {
            String[] command = new String[]{"cmd.exe", "/c", "\"C:\\Program Files\\MariaDB 10.5\\bin\\mysqldump.exe\" --quick --lock-tables --user=\"root\" --password=\"2020\" zakupy > \"C:\\Users\\Vadym\\Documents\\testB\\file.sql\" "};
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadBackUp() {
        String[] command = new String[]{"cmd.exe", "/c", "\"C:\\Program Files\\MariaDB 10.5\\bin\\mysql.exe\" --user=\"root\" --password=\"2020\" zakupy < \"C:\\Users\\Vadym\\Documents\\testB\\file.sql\" "};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
