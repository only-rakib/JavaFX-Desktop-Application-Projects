package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginSceneController implements Initializable {
	public static String userIdforShow=null;
	public static String userPassword=null;
	
	@FXML
	private Button buttonSubmit, buttonClear, buttonCancel, buttonNewAcc;
	@FXML
	private TextField textFieldName;
	@FXML
	private PasswordField passwordFieldPassword;
	@FXML
	private ComboBox<String> comboBoxLoginAs;

	@FXML
	public void buttonClearAction(ActionEvent event) {

		textFieldName.setText("");
		passwordFieldPassword.setText("");

	}

	@FXML
	public void buttonCancelAction(ActionEvent event) {

		Platform.exit();
	}

	@FXML
	public void buttonSubmitAction(ActionEvent event) throws SQLException {
		String s = comboBoxLoginAs.getValue();
		if (comboBoxLoginAs.getValue().toString().equals("Select")) {
			AlertMessage.display("Error", "Select user", "OK");
		}
		else if (textFieldName.getText().equals("") || passwordFieldPassword.getText().equals("")) {
			AlertMessage.display("Error", "Name or Password is null", "OK");
		} 
		else {
			boolean res = false;
			DatabaseStore conn = new DatabaseStore();
			if (s.equals("Admin")) {
				//System.out.println(s);
				res = conn.loginAdmin(textFieldName.getText(), passwordFieldPassword.getText(), 1);
				if (res) {
					
					try {
						ChangePasswordAdminController.selectUser=1;
						userIdforShow = textFieldName.getText();
						userPassword = passwordFieldPassword.getText();
						StorePageController.setSignal(1);
						StorePageController.setNameOfSaler(userIdforShow);
						ShowSalesManHistoryController.setIntSignal(1);
						ShowSalesManHistoryController.setStringUserID(userIdforShow);
						
						Main.adminOption();
					} catch (IOException e) {

						e.printStackTrace();
					}
				} 
				else {
					AlertMessage.display("Error", "User Name or Password incorrect", "OK");
				}
			}
			else {
				res = conn.loginAdmin(textFieldName.getText(), passwordFieldPassword.getText(), 2);
				if (res) {
					
					try {
						ChangePasswordAdminController.selectUser = 2;
						userIdforShow = textFieldName.getText();
						userPassword = passwordFieldPassword.getText();
						StorePageController.setSignal(2);
						StorePageController.setNameOfSaler(userIdforShow);
						ShowSalesManHistoryController.setIntSignal(2);
						ShowSalesManHistoryController.setStringUserID(userIdforShow);
						Main.salesmanOption();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					AlertMessage.display("Error", "User Name or Password incorrect", "OK");
				}

			}

		}
	}
	@FXML
	public void buttonNewAccClick(ActionEvent event) throws IOException
	{
		Main.createNewAccount();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// comboBoxLoginAs = new ComboBox<>();
		comboBoxLoginAs.getItems().addAll("Admin", "Salesman");
		comboBoxLoginAs.setValue("Select");
	}

}
