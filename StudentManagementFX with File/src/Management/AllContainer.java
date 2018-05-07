/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Rakib
 */
public class AllContainer {

    
    public static boolean login(String name, String pass) {
        File file = new File("login.txt");
        try {

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                if (name.equals(reader.nextLine()) && pass.equals(reader.nextLine())) {
                    if (name != null && pass != null) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
            reader.close();

        } catch (FileNotFoundException ex) {
            AlertMessage.display("Error", "File Not Found", "Ok");
            return false;
        }
        return false;
    }

    public static void print(String name, String id, String dept) {

        File file = new File("info.txt");
        try {
            PrintStream writer = new PrintStream(new FileOutputStream(file, true));
            writer.append("ID :" + id);
            writer.println();
            writer.append("name :" + name);
            writer.println();
            writer.append("Department : " + dept);
            writer.println();
            writer.close();
            AlertMessage.display("Success", "Successfully Add", "Ok");

        } catch (FileNotFoundException ex) {
            AlertMessage.display("Error", "File Not Found", "Ok");
        }
        //break;
    }

}
