package application.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class CreateNewACCController implements Initializable{

	@FXML
	private Button buttonSubmit,buttonUpload,buttonBack;
	@FXML
	private TextField textFieldName,textFieldCareOf,textFieldAddress,textFieldNidNo,textFieldPhone,textFieldUserId; 
	@FXML
	private PasswordField passwordField,passwordFieldConfirm;
	@FXML
	private ImageView imageView;
	@FXML
	private DatePicker datepicker;
	@FXML
	private Label labelImg;
	
	
	
	
	private String photoPath=null;
	private FileChooser fileChooser;
	
	
	
	
	@FXML
	public void buttonSubmitClick(ActionEvent event) throws FileNotFoundException
	{
		if(textFieldName.getText().equals("")||textFieldCareOf.getText().equals("")||textFieldAddress.getText().equals("")||textFieldNidNo.getText().equals("")||textFieldPhone.getText().equals(""))
		{
			AlertMessage.display("Error","Fill all the star contents", "OK");
		}
		if(textFieldUserId.getText().equals("")||photoPath.equals(""))
		{
			AlertMessage.display("Error","Enter user id and photos", "OK");
		}
		if((!passwordField.getText().equals(passwordFieldConfirm.getText())||passwordField.getText().equals("")||passwordFieldConfirm.getText().equals("")))
		{
			System.out.println("pass");
		}
		else
		{
			DatabaseStore db = new DatabaseStore();
			File source1 = new File(photoPath);
			File dest1 = new File("Store\\photos\\" + textFieldUserId + ".jpg");
			try {
				FileUtils.copyFile(source1, dest1);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			if(db.insertTemporary(textFieldName.getText(),textFieldCareOf.getText(), textFieldAddress.getText(), datepicker.getValue().toString(),textFieldNidNo.getText(),textFieldPhone.getText(),textFieldUserId.getText(),passwordField.getText(),dest1.getAbsolutePath()))
			{
				AlertMessage.display("Success", "Successfully registered", "Ok");
			}
			else
			{
				AlertMessage.display("Error", "User Id must be Unique", "Ok");
			}
			buttonSubmit.setVisible(false);
		}
	}
	@FXML
	public void buttonUploadClick(ActionEvent event)
	{
		labelImg.setVisible(false);
		
		try{  
            Window primaryStage = null;
			File file = fileChooser.showOpenDialog(primaryStage);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            photoPath = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            imageView.setFitHeight(180);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            
          }
          catch(Exception ex)
          {
              System.out.println(ex);
          }
	}
	@FXML
	public void buttonBackClick(ActionEvent event) throws IOException
	{
		Main.loginPage();
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		labelImg.setVisible(true);
		fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        datepicker.setValue(LocalDate.now());
	}

}
