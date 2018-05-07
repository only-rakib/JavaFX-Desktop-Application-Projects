/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Rakib
 */
public class UnitInput extends Application{
    private ComboBox comboBoxUnit;
    private DatePicker datePickerDeadLine;
    private TextField textFieldSeatNum,textFieldMarksSub1,textFieldMarksSub2,textFieldMarksSub3,textFieldMarksSub4;
    private Label labelUnit,labelAdmissionDeadLine,labelSeatNumber,labelPassMarks,labelSub1,labelSub2,labelSub3,labelSub4;
    private Button buttonOk,buttonCancel;
    private String filePath;
    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage primaryStage) throws Exception {
 
        primaryStage.setTitle("Admin Unit Modify");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(8,8,8,8));
        grid.setHgap(10);
        grid.setVgap(10);
        
        labelUnit = new Label("Select Unit");
        GridPane.setConstraints(labelUnit, 5, 5);
        comboBoxUnit = new ComboBox();
        comboBoxUnit.setValue("Unit");
        comboBoxUnit.getItems().addAll("A","B","C","D");
        GridPane.setConstraints(comboBoxUnit,8,5);
        
        labelSeatNumber = new Label("Seat Number");
        GridPane.setConstraints(labelSeatNumber,5,6);
        textFieldSeatNum = new TextField();
        GridPane.setConstraints(textFieldSeatNum,8, 6);
        
        labelAdmissionDeadLine = new Label("Admission DeadLine");
        GridPane.setConstraints(labelAdmissionDeadLine, 5, 7);
        datePickerDeadLine = new DatePicker();
        datePickerDeadLine.setValue(LocalDate.now());
        GridPane.setConstraints(datePickerDeadLine,8,7);
        
        labelPassMarks = new Label("Enter pass marks");
        labelPassMarks.setTextFill(Color.RED);
        GridPane.setConstraints(labelPassMarks,5, 8);
        
        labelSub1 = new Label();
        GridPane.setConstraints(labelSub1, 5, 9);
        labelSub1.setVisible(false);
        textFieldMarksSub1 = new TextField();
        GridPane.setConstraints(textFieldMarksSub1, 8, 9);
        textFieldMarksSub1.setVisible(false);
        
        labelSub2 = new Label();
        GridPane.setConstraints(labelSub2, 5, 10);
        labelSub2.setVisible(false);
        textFieldMarksSub2 = new TextField();
        GridPane.setConstraints(textFieldMarksSub2, 8, 10);
        textFieldMarksSub2.setVisible(false);
        
        labelSub3 = new Label();
        GridPane.setConstraints(labelSub3, 5, 11);
        labelSub3.setVisible(false);
        textFieldMarksSub3 = new TextField();
        GridPane.setConstraints(textFieldMarksSub3, 8, 11);
        textFieldMarksSub3.setVisible(false);
        
        labelSub4 = new Label();
        GridPane.setConstraints(labelSub4, 5, 12);
        labelSub4.setVisible(false);
        textFieldMarksSub4 = new TextField();
        GridPane.setConstraints(textFieldMarksSub4, 8, 12);
        textFieldMarksSub4.setVisible(false);
        
        comboBoxUnit.setOnAction(e->{
            if(comboBoxUnit.getValue().equals("A"))
            {
                labelSub1.setText("English");
                labelSub2.setText("Chemistry");
                labelSub3.setText("Physics");
                labelSub4.setText("Mathematics");
                labelSub1.setVisible(true);
                labelSub2.setVisible(true);
                labelSub3.setVisible(true);
                labelSub4.setVisible(true);
                textFieldMarksSub1.setVisible(true);
                textFieldMarksSub2.setVisible(true);
                textFieldMarksSub3.setVisible(true);
                textFieldMarksSub4.setVisible(true);
            }
            else if(comboBoxUnit.getValue().equals("B"))
            {
                labelSub1.setText("English");
                labelSub2.setText("Chemistry");
                labelSub3.setText("Physics");
                labelSub4.setText("Biology");
                labelSub1.setVisible(true);
                labelSub2.setVisible(true);
                labelSub3.setVisible(true);
                labelSub4.setVisible(true);
                textFieldMarksSub1.setVisible(true);
                textFieldMarksSub2.setVisible(true);
                textFieldMarksSub3.setVisible(true);
                textFieldMarksSub4.setVisible(true);
            }
            else if(comboBoxUnit.getValue().equals("C"))
            {
                labelSub1.setText("English");
                labelSub2.setText("Chemistry");
                labelSub3.setText("Physics");
                labelSub4.setText("Mathematics");
                labelSub1.setVisible(true);
                labelSub2.setVisible(true);
                labelSub3.setVisible(true);
                labelSub4.setVisible(true);
                textFieldMarksSub1.setVisible(true);
                textFieldMarksSub2.setVisible(true);
                textFieldMarksSub3.setVisible(true);
                textFieldMarksSub4.setVisible(true);
            }
            else if(comboBoxUnit.getValue().equals("D"))
            {
                labelSub1.setText("English");
                labelSub2.setText("Principle of Business and Practice");
                labelSub3.setText("Accounting");
                
                labelSub1.setVisible(true);
                labelSub2.setVisible(true);
                labelSub3.setVisible(true);
                labelSub4.setVisible(false);
                textFieldMarksSub1.setVisible(true);
                textFieldMarksSub2.setVisible(true);
                textFieldMarksSub3.setVisible(true);
                textFieldMarksSub4.setVisible(false);
            }
        
        });
        
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx")
        );
        Button openButton = new Button("Open File");
        GridPane.setConstraints(openButton, 5, 13);

        openButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            filePath = file.getAbsolutePath();
            
        });
        
        buttonOk = new Button("Ok");
        GridPane.setConstraints(buttonOk, 10, 14);
        buttonCancel = new Button("Cancel");
        GridPane.setConstraints(buttonCancel,5,14);
        buttonOk.setOnAction(e->{
            LocalDate localDate = LocalDate.now();
            String date = localDate.toString();
            if(date.compareTo(datePickerDeadLine.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))>0)
            {
                AlertMessage.display("Error", "Previous Date Selected", "Ok");
            }
            else if(comboBoxUnit.getValue().equals("Unit"))
            {
                AlertMessage.display("Error", "Select Unit", "Ok");
            }
            else if(textFieldSeatNum.getText().equals(""))
            {
                AlertMessage.display("Error", "Enter Seat Number", "Ok");
            }
            else if(comboBoxUnit.getValue().equals("A"))
            {
                File source1 = new File(filePath);
                File dest1 = new File("FilesExcel\\A.xlsx");
                String desPath=dest1.getAbsolutePath();
                try {
                    FileUtils.copyFile(source1, dest1);
                } 
                catch(Exception ex){
                    AlertMessage.display("Error", "An error occured", "Ok");
                    
                }
                try {
                    ExcelFileRead.excelFile(desPath,comboBoxUnit.getValue().toString(),textFieldMarksSub1.getText(),textFieldMarksSub2.getText(),textFieldMarksSub3.getText(),textFieldMarksSub4.getText());
                } catch (IOException ex) {
                    AlertMessage.display("Success","File Error", "Ok");
                } catch (InvalidFormatException ex) {
                   AlertMessage.display("Success","File Error", "Ok");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UnitInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                AlertMessage.display("Success", comboBoxUnit.getValue()+" Unit has "+textFieldSeatNum.getText()+" Seats", "Ok");
            }
            else if(comboBoxUnit.getValue().equals("B"))
            {
                File source1 = new File(filePath);
                File dest1 = new File("FilesExcel\\B.xlsx");
                String desPath=dest1.getAbsolutePath();
                try {
                    FileUtils.copyFile(source1, dest1);
                } 
                catch(Exception ex){
                    AlertMessage.display("Error", "An error occured", "Ok");
                    
                }
                
                try {
                    ExcelFileRead.excelFile(desPath,comboBoxUnit.getValue().toString(),textFieldMarksSub1.getText(),textFieldMarksSub2.getText(),textFieldMarksSub3.getText(),textFieldMarksSub4.getText());
                } catch (IOException ex) {
                    AlertMessage.display("Success","File Error", "Ok");
                } catch (InvalidFormatException ex) {
                   AlertMessage.display("Success","File Error", "Ok");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UnitInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                AlertMessage.display("Success", comboBoxUnit.getValue()+" Unit has "+textFieldSeatNum.getText()+" Seats", "Ok");
            }
            else if(comboBoxUnit.getValue().equals("C"))
            {
                File source1 = new File(filePath);
                File dest1 = new File("FilesExcel\\C.xlsx");
                String desPath=dest1.getAbsolutePath();
                try {
                    FileUtils.copyFile(source1, dest1);
                } 
                catch(Exception ex){
                    AlertMessage.display("Error", "An error occured", "Ok");
                    
                }
                
                try {
                    ExcelFileRead.excelFile(desPath,comboBoxUnit.getValue().toString(),textFieldMarksSub1.getText(),textFieldMarksSub2.getText(),textFieldMarksSub3.getText(),textFieldMarksSub4.getText());
                } catch (IOException ex) {
                    AlertMessage.display("Success","File Error", "Ok");
                } catch (InvalidFormatException ex) {
                   AlertMessage.display("Success","File Error", "Ok");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UnitInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                AlertMessage.display("Success", comboBoxUnit.getValue()+" Unit has "+textFieldSeatNum.getText()+" Seats", "Ok");
            }
            else if(comboBoxUnit.getValue().equals("D"))
            {
                File source1 = new File(filePath);
                File dest1 = new File("FilesExcel\\D.xlsx");
                String desPath=dest1.getAbsolutePath();
                try {
                    FileUtils.copyFile(source1, dest1);
                } 
                catch(Exception ex){
                    AlertMessage.display("Error", "An error occured", "Ok");
                    
                }
                
                try {
                    ExcelFileRead.excelFile(desPath,comboBoxUnit.getValue().toString(),textFieldMarksSub1.getText(),textFieldMarksSub2.getText(),textFieldMarksSub3.getText());
                } catch (IOException ex) {
                    AlertMessage.display("Success","File Error", "Ok");
                } catch (InvalidFormatException ex) {
                   AlertMessage.display("Success","File Error", "Ok");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UnitInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                AlertMessage.display("Success", comboBoxUnit.getValue()+" Unit has "+textFieldSeatNum.getText()+" Seats", "Ok");
            }
        });
        
        
        buttonCancel.setOnAction(e->{
            AdminOption option = new AdminOption();
            try{
                option.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        });
        grid.getChildren().addAll(labelUnit,labelAdmissionDeadLine,labelSeatNumber,buttonOk,buttonCancel,comboBoxUnit,datePickerDeadLine,textFieldSeatNum,openButton);
        grid.getChildren().addAll(labelPassMarks,labelSub1,labelSub2,labelSub3,labelSub4,textFieldMarksSub1,textFieldMarksSub2,textFieldMarksSub3,textFieldMarksSub4);
        Scene scene = new Scene(grid,600,600);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
