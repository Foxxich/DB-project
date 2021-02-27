package org.example;

import java.io.IOException;

/**
 * This is class used for making and loading back-up's of the database "Zakupy".
 */
public class BaseCommand {

    public BaseCommand() {
    }

    /**
     * This method is used to make back-up and put the database copy to prepared folder.
     */
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

    /**
     * This method is used to load back-up and use it as current database.
     */
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
