/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Rakib
 */
public class FileHandle {
    public int rollGenerator()
    {
        int roll=0;
        File file = new File("rollGenerator.txt");
        try {
            
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                roll = Integer.parseInt(reader.nextLine());
            }
            reader.close();

        } catch (FileNotFoundException ex) {
            AlertMessage.display("Error", "File Not Found", "Ok");
            return 0;
        }
        
        try {
            
            PrintStream writer = new PrintStream(file);
            writer.println(roll+1);
            writer.close();

        } catch (FileNotFoundException ex) {
            AlertMessage.display("Error", "File Not Found", "Ok");
        }
        return roll;
    }       
                         
}
