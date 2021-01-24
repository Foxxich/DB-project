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
            String[] command = new String[] {"cmd.exe", "/c", "\"C:\\Program Files\\MariaDB 10.5\\bin\\mysqldump.exe\" --quick --lock-tables --user=\"root\" --password=\"2020\" zakupy > \"C:\\Users\\Vadym\\Documents\\testB\\file.sql\" "};
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                try {
                    if (!((input.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //String line = input.readLine();
                //words.add(line);
                //System.out.println(input.readLine()); //there you can write file
            }
            //input.close();
            try {
                FileWriter myWriter = new FileWriter("C:\\Users\\Vadym\\Documents\\testB\\filename.txt");
                myWriter.write(String.valueOf(words));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

}
