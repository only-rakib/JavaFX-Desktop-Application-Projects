package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ChangePasswordAdminController implements Initializable {

	public static int selectUser ;
	
	@FXML
	PasswordField textOldPassword,textNewPassword,textConfirmPassword;
	@FXML
	Button buttonOk,buttonCancel;
	
	@FXML
	public void buttonOkClick(ActionEvent event)
	{
		DatabaseStore db = new DatabaseStore();
		
		if(selectUser == 1)
		{
			//System.out.println(selectUser);
			if(LoginSceneController.userPassword.equals(textOldPassword.getText())){
				if(textNewPassword.getText().equals(""))
				{
					AlertMessage.display("Error","Password field empty", "OK");
				}
				else if(textNewPassword.getText().equals(textConfirmPassword.getText()))
				{
					db.changePassword(LoginSceneController.userIdforShow, textNewPassword.getText(), selectUser);
					AlertMessage.display("Success","Successfully Updated", "OK");
					try {
						Main.adminOption();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					AlertMessage.display("Error","New and Confirm password not matched", "OK");
				}
			}
			else
			{
				AlertMessage.display("Error","Old password not matched", "OK");
			}
			
		}
		else if(selectUser==2)
		{
			if(LoginSceneController.userPassword.equals(textOldPassword.getText())){
				if(textNewPassword.getText().equals(""))
				{
					AlertMessage.display("Error","Password field empty", "OK");
				}
				else if(textNewPassword.getText().equals(textConfirmPassword.getText()))
				{
					db.changePassword(LoginSceneController.userIdforShow, textNewPassword.getText(), selectUser);
					AlertMessage.display("Success","Successfully Updated", "OK");
					try {
						Main.salesmanOption();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					AlertMessage.display("Error","New and Confirm password not matched", "OK");
				}
			}
			else
			{
				AlertMessage.display("Error","Old password not matched", "OK");
			}
		}
		else
		{
			System.out.println("Error  ok button");
		}
	}
	@FXML
	public void buttonBackClick(ActionEvent event) throws IOException
	{
		if(selectUser == 1)
		{
			Main.adminOption();
		}
		else if(selectUser==2)
		{
			Main.salesmanOption();
		}
		else
		{
			System.out.println("Error  back button");
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
