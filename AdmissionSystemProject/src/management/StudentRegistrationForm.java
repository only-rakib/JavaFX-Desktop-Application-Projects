/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.io.File;
import java.time.LocalDate;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 *
 */
public class StudentRegistrationForm extends Application {

    private TextField textFieldStudentName, textFieldStudentCO, textFieldHSCRoll, textFieldHSCReg, textFieldSSCRoll, textFieldSSCReg;
    private TextField textFieldHSCGPA, textFieldSSCGPA;
    private Label labelStudentName, labelStudentCO, labelHSCRoll, labelHSCReg, labelSSCRoll, labelSSCReg, labelHSCGPA, labelSSCGPA;
    private Label labelHSCYear, labelSSCYear, labelAddress, labelDateOfBirth, labelUnit;
    private Button buttonSubmit, buttonCancel, buttonSigneture;
    private String photoPath, signeturePath;
    private final String sDhakaDistrict[] = new String[]{"Dhaka", "Gazipur", "Manikganj", "Munshiganj", "Narayanganj", "Tangail", "Faridpur", "Gopalganj", "Madaripur", "Rajbari", "Shariatpur"};
    private final String sChattagramDistrict[] = new String[]{"Brahmanbaria", "Comilla", "Chandpur", "Lakshmipur", "Noakhali", "Feni", "Khagrachhari", "Rangamati", "Bandarban", "Chittagong", "Coxs Bazar", "Chattagram"};
    private final String sRajshahiDistrict[]= new String[] {"Bogura","Joypurhat","Naogaon","Natore","Chapai Nawabganj","Pabna","Rajshahi","Sirajganj"}; 
    private final String sKhulnaDistrict[]= new String [] {"Bagerhat","Chuadanga","Jashore","Jhenaidah","Khulna","Kushtia","Magura","Meherpur","Narail","Satkhira"};
    private final String sBarishalDistrict[]= new String [] {"Barguna","Barishal","Bhola","Jhalokati","Patuakhali","Pirojpur"};
    private final String sSylhetDistrict[]= new String [] {"Sylhet", "Sunamganj","Moulvibazar","Habiganj"};
    private final String sRangpurDistrict[]= new String [] {"Thakurgaon","Rangpur","Panchagarh","Nilphamari","Lalmonirhat","Kurigram","Gaibandha","Dinajpur"};
    private final String sMymensinghDistrict[]= new String [] {"Jamalpur", "Netrokona", "Sherpur", "Mymensingh"};
    private boolean flag = false;

    ComboBox<String> comboBoxDivision, comboBoxDistrict, comboBoxUnit,comboBoxUpazila;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Registration");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setHgap(10);
        grid.setVgap(5);

        labelStudentName = new Label("Name");
        GridPane.setConstraints(labelStudentName, 5, 1);
        textFieldStudentName = new TextField();
        GridPane.setConstraints(textFieldStudentName, 8, 1);

        labelStudentCO = new Label("C/O");
        GridPane.setConstraints(labelStudentCO, 5, 2);
        textFieldStudentCO = new TextField();
        GridPane.setConstraints(textFieldStudentCO, 8, 2);

        labelHSCRoll = new Label("HSC Roll");
        GridPane.setConstraints(labelHSCRoll, 5, 3);
        textFieldHSCRoll = new TextField();
        GridPane.setConstraints(textFieldHSCRoll, 8, 3);

        labelHSCReg = new Label("HSC Reg");
        GridPane.setConstraints(labelHSCReg, 5, 4);
        textFieldHSCReg = new TextField();
        GridPane.setConstraints(textFieldHSCReg, 8, 4);

        labelHSCYear = new Label("HSC Year");
        GridPane.setConstraints(labelHSCYear, 5, 5);
        ComboBox<String> comboboxHSCYear = new ComboBox<>();
        comboboxHSCYear.getItems().addAll("2018", "2017");
        comboboxHSCYear.setValue(null);
        GridPane.setConstraints(comboboxHSCYear, 8, 5);

