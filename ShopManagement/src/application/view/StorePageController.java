package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class StorePageController implements Initializable {
	
	public static int signal;
	public static String nameOfSaler;
	public static int getSignal() {
		return signal;
	}

	public static void setSignal(int signal) {
		StorePageController.signal = signal;
	}

	public static String getNameOfSaler() {
		return nameOfSaler;
	}

	public static void setNameOfSaler(String nameOfSaler) {
		StorePageController.nameOfSaler = nameOfSaler;
	}

	@FXML
	private AnchorPane anchor;
	@FXML
	Button buttonAdd, buttonDelete, buttonEdit, buttonSell, buttonBack, buttonOK, buttonCancelProduct,
			buttonAddProduct, buttonUpdate;
	@FXML
	TableView<StoreTableView> tableViewList;
	private ObservableList<StoreTableView> tableStore = FXCollections.observableArrayList();
	private ArrayList<StoreTableView> storeTableArrayList;
	private ArrayList<ForSellTable> forSellTableArrayList;
	private ObservableList<ForSellTable> forSellTableObjerve = FXCollections.observableArrayList();
	// private ArrayList
	@SuppressWarnings("rawtypes")
	@FXML
	TableColumn nameCol, idCol, availableCol, priceCol, quantityCol, totalAmountCol;
	@FXML
	Label labelAmount, labelAmountText, labelName, labelAddress, labelPhone, labelNameOfProduct, labelIDOfProduct,
			labelAvailableOfProduct, labelPriceOfProduct,labelUserIDName,labelDate,labelCustomerType;

	@FXML
	RadioButton radioWholeSaler,radioRetailer;
	@FXML
	TextField textName, textAddress, textPhone, textNameOfProduct, textIDOfProduct, textAvailableOfProduct,
			textPriceOfProduct, filterField;
	// For second table
	@FXML
	private TableView<ForSellTable> tableViewSell;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn columnOne, columnTwo, columnThree,columnFour;
	
	private String nullString = null;
	private String empty = new String();
	private String radioButtonValue = null;
	
	private ToggleGroup radioGroup;
	
	ArrayList<TableShowHistory>arrayHistory = new ArrayList<>();
	
	/**
	 * Edit the quantity column of the store table
	 * @param edited
	 */
	@FXML // For makeing quantity col editable set commit
	public void onEditChange(@SuppressWarnings("rawtypes") CellEditEvent edited) {
		// int cnt = 0;
		StoreTableView quantityEdit = tableViewList.getSelectionModel().getSelectedItem();
		try {
			if((Integer.parseInt((edited.getNewValue().toString())) > Integer.parseInt(quantityEdit.getAvailable()))) 
			{
				AlertMessage.display("Overflow", "Available item is less than quantity", "OK");
			quantityEdit.setQuantity("0");
			quantityEdit.setTotalAmount("0");
			
			
			// cnt =0;
			}
		 else {
			quantityEdit.setQuantity((edited.getNewValue().toString()));
			quantityEdit.setTotalAmount(String.valueOf(
					Integer.parseInt((edited.getNewValue().toString())) * Double.parseDouble(quantityEdit.getPrice())));
			buttonSell.setDisable(false);
		}
		}
		catch(NumberFormatException e)
		{
			Alert alert = new Alert(AlertType.ERROR,"Enter Integer number only",ButtonType.OK);
			alert.setTitle("Error");
			alert.showAndWait();
			quantityEdit.setQuantity("0");
			quantityEdit.setTotalAmount("0");
		}
	}

	/**
	 * Edit the column of the Sell Table
	 * @param edited
	 */
	@FXML
	public void onEditChangeSellTabel(@SuppressWarnings("rawtypes") CellEditEvent edited) {
		// int cnt = 0;
		ForSellTable serviceEdit = tableViewSell.getSelectionModel().getSelectedItem();
		serviceEdit.setGuarantee((edited.getNewValue().toString()));
	}
	
	/**
	 * Action Performed when the sell button click
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void sellButtonClick(ActionEvent event) {
		labelNameOfProduct.setVisible(false);
		labelIDOfProduct.setVisible(false);
		labelAvailableOfProduct.setVisible(false);
		labelPriceOfProduct.setVisible(false);
		textNameOfProduct.setVisible(false);
		textIDOfProduct.setVisible(false);
		textAvailableOfProduct.setVisible(false);
		textPriceOfProduct.setVisible(false);
		buttonAddProduct.setVisible(false);
		buttonCancelProduct.setVisible(false);
		buttonUpdate.setVisible(false);

		
		anchor.setVisible(true);

		double amount = 0.0;
		forSellTableArrayList.clear();
		arrayHistory.clear();
		for (int i = 0; i < tableViewList.getItems().size(); i++) {
			StoreTableView quantityEdit = tableViewList.getItems().get(i);// get rows
			ForSellTable forSellTable = new ForSellTable();
			TableShowHistory tableShowHistory = new TableShowHistory();
			if ((quantityEdit.getQuantity() != null && !quantityEdit.getQuantity().isEmpty())) {
				if ((Integer.parseInt(quantityEdit.getQuantity()) != 0)) {

					forSellTable.setName(quantityEdit.getName());
					forSellTable.setQuantity(quantityEdit.getQuantity());
					forSellTable.setTotalPrice(quantityEdit.getTotalAmount());
					forSellTable.setAvailable(quantityEdit.getAvailable());
					forSellTable.setGuarantee(null);
					amount += Double.parseDouble(quantityEdit.getTotalAmount());
					forSellTableArrayList.add(forSellTable);
					
					//Store salesman history in details
					tableShowHistory.setProductName(forSellTable.getName());
					tableShowHistory.setProductQuantity(forSellTable.getQuantity());
					tableShowHistory.setProductPrice(forSellTable.getTotalPrice());
					arrayHistory.add(tableShowHistory);

				}
			}

		}
		
		
		//Call to store sell history of a salesman in details
		FileHandle fileHandle = new FileHandle();
		fileHandle.salesHistory(arrayHistory,nameOfSaler , labelDate.getText());
				
		columnOne.setCellValueFactory(new PropertyValueFactory<>("name"));

		columnTwo.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		columnThree.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		columnFour.setCellValueFactory(new PropertyValueFactory<>("guarantee"));
		forSellTableObjerve.clear();
		for (int i = 0; i < forSellTableArrayList.size(); i++) {

			forSellTableObjerve.addAll(new ForSellTable(forSellTableArrayList.get(i).getName(),
					forSellTableArrayList.get(i).getQuantity(), forSellTableArrayList.get(i).getTotalPrice(),forSellTableArrayList.get(i).getGuarantee()));

		}
		
		tableViewSell.setItems(forSellTableObjerve);
		tableViewSell.setEditable(true);// For quantity column editable
		columnFour.setCellFactory(TextFieldTableCell.forTableColumn());// For quantity column editable
		DatabaseStore db = new DatabaseStore();
		db.updateQuantity(forSellTableArrayList);
		labelAmount.setText(String.valueOf(amount));
		db.updateSellHistory(getNameOfSaler(), labelDate.getText(), labelAmount.getText());
		tableRefresh();
	}

	@FXML
	public void deleteButtonClick(ActionEvent event) {
		try {
		// YES NO button
		Alert alert = new Alert(AlertType.WARNING, "Do you want to delete?", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Confirmation");
		Optional<ButtonType> result = alert.showAndWait();
		// Yes click
		if (result.get() == ButtonType.YES) {
			// Get deleted row's value
			StoreTableView storeTableView = tableViewList.getSelectionModel().getSelectedItem();
			DatabaseStore database = new DatabaseStore();
			database.deleteStoreData(storeTableView.getName());
			tableViewList.getItems().removeAll(tableViewList.getSelectionModel().getSelectedItems()// Delete row
			);
		}
		}
		catch(Exception e)
		{
			Alert alert = new Alert(AlertType.WARNING, "Select item", ButtonType.OK);
			alert.setTitle("Empty");
			alert.showAndWait();
		}
	}

	// Edit button Acton
	@FXML
	public void editButtonClick(ActionEvent event) {
		try {
			labelNameOfProduct.setVisible(true);
			labelIDOfProduct.setVisible(true);
			labelAvailableOfProduct.setVisible(true);
			labelPriceOfProduct.setVisible(true);
			textNameOfProduct.setVisible(true);
			textNameOfProduct.setEditable(false);
			textIDOfProduct.setVisible(true);
			textAvailableOfProduct.setVisible(true);
			textPriceOfProduct.setVisible(true);
			buttonAddProduct.setVisible(false);
			buttonCancelProduct.setVisible(true);
			buttonUpdate.setVisible(true);

			
			anchor.setVisible(false);

			// get selected row
			StoreTableView storetableView = tableViewList.getSelectionModel().getSelectedItem();
			textNameOfProduct.setText(storetableView.getName());
			textIDOfProduct.setText(storetableView.getIDNo());
			textAvailableOfProduct.setText(storetableView.getAvailable());
			textPriceOfProduct.setText(storetableView.getPrice());
		} catch (NullPointerException e) {
			labelNameOfProduct.setVisible(false);
			labelIDOfProduct.setVisible(false);
			labelAvailableOfProduct.setVisible(false);
			labelPriceOfProduct.setVisible(false);
			textNameOfProduct.setVisible(false);
			textIDOfProduct.setVisible(false);
			textAvailableOfProduct.setVisible(false);
			textPriceOfProduct.setVisible(false);
			buttonAddProduct.setVisible(false);
			buttonCancelProduct.setVisible(false);
			buttonUpdate.setVisible(false);
			Alert alert = new Alert(AlertType.WARNING, "Select items", ButtonType.OK);
			alert.setTitle("Empty");
			alert.showAndWait();
		}
	}

	@FXML
	private void updateProductButtonClick(ActionEvent event) {
		if (textAvailableOfProduct.getText().equals(nullString) || textAvailableOfProduct.getText().equals(empty)) {
			Alert alert = new Alert(AlertType.ERROR, "Available of product is empty", ButtonType.OK);
			alert.setTitle("error");
			alert.showAndWait();	
			
		}
		else
		{
			//System.out.println(textNameOfProduct.getText()+"  "+textIDOfProduct.getText()+"  "+textAvailableOfProduct.getText()+"  "+ textPriceOfProduct.getText());
			DatabaseStore db = new DatabaseStore();
			db.updateStoreData(textNameOfProduct.getText(), textIDOfProduct.getText(), textAvailableOfProduct.getText(), textPriceOfProduct.getText());
			textNameOfProduct.clear();
			textIDOfProduct.clear();
			textAvailableOfProduct.clear();
			textPriceOfProduct.clear();
			tableRefresh();
		}
	}

	@FXML
	public void buttonAddProductClick(ActionEvent event) {

		if ((textNameOfProduct.getText().isEmpty()) || textAvailableOfProduct.getText().isEmpty()
				|| textPriceOfProduct.getText().isEmpty()) {

		} else {
			DatabaseStore db = new DatabaseStore();
			tableStore.addAll(new StoreTableView(textNameOfProduct.getText(), textIDOfProduct.getText(),
					textAvailableOfProduct.getText(), textPriceOfProduct.getText()));
			db.addStoreData(textNameOfProduct.getText(), textIDOfProduct.getText(), textAvailableOfProduct.getText(),
					textPriceOfProduct.getText());
			textNameOfProduct.clear();
			textAvailableOfProduct.clear();
			textIDOfProduct.clear();
			textPriceOfProduct.clear();
		}
	}

	@FXML
	public void buttonCancelProductClick(ActionEvent event) {
		labelNameOfProduct.setVisible(false);
		labelIDOfProduct.setVisible(false);
		labelAvailableOfProduct.setVisible(false);
		labelPriceOfProduct.setVisible(false);
		textNameOfProduct.setVisible(false);
		textIDOfProduct.setVisible(false);
		textAvailableOfProduct.setVisible(false);
		textPriceOfProduct.setVisible(false);
		buttonAddProduct.setVisible(false);
		buttonCancelProduct.setVisible(false);
		buttonUpdate.setVisible(false);
	}

	@FXML
	public void buttonAddClick(ActionEvent event) {
		labelNameOfProduct.setVisible(true);
		labelIDOfProduct.setVisible(true);
		labelAvailableOfProduct.setVisible(true);
		labelPriceOfProduct.setVisible(true);
		textNameOfProduct.setVisible(true);
		textIDOfProduct.setVisible(true);
		textAvailableOfProduct.setVisible(true);
		textPriceOfProduct.setVisible(true);
		buttonAddProduct.setVisible(true);
		buttonCancelProduct.setVisible(true);
		buttonUpdate.setVisible(false);

		
		anchor.setVisible(false);
	}
	
	
	@FXML
	private void buttonBackClick(ActionEvent event) throws IOException
	{
		if(getSignal()==1)
		{
			Main.adminOption();
		}
		else
		{
			Main.salesmanOption();
		}
		
	}
	
	@FXML
	private void buttonOKClick(ActionEvent event)
	{
		ArrayList<ForSellTable>listForPrint = new ArrayList<>();
		
		for (int i = 0; i < tableViewSell.getItems().size(); i++) {
			//System.out.println(i);
			ForSellTable forSellTableObj = tableViewSell.getItems().get(i);// get rows
			listForPrint.add(forSellTableObj);
		}
		
		//File to store sell table to print data
		FileHandle printFile = new FileHandle();
		if(radioWholeSaler.isSelected())
		{
			radioButtonValue = radioWholeSaler.getText();
		}
		else
		{
			radioButtonValue = radioRetailer.getText();
		}
		printFile.fileForPrint(listForPrint, labelDate.getText(),textName.getText(),textAddress.getText(),radioButtonValue,textPhone.getText(),getNameOfSaler());
			try {
				Main.printPage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}
	
	
	// Makeing patern for double value input in textfield
	Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

	UnaryOperator<TextFormatter.Change> filter = c -> {
		String text = c.getControlNewText();
		if (validEditingState.matcher(text).matches()) {
			return c;
		} else {
			return null;
		}
	};

	StringConverter<Double> converter = new StringConverter<Double>() {

		@Override
		public Double fromString(String s) {
			if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
				return 0.0;
			} else {
				return Double.valueOf(s);
			}
		}

		@Override
		public String toString(Double d) {
			return d.toString();
		}
	};
	/// Complete making double value input

	@SuppressWarnings("unchecked")
	// Table configuretion
	private void tableRefresh() {
		tableStore.clear();
		/// All store information table
		forSellTableArrayList = new ArrayList<>();
		storeTableArrayList = new ArrayList<>();
		DatabaseStore db = new DatabaseStore();
		storeTableArrayList = db.storeForStoreTable();

		// Configure Table column
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		idCol.setCellValueFactory(new PropertyValueFactory<>("IDNo"));
		availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalAmountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
		/// get value from database
		for (int i = 0; i < storeTableArrayList.size(); i++) {

			tableStore.addAll(
					new StoreTableView(storeTableArrayList.get(i).getName(), storeTableArrayList.get(i).getIDNo(),
							storeTableArrayList.get(i).getAvailable(), storeTableArrayList.get(i).getPrice()));

		}

		tableViewList.setItems(tableStore);
		tableViewList.setEditable(true);// For quantity column editable
		quantityCol.setCellFactory(TextFieldTableCell.forTableColumn());// For quantity column editable

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//RadioButton Toggle Option
		radioGroup = new ToggleGroup();
		///radioRetailer.get
		
        radioRetailer.setToggleGroup(radioGroup);
        radioWholeSaler.setToggleGroup(radioGroup);
        radioRetailer.setSelected(true);
        /*radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
            	
            	if(old_toggle != null) {
                 if (radioGroup.getSelectedToggle() != null) {

                	 radioButtonValue= radioGroup.getSelectedToggle().getUserData().toString();
                     // Do something here with the userData of newly selected radioButton

                 }

             } 
            }
        });*/
        
		
		//Show user id
		
		labelUserIDName.setText(getNameOfSaler());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //get current date time with Date()
	    Date date = new Date(0);
	    dateFormat.format(date);//No need
	    Calendar cal = Calendar.getInstance();
	    labelDate.setText(dateFormat.format(cal.getTime()));
		
		/// force the field to be numeric only
		textAvailableOfProduct.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textAvailableOfProduct.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
		/// textPriceOfProduct forced input double value
		TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
		textPriceOfProduct.setTextFormatter(textFormatter);

		anchor.setVisible(false);
		
		
		labelNameOfProduct.setVisible(false);
		labelIDOfProduct.setVisible(false);
		labelAvailableOfProduct.setVisible(false);
		labelPriceOfProduct.setVisible(false);
		textNameOfProduct.setVisible(false);
		textIDOfProduct.setVisible(false);
		textAvailableOfProduct.setVisible(false);
		textPriceOfProduct.setVisible(false);
		buttonAddProduct.setVisible(false);
		buttonCancelProduct.setVisible(false);
		buttonUpdate.setVisible(false);
		if(getSignal()==1)
		{
			buttonAdd.setDisable(false);
			buttonDelete.setDisable(false);
			buttonEdit.setDisable(false);
		}
		else
		{
			buttonAdd.setDisable(true);
			buttonDelete.setDisable(true);
			buttonEdit.setDisable(true);
		}
		tableRefresh();

		// Search textfiled KeyOnrelesed action
		@SuppressWarnings("rawtypes")
		FilteredList filterSearch = new FilteredList(tableStore, e -> true);
		filterField.setOnKeyReleased(e -> {
			filterField.textProperty().addListener((observable, oldValue, newValue) -> {
				filterSearch.setPredicate((Predicate<? super StoreTableView>) (StoreTableView std) -> {
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
			sort.comparatorProperty().bind(tableViewList.comparatorProperty());
			tableViewList.setItems(sort);
		});
	}

}
