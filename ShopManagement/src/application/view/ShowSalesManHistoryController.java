package application.view;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowSalesManHistoryController implements Initializable {

	@FXML
	Button buttonBack, buttonShowSellHistory, buttonDeleteAcc,buttonViewDetails,buttonBackForTxtArea;
	@FXML
	Label labelUserID;
	@FXML
	TextField textSeacrh;
	@FXML
	TextArea txtAreaShowDetails;
	@FXML
	TableView<StoreForNotificationFile> tableShowSalesMan;
	@FXML
	TableView<TableSalesManSellHistory> tableSalesManRecord;
	ObservableList<StoreForNotificationFile> tableShowSalesManList = FXCollections.observableArrayList();
	ObservableList<TableSalesManSellHistory> tableSalesManRecordList = FXCollections.observableArrayList();
	@SuppressWarnings("rawtypes")
	@FXML
	TableColumn colName, colCareOf, colAddress, colPhone, colUserID, colDate, colTotalAmount;
	private String salesManIDSelectFromtable;
	private static String stringUserID;
	private static int intSignal;

	// Get set Method for userId and signal
	// If signal==1 then Admin else Salseman
	public static String getStringUserID() {
		return stringUserID;
	}

	public static void setStringUserID(String stringUserID) {
		ShowSalesManHistoryController.stringUserID = stringUserID;
	}

	public static int getIntSignal() {
		return intSignal;
	}

	public static void setIntSignal(int intSignal) {
		ShowSalesManHistoryController.intSignal = intSignal;
	}

	// Table SalesManDetails Configuration
	@SuppressWarnings("unchecked")
	private void salesManDetailsTable() {
		tableShowSalesManList.clear();
		/// All information about SalesMan on table

		ArrayList<StoreForNotificationFile> array = new ArrayList<>();
		DatabaseStore db = new DatabaseStore();
		array = db.getAllSalesManDetails();

		// Configure Table column
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colCareOf.setCellValueFactory(new PropertyValueFactory<>("careOf"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
		colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

		/// get value from database
		for (int i = 0; i < array.size(); i++) {

			tableShowSalesManList.addAll(new StoreForNotificationFile(array.get(i).getName(), array.get(i).getCareOf(),
					array.get(i).getAddress(), array.get(i).getPhoneNo(), array.get(i).getUserID()));

		}

		tableShowSalesMan.setItems(tableShowSalesManList);

	}

	// Show particular user sell record
	@SuppressWarnings("unchecked")
	private void salesManSellRecordTable(String userID) {
		tableSalesManRecordList.clear();
		ArrayList<TableSalesManSellHistory> array = new ArrayList<>();
		DatabaseStore db = new DatabaseStore();
		array = db.getIndividualRecord(userID);

		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
		for (int i = 0; i < array.size(); i++) {
			tableSalesManRecordList
					.addAll(new TableSalesManSellHistory(array.get(i).getDate(), array.get(i).getTotalAmount()));
		}
		tableSalesManRecord.setItems(tableSalesManRecordList);
	}

	@FXML
	// ShowDetails Button Action
	private void buttonShowDetailsClick(ActionEvent event) {
		if (getIntSignal() == 1) {
			try {
				StoreForNotificationFile store = tableShowSalesMan.getSelectionModel().getSelectedItem();
				salesManSellRecordTable(store.getUserID());
				salesManIDSelectFromtable = store.getUserID();
				buttonViewDetails.setVisible(true);
				buttonViewDetails.setDisable(false);
				buttonBackForTxtArea.setVisible(false);
			} catch (Exception e) {
				AlertMessage.display("Error", e.getMessage(), "OK");
			}
		} else {
			salesManSellRecordTable(getStringUserID());
		}
	}

	// Back button Action
	@FXML
	private void buttonBackClick(ActionEvent event) {
		if (getIntSignal() == 1) {
			try {
				Main.adminOption();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				AlertMessage.display("Error", e.getMessage(), "OK");
			}
		} else {
			try {
				Main.salesmanOption();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				AlertMessage.display("Error", e.getMessage(), "OK");
			}
		}

	}
	/**
	 * When click the view details button then open the text area and show the details from .txt file
	 * @param event
	 */
	@FXML
	private void buttonViewDetailsClick(ActionEvent event) {
		tableSalesManRecord.setVisible(false);
		buttonShowSellHistory.setVisible(false);
		buttonViewDetails.setVisible(false);
		buttonBackForTxtArea.setVisible(true);
		textSeacrh.setVisible(false);
		txtAreaShowDetails.setVisible(true);
		try {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(new File("Store\\generate\\"+salesManIDSelectFromtable+".dat")).useDelimiter("\\s+");
		System.out.println(stringUserID);
		while (input.hasNextLine()) {
		    if (input.hasNextLine()) {
		    	txtAreaShowDetails.appendText(input.nextLine() +"\n");
		    } else {
		    	txtAreaShowDetails.appendText(input.next() + "\n"); 
		    }
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			AlertMessage.display("Error", "File Not Found", "OK");
		}
	}
	
	@FXML
	private void buttonBackForTxtAreaClick(ActionEvent event)
	{
		buttonBackForTxtArea.setVisible(false);
		buttonViewDetails.setVisible(true);
		tableSalesManRecord.setVisible(true);
		txtAreaShowDetails.setVisible(false);
	}
	
	// Delete Button click
	@FXML
	private void buttonDeleteClick(ActionEvent event) {

		try {
			StoreForNotificationFile store = tableShowSalesMan.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.CONFIRMATION,"Do you want to delete?",ButtonType.YES,ButtonType.NO);
			Optional<ButtonType> result = alert.showAndWait();
			// Yes click
			DatabaseStore database = new DatabaseStore();
			if (result.get() == ButtonType.YES) {
				// Get deleted row's value
				//StoreTableView storeTableView = tableViewList.getSelectionModel().getSelectedItem();
				Alert alertInside = new Alert(AlertType.CONFIRMATION,"Delete User All informatio?",ButtonType.YES,ButtonType.NO);
				Optional<ButtonType> resultInside = alertInside.showAndWait();
				if(resultInside.get()==ButtonType.YES)
				{
					//Delete all the information about salesman
					database.deleteInformation(store.getUserID());
					database.deleteTable(store.getUserID());
					tableShowSalesMan.getItems().removeAll(tableShowSalesMan.getSelectionModel().getSelectedItems());	
				}
				else
				{
					//Delete Sales record and change password
					//so that he can not login again
					database.deleteTable(store.getUserID());
					tableShowSalesMan.getItems().removeAll(tableShowSalesMan.getSelectionModel().getSelectedItems());
					// Delete row
					database.changePassword(store.getUserID(), "LetsGoBroBy007Rakib", 2);
					
				}
				
			}

		} catch (Exception e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// tableSalesManRecord.setVisible(false);
		labelUserID.setText(getStringUserID());
		if (getIntSignal() == 1) {
			tableShowSalesMan.setDisable(false);
			buttonDeleteAcc.setDisable(false);
			buttonShowSellHistory.setDisable(false);
			buttonViewDetails.setDisable(true);
			textSeacrh.setDisable(false);
			buttonBackForTxtArea.setVisible(false);
			
		} else {
			tableShowSalesMan.setDisable(true);
			buttonDeleteAcc.setDisable(true);
			buttonShowSellHistory.setDisable(true);
			textSeacrh.setDisable(true);
			buttonBackForTxtArea.setVisible(false);
			buttonViewDetails.setDisable(true);

		}
		this.salesManDetailsTable();
		
		// Search textfiled KeyOnrelesed action
				@SuppressWarnings("rawtypes")
				FilteredList filterSearch = new FilteredList(tableShowSalesManList, e -> true);
				textSeacrh.setOnKeyReleased(e -> {
					textSeacrh.textProperty().addListener((observable, oldValue, newValue) -> {
						filterSearch.setPredicate((Predicate<? super StoreForNotificationFile>) (StoreForNotificationFile std) -> {
							if (newValue.isEmpty() || newValue == null) {
								return true;
							}
							String lowerCaseFilter = newValue.toLowerCase();
							if (std.getName().toLowerCase().contains(lowerCaseFilter)) {
								return true;
							}

							return false;
						});
					});
					@SuppressWarnings("rawtypes")
					SortedList sort = new SortedList<>(filterSearch);
					sort.comparatorProperty().bind(tableShowSalesMan.comparatorProperty());
					tableShowSalesMan.setItems(sort);
				});
	}

}