        labelHSCGPA = new Label("HSC GPA");
        GridPane.setConstraints(labelHSCGPA, 5, 6);
        textFieldHSCGPA = new TextField();
        GridPane.setConstraints(textFieldHSCGPA, 8, 6);

        labelSSCRoll = new Label("SSC Roll");
        GridPane.setConstraints(labelSSCRoll, 5, 7);
        textFieldSSCRoll = new TextField();
        GridPane.setConstraints(textFieldSSCRoll, 8, 7);

        labelSSCReg = new Label("SSC Reg");
        GridPane.setConstraints(labelSSCReg, 5, 8);
        textFieldSSCReg = new TextField();
        GridPane.setConstraints(textFieldSSCReg, 8, 8);

        labelSSCYear = new Label("SSC Year");
        GridPane.setConstraints(labelSSCYear, 5, 9);
        ComboBox<String> comboboxSSCYear = new ComboBox<>();
        comboboxSSCYear.getItems().addAll("2016", "2015");
        comboboxSSCYear.setValue(null);
        GridPane.setConstraints(comboboxSSCYear, 8, 9);

        labelSSCGPA = new Label("SSC GPA");
        GridPane.setConstraints(labelSSCGPA, 5, 10);
        textFieldSSCGPA = new TextField();
        GridPane.setConstraints(textFieldSSCGPA, 8, 10);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        Button openButton = new Button("Open Image");
        GridPane.setConstraints(openButton, 22, 5);

        openButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            photoPath = file.toURI().toString();
            if (photoPath.equals("")) {
                flag = true;
                System.out.println("photoPath");
            } 
            else 
            {
                Image image = new Image(photoPath);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                GridPane.setConstraints(imageView, 22, 3);
                grid.getChildren().add(imageView);
            }
        });
        labelAddress = new Label("Address");
        GridPane.setConstraints(labelAddress, 5, 11);

        comboBoxDivision = new ComboBox<String>();
        GridPane.setConstraints(comboBoxDivision, 8, 11);
        comboBoxDivision.getItems().addAll("Dhaka", "Chattagram", "Rajshahi", "Khulna", "Barishal", "Sylhet", "Rangpur", "Mymenshingh");
        comboBoxDivision.setValue("Division");

        comboBoxDistrict = new ComboBox<String>();
        GridPane.setConstraints(comboBoxDistrict, 12, 11);
        comboBoxDistrict.setValue("District");

        comboBoxDivision.setOnAction(e -> {
            
            if ("Dhaka".equals(comboBoxDivision.getValue())) {
                comboBoxDistrict.setValue("District");
                comboBoxDistrict.getItems().clear();
                comboBoxDistrict.getItems().addAll(sDhakaDistrict);
                //grid.getChildren().addAll(comboBoxDistrict);
            } else if ("Chattagram".equals(comboBoxDivision.getValue())) {
                comboBoxDistrict.setValue("District");
                comboBoxDistrict.getItems().clear();
                comboBoxDistrict.getItems().addAll(sChattagramDistrict);
                //
            }
            /*else if("Rajshahi".equals(comboBoxDivision.getValue())){
                
            }
            else if("Khulna".equals(comboBoxDivision.getValue())){
                
            }
            else if("Barishal".equals(comboBoxDivision.getValue())){
                
            }
            else if("Sylhet".equals(comboBoxDivision.getValue())){
                
            }
            else if("Rangpur".equals(comboBoxDivision.getValue())){
                
            }
            else if("Mymenshingh".equals(comboBoxDivision.getValue())){
                
            }*/

        });
        
        labelDateOfBirth = new Label("Date Of Birth");
        GridPane.setConstraints(labelDateOfBirth, 5, 12);
        DatePicker checkDatePicker = new DatePicker();
        checkDatePicker.setValue(LocalDate.now());
        GridPane.setConstraints(checkDatePicker, 8, 12);

        labelUnit = new Label("Select Unit");
        GridPane.setConstraints(labelUnit, 5, 13);
        comboBoxUnit = new ComboBox<String>();
        GridPane.setConstraints(comboBoxUnit, 8, 13);
        comboBoxUnit.setValue("Unit");
        comboBoxUnit.getItems().addAll("A", "B", "C", "D");

        buttonSigneture = new Button("Upload Signeture");
        GridPane.setConstraints(buttonSigneture, 5, 14);
        buttonSigneture.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            signeturePath = file.toURI().toString();
            if(signeturePath.equals(""))
            {
                flag = true;
                System.out.println("SigneturePath");
            }    
            else{   
                Image image = new Image(signeturePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                GridPane.setConstraints(imageView, 8, 14);

                grid.getChildren().add(imageView);
            }
        });
        /*if(textFieldStudentName.getText().equals("")|| textFieldStudentCO.getText().equals("")|| textFieldHSCRoll.getText().equals("")|| textFieldHSCReg.getText().equals("")|| textFieldSSCRoll.getText().equals("")|| textFieldSSCReg.getText().equals("")|| textFieldHSCGPA.getText().equals("")|| textFieldSSCGPA.getText().equals(""))
        {
            flag = true;
            System.out.println("Condition");
        }*/
        buttonSubmit = new Button("Submit");
        GridPane.setConstraints(buttonSubmit, 12, 18);
        
        buttonSubmit.setOnAction(e->{
            if(flag)
            {
                AlertMessage.display("Error","Some Content are not filled", "OK");
            }
            else
            {
                FileHandle fileHandle = new FileHandle();
                String roll = Integer.toString(fileHandle.rollGenerator());
                AlertMessage.display("Roll Number",roll, "OK");
                PrintAdmit printAdmit = new PrintAdmit();
                printAdmit.store.add(roll);
                printAdmit.store.add(textFieldStudentName.getText());
                printAdmit.store.add(textFieldStudentCO.getText());
                printAdmit.store.add(textFieldHSCRoll.getText());
                printAdmit.store.add(textFieldHSCReg.getText());
                printAdmit.store.add(comboboxHSCYear.getValue());
                printAdmit.store.add(textFieldHSCGPA.getText());
                printAdmit.store.add(textFieldSSCRoll.getText());
                printAdmit.store.add(textFieldSSCReg.getText());
                printAdmit.store.add(comboboxSSCYear.getValue());
                printAdmit.store.add(textFieldSSCGPA.getText());
                printAdmit.store.add(comboBoxDivision.getValue());
                printAdmit.store.add(comboBoxDistrict.getValue());
               // printAdmit.store.add(comboBoxUpazila.getValue());
                printAdmit.store.add(checkDatePicker.getValue().toString());
                printAdmit.store.add(photoPath);
                printAdmit.store.add(signeturePath);
                printAdmit.store.add(comboBoxUnit.getValue());
                try{
                    printAdmit.start(primaryStage);
                }
                catch(Exception ex)
                {
                    
                }
                
            }
        });
        
        buttonCancel = new Button("Cancel");
        GridPane.setConstraints(buttonCancel, 5, 18);

        buttonCancel.setOnAction(e -> {
            StudentOption studentOption = new StudentOption();
            try {
                studentOption.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });

        grid.getChildren().addAll(textFieldStudentName, textFieldStudentCO, textFieldHSCRoll, textFieldHSCReg, textFieldSSCRoll, textFieldSSCReg, textFieldHSCGPA, textFieldSSCGPA);
        grid.getChildren().addAll(labelStudentName, labelStudentCO, labelHSCRoll, labelHSCReg, labelSSCRoll, labelSSCReg, labelHSCGPA, labelSSCGPA, labelHSCYear, labelSSCYear, labelAddress, labelUnit,labelDateOfBirth);
        grid.getChildren().addAll(buttonCancel, buttonSubmit, openButton, buttonSigneture);
        grid.getChildren().addAll(comboboxHSCYear, comboboxSSCYear, comboBoxDivision, comboBoxDistrict, checkDatePicker, comboBoxUnit);
        Scene scene = new Scene(grid, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
