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
            ArrayList<String> words = new ArrayList<>();
            String[] command = new String[]{"cmd.exe", "/c", "\"C:\\Program Files\\MariaDB 10.5\\bin\\mysqldump.exe\" --quick --lock-tables --user=\"root\" --password=\"2020\" zakupy > \"C:\\Users\\Vadym\\Documents\\testB\\file.sql\" "};
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(command);
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
