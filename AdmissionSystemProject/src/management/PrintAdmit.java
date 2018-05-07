package management;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.io.*;
public class PrintAdmit extends Application {

    VBox myPrint;
    public ArrayList<String> store = new ArrayList<String>();
     TextField tt=new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Registration");
        primaryStage.setResizable(false);
        myPrint = new VBox(20);
        myPrint.setPadding(new Insets(10, 50, 50, 50));
        myPrint.setSpacing(10);
        Button buttonPrint = new Button("Print");
        GridPane.setConstraints(tt,4,5);
        myPrint.getChildren().addAll(buttonPrint);
        buttonPrint.setOnAction(e->{
            print();
        });
        
        Scene scene = new Scene(myPrint, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void print() {
       
        try{
            Document doc= new Document();
            PdfWriter.getInstance(doc,new FileOutputStream(store.get(0)+".pdf"));
            doc.open();
            Image pic=Image.getInstance(store.get(14));
            doc.add(pic);
             doc.add(new Paragraph("Unit: "+store.get(16)));
            doc.add(new Paragraph("Roll: "+store.get(0)));
            doc.add(new Paragraph("Name: "+store.get(1)));
            doc.add(new Paragraph("C/O: "+store.get(2)));
            doc.add(new Paragraph("HSC Roll: "+store.get(3)));
            doc.add(new Paragraph("HSC Reg.: "+store.get(4)));
            doc.add(new Paragraph("HSC Passing Year: "+store.get(5)));
            doc.add(new Paragraph("HSC GPA: "+store.get(6)));
            doc.add(new Paragraph("SSC Roll: "+store.get(7)));
            doc.add(new Paragraph("SSC Reg: "+store.get(8)));
            doc.add(new Paragraph("SSC Passing Year: "+store.get(9)));
            doc.add(new Paragraph("SSC GPA: "+store.get(10)));
            doc.add(new Paragraph("Division: "+store.get(11)));
            doc.add(new Paragraph("District: "+store.get(12)));
            doc.add(new Paragraph("DOB: "+store.get(13)));
            doc.add(new Paragraph("Signature:"));
            Image sig=Image.getInstance(store.get(15));
            doc.add(sig);
            doc.close();
            database db=new database();
            db.storeInfo(store);
        }
        catch(Exception e)
        {
            AlertMessage.display("Error","Not Succeeds", "Ok");
        }
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
