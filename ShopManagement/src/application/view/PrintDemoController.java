package application.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrintDemoController implements Initializable {
	@FXML
	AnchorPane anchorPane;
	@FXML
	Button buttonPrint,buttonBack;
	@FXML
	private Label jobStatus,labelDate;
	@FXML
	private TextArea textArea;
	
	private ArrayList<ForSellTable>array = new ArrayList<>();
	
	public ArrayList<ForSellTable> getArray() {
		return array;
	}

	public void setArray(ArrayList<ForSellTable> array) {
		this.array = array;
	}
	Stage owner;
	@FXML
	private void buttonPrintClick(ActionEvent event)
	{
		//name();
		//printSetup();
		try {
			textPrint();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			AlertMessage.display("Error", e.getMessage(), "OK");
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
		
	}
	
	@FXML
	private void buttonBackClick(ActionEvent event)
	{
		try {
			Main.storeButtonClick();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
	}
	
	@SuppressWarnings("unused")
	private void printSetup() 
	{
		// Create the PrinterJob		
		PrinterJob job = PrinterJob.createPrinterJob();
	
		if (job == null) 
		{
			return;
		}

		// Show the print setup dialog
		boolean proceed = job.showPrintDialog(owner);
		
		if (proceed) 
		{
			jobStatus.textProperty().bind(job.jobStatusProperty().asString());
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		jobStatus.setText("");
		textArea.setText("");
		try {
	        @SuppressWarnings("resource")
			Scanner input = new Scanner(new File("Store\\generate\\sellTemporary.dat")).useDelimiter("\\s+");
	        while (input.hasNextLine()) {
	            if (input.hasNextLine()) {
	            	textArea.appendText(input.nextLine() +"\n");
	            } else {
	            	textArea.appendText(input.next() + "\n"); 
	            }
	        }
	    } catch (FileNotFoundException ex) {
	    	textArea.appendText("File not found");
	    }
		
	}
	private void textPrint() throws FileNotFoundException, PrintException
	{
		FileInputStream textStream;
		
		textStream = new FileInputStream("Store\\generate\\sellTemporary.dat"); 
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
      	aset.add(new Copies(1));
      
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc mydoc = new SimpleDoc(textStream, flavor, null);

		   PrintService[] services = PrintServiceLookup.lookupPrintServices(
		                flavor, aset);
		   PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

		   if(services.length == 0) {
		       if(defaultService == null) {
		             //no printer found

		       } else {
		            //print using default
		            DocPrintJob job = defaultService.createPrintJob();
		            job.print(mydoc, aset);

		       }

		    } else {

		       //built in UI for printing you may not use this
		       PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, aset);


		        if (service != null)
		        {
		           DocPrintJob job = service.createPrintJob();
		           job.print(mydoc, aset);
		        }

		    }
	}
}
