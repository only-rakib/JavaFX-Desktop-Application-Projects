package application.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class FileHandle{

	public void salesHistory(ArrayList<TableShowHistory>arrayHistory,String ID,String date)
    {
        File file = new File("Store\\generate\\" + ID + ".dat");
        
        try {
            
        	PrintStream writer = new PrintStream(new FileOutputStream(file, true));
        	writer.append("###### "+date+" ######");
        	writer.println();
            for(int i = 0;i<arrayHistory.size();i++)
        	{
            	writer.append("Product Name :" + arrayHistory.get(i).getProductName());
            	writer.println();
            	writer.append("Quantity     :" + arrayHistory.get(i).getProductQuantity());
            	writer.println();
            	writer.append("Price        : "+ arrayHistory.get(i).getProductPrice());
            	writer.println();
        	}
            writer.close();
            //AlertMessage.display("Success", "Successfully Add", "Ok");

        } catch (FileNotFoundException ex) {
            AlertMessage.display("Error", "File Not Found", "Ok");
            
        }
       
    }
	
	/**
	 * This method is to store sell table data in a text file where we can print this
	 * @param array
	 * @param date
	 * @param customerName
	 * @param customerAddress
	 * @param customerType
	 * @param customerPhoneNumber
	 */
	public void fileForPrint(ArrayList<ForSellTable>array,String date,String customerName,String customerAddress,String customerType,String customerPhoneNumber,String ID)
	{
		File file = new File("Store\\generate\\sellTemporary.dat");
        try {
            
        	PrintStream writer = new PrintStream(file);
        	writer.println();
        	writer.println();
        	writer.println();
        	writer.println();
        	writer.println();
        	writer.println();
        	writer.println("      Date : "+date);
        	writer.println();
        	writer.println();
        	writer.println("      Customer Information/s:");
        	writer.println();
        	writer.println("          Name    : "+customerName);
        	writer.println("          Address : "+customerAddress);
        	writer.println("          Type    : "+customerType);
        	writer.println("          Phone No: "+customerPhoneNumber);
        	writer.println();
        	writer.println();
        	writer.println("      Details of the product/s:");
        	writer.println();
        	
        	String format =  "%-10s %-20s %-10s %-15s %s %n";
        	writer.printf(format," ", "Name","Quantity","Total Price","Guarantee/Warrenty");
        	writer.println();
        	writer.println();
        	writer.println();
        	
        	String formatFile =  "%-10s %-23s %-13s %-18s %s %n";
            for(int i = 0;i<array.size();i++)
        	{
            	writer.printf(formatFile," ",array.get(i).getName(),array.get(i).getQuantity(),array.get(i).totalPrice,array.get(i).getGuarantee());
            	
            	
        	}
            writer.println();
            writer.println();
            writer.println();
            writer.println();
            writer.println();
            writer.println();
            writer.printf("%-10s %-4s %-40s %s", " ","Salesman ID:",ID,"Signeture");
            writer.close();
            //AlertMessage.display("Success", "Successfully Add", "Ok");

        } catch (FileNotFoundException ex) {
            AlertMessage.display("Error", "File Not Found", "Ok");
            
        }
	}
}
