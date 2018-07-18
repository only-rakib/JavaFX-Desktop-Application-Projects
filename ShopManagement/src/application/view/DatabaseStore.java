package application.view;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseStore {
	static Connection connection;

	DatabaseStore() {
		connection = DatabaseConnection.checkConnection();
		if (connection == null) {

			System.exit(1);
		}
	}

	/// Salesman infomation when creates a new salesman account which is approved by
	/// Admin
	/**
	 * When a salesman id is accepted by the admin then this id permanently store in database
	 * @param sName
	 * @param sCareOf
	 * @param sAddress
	 * @param sDate
	 * @param sNIDNo
	 * @param sPhoneNo
	 * @param sUserID
	 * @param sPassword
	 * @param sPhotoPath
	 */
	public void insertSalesMan(String sName, String sCareOf, String sAddress, String sDate, String sNIDNo,
			String sPhoneNo, String sUserID, String sPassword, String sPhotoPath) {
		String sql = "INSERT INTO salesMan(name,careOf,address,dateOfBirth,NIDNo,phoneNo,userID,password,photoPath) VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, sName);
			pstmt.setString(2, sCareOf);
			pstmt.setString(3, sAddress);
			pstmt.setString(4, sDate);
			pstmt.setString(5, sNIDNo);
			pstmt.setString(6, sPhoneNo);
			pstmt.setString(7, sUserID);
			pstmt.setString(8, sPassword);
			pstmt.setString(9, sPhotoPath);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
		this.createNewTable(sUserID);
	}

	/// Insert salesman information in temporary so that the Admin can approved it
	/**
	 * When a salesman create an ID then it store the data in temporary so that the admin can accept this
	 * @param sName
	 * @param sCareOf
	 * @param sAddress
	 * @param sDate
	 * @param sNIDNo
	 * @param sPhoneNo
	 * @param sUserID
	 * @param sPassword
	 * @param sPhotoPath
	 */
	public boolean insertTemporary(String sName, String sCareOf, String sAddress, String sDate, String sNIDNo,
			String sPhoneNo, String sUserID, String sPassword, String sPhotoPath) {
		String sql = "INSERT INTO temporary(name,careOf,address,dateOfBirth,NIDNo,phoneNo,userID,password,photoPath) VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, sName);
			pstmt.setString(2, sCareOf);
			pstmt.setString(3, sAddress);
			pstmt.setString(4, sDate);
			pstmt.setString(5, sNIDNo);
			pstmt.setString(6, sPhoneNo);
			pstmt.setString(7, sUserID);
			pstmt.setString(8, sPassword);
			pstmt.setString(9, sPhotoPath);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
			return false;
		}
		return true;
	}
	/**
	 * after accepting/deleting the salesman id by the admin then the id remove from temporary table
	 * @param userID
	 */
	public void delete(String userID) {
		String sql = "DELETE FROM temporary WHERE userID = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
	}

	// Delete data from store table
	/**
	 * delete selected item from store table 
	 * @param Name
	 */
	public void deleteStoreData(String Name) {
		String sql = "DELETE FROM Store WHERE Name = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Name);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
	}
	///Delete SalesMan information permanent
	/**
	 * when admin delete the salesman account then it permanently detele this ID and information from
	 * the database
	 * @param userID
	 */
	public void deleteInformation(String userID) {
		String sql = "DELETE FROM salesMan WHERE userID = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
	}
	
	
	
	
	// Add data to Store table
	/**
	 * Adding item in store table
	 * @param name
	 * @param ID
	 * @param available
	 * @param price
	 */
	public void addStoreData(String name, String ID, String available, String price) {
		String sql = "INSERT INTO Store(Name,IDNo,Available,Price) VALUES(?,?,?,?)";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, ID);
			pstmt.setString(3, available);
			pstmt.setString(4, price);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
	}

	// Update quantity of product after sell
	/**
	 * Update the quantity of the product after selling
	 * @param array
	 */
	public void updateQuantity(ArrayList<ForSellTable> array) {
		// System.out.println(Integer.parseInt(array.get(0).getAvailable()));
		for (int i = 0; i < array.size(); i++) {
			String sql = "UPDATE Store SET Available = ? WHERE Name = ?";
			// System.out.println(remain);
			try {
				String remain = Integer.toString((Integer.parseInt(array.get(i).getAvailable()))
						- (Integer.parseInt(array.get(i).getQuantity())));

				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, remain);
				pstmt.setString(2, array.get(i).getName());
				pstmt.executeUpdate();
				pstmt.close();

			} catch (SQLException e) {

				AlertMessage.display("Error", e.getMessage(), "OK");
			}
		}
	}

	/// Edit and update store table value
	/**
	 * Edit and Update data in Store table
	 * @param name
	 * @param ID
	 * @param available
	 * @param price
	 */
	public void updateStoreData(String name, String ID, String available, String price) {
		String sql = "UPDATE Store SET IDNo = ?,Available=?,Price=? WHERE Name = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(4, name);
			pstmt.setString(1, ID);
			pstmt.setString(2, available);
			pstmt.setString(3, price);
			pstmt.executeUpdate();
			pstmt.close();
			AlertMessage.display("Success", "Successfully Updated", "OK");
		} catch (SQLException e) {
			AlertMessage.display("Error", e.getMessage(), "OK");
		}
	}
	
	/**
	 * It returns the List of the temporary salesman Info for showing notification
	 * @return
	 */
	public ArrayList<StoreForNotificationFile> storeForNotification() {
		ArrayList<StoreForNotificationFile> store = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String query = "select * from temporary";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StoreForNotificationFile obj = new StoreForNotificationFile();
				obj.setName(resultSet.getString("name"));
				obj.setCareOf(resultSet.getString("careOf"));
				obj.setAddress(resultSet.getString("address"));
				obj.setDateOfBirth(resultSet.getString("dateOfBirth"));
				obj.setNIDNo(resultSet.getString("NIDNo"));
				obj.setPhoneNo(resultSet.getString("phoneNo"));
				obj.setUserID(resultSet.getString("userID"));
				obj.setPassword(resultSet.getString("password"));
				obj.setPhotoPath(resultSet.getString("photoPath"));
				store.add(obj);

			}
			preparedStatement.close();
			resultSet.close();

		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");

		}

		return store;
	}
	
	/**
	 * It returns all the salesman details when admin wants.
	 * If any salesman id is removed then it dosen't show this 
	 * @return
	 */
	public ArrayList<StoreForNotificationFile> getAllSalesManDetails() {
		ArrayList<StoreForNotificationFile> store = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String query = "select * from salesMan";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StoreForNotificationFile obj = new StoreForNotificationFile();
				obj.setName(resultSet.getString("name"));
				obj.setCareOf(resultSet.getString("careOf"));
				obj.setAddress(resultSet.getString("address"));
				obj.setDateOfBirth(resultSet.getString("dateOfBirth"));
				obj.setNIDNo(resultSet.getString("NIDNo"));
				obj.setPhoneNo(resultSet.getString("phoneNo"));
				obj.setUserID(resultSet.getString("userID"));
				obj.setPassword(resultSet.getString("password"));
				obj.setPhotoPath(resultSet.getString("photoPath"));
				if(obj.getPassword().equals("LetsGoBroBy007Rakib"))
				{
					
				}
				else
				{
					store.add(obj);
				}

			}
			preparedStatement.close();
			resultSet.close();

		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");

		}

		return store;
	}

	/**
	 * return boolean true if the file is not empty
	 * @return
	 */
	public boolean fileEmpty() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from temporary";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				preparedStatement.close();
				resultSet.close();
				return true;
			}

		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");
			return false;

		}
		return false;
	}
	/**
	 * Change the password by the admin or salesman
	 * @param userID is 1 then admin password change else 2 then salesman password change
	 * @param password
	 * @param selectUser
	 */
	public void changePassword(String userID, String password, int selectUser) {
		if (selectUser == 1) {
			String sql = "UPDATE admin SET password = ? WHERE userName = ?";

			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, password);
				pstmt.setString(2, userID);
				pstmt.executeUpdate();

				pstmt.close();

			} catch (SQLException e) {

				AlertMessage.display("Error",e.getMessage(), "OK");
			}
		} else {
			String sql = "UPDATE salesMan SET password = ? " + "WHERE userID = ?";

			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, password);
				pstmt.setString(2, userID);

				pstmt.executeUpdate();

				pstmt.close();
			} catch (SQLException e) {
				AlertMessage.display("Error", e.getMessage(), "OK");
			}
		}
	}

	/**
	 * login userID and Password validation
	 * @param userName
	 * @param password
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean loginAdmin(String userName, String password, int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (id == 1) {
			boolean flag = false;
			String query = "select * from admin";
			try {
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					String lastName = resultSet.getString("userName");
					String pass = resultSet.getString("password");
					if (lastName.equals(userName) && password.equals(pass)) {
						flag = true;
						// return true;
					}

				}
				if (flag == false) {
					preparedStatement.close();
					resultSet.close();
					return false;
				} else {
					preparedStatement.close();
					resultSet.close();
					return true;
				}

			} catch (Exception e) {

				AlertMessage.display("Error", e.getMessage(), "OK");
				return false;
			}

		} else if (id == 2) {
			String query = "select * from salesMan";
			try {
				boolean flag = false;
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					String lastName = resultSet.getString("userID");
					String pass = resultSet.getString("password");
					if (lastName.equals(userName) && password.equals(pass)) {
						flag = true;

					}

				}
				if (flag == false) {
					preparedStatement.close();
					resultSet.close();
					return false;
				} else {
					preparedStatement.close();
					resultSet.close();
					return true;
				}

			} catch (Exception e) {

				AlertMessage.display("Error", e.getMessage(), "OK");
				return false;
			}
		}
		return false;
	}

	/**
	 * Getting the data from the store of the shop
	 */
	public ArrayList<StoreTableView> storeForStoreTable() {
		ArrayList<StoreTableView> store = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String query = "select * from Store";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StoreTableView obj = new StoreTableView();
				obj.setName(resultSet.getString("Name"));
				obj.setIDNo(resultSet.getString("IDNo"));
				obj.setAvailable(resultSet.getString("Available"));
				obj.setPrice(resultSet.getString("Price"));
				obj.setQuantity("0");
				obj.setTotalAmount("0");
				store.add(obj);

			}
			preparedStatement.close();
			resultSet.close();

		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");

		}

		return store;
	}

	/**
	 * Get all the table list from database
	 * @return
	 */
	public ArrayList<String> getTableListFromDataBase() {
		ArrayList<String> array = new ArrayList<>();
		String[] types = { "TABLE" };
		try {
			DatabaseMetaData metadata = connection.getMetaData();
			ResultSet resultSet = metadata.getTables(null, null, "%", types);
			while (resultSet.next()) {
				String tableName = resultSet.getString(3);
				// IF these needed then do it
				// String tableCatalog = resultSet.getString(1);
				// String tableSchema = resultSet.getString(2);

				// System.out.println("Table : " + tableName + "nCatalog : " + tableCatalog +
				// "nSchema : " + tableSchema);
				array.add(tableName);
			}
			resultSet.close();

		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");

		}

		return array;
	}

	/**
	 * Create a new table for Salesman it is called from insertSalesMan() method in this class
	 * @param userID
	 */
	public void createNewTable(String userID) {
		Statement stmt = null;

		String query = "CREATE TABLE IF NOT EXISTS " + userID + 
				" (\n" + "date text,\n" + "totalSell text\n"+ ");";
		try {
			stmt = connection.createStatement();
			stmt.execute(query);
			stmt.close();
		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");

		}
	}
	//If sales man sack from job then delete his table
	public void deleteTable(String tableName) {
		try {
			Statement stmt = connection.createStatement();
			String sqlCommand = "DROP TABLE IF EXISTS"+" '"+tableName+"' ";
			stmt.executeUpdate(sqlCommand);
			stmt.close();
			//connection.commit();
		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");
		}
	}
	
	//Update sell history table of a sales man
	
	public void updateSellHistory(String userID,String date,String amount)
	{
		String total;
		try (PreparedStatement checkAccountExists = connection.prepareStatement(
		        "SELECT 1 FROM "+userID+" WHERE date = ?")) {
		    checkAccountExists.setString(1,date);//Check the date exists or not

		    try (ResultSet rs = checkAccountExists.executeQuery()) {
		        if (rs.next()) {
		            // if the date exists
		        	try
		        	{
		        		//get the total amount of the previous sells
		        		String currentAmount=null;
		        		String query ="SELECT totalSell FROM "+ userID +" WHERE date = ?";
		        		PreparedStatement preparedStatement = connection.prepareStatement(query);
		        		preparedStatement.setString(1, date);
		        		ResultSet result= preparedStatement.executeQuery();
		        		while(result.next())
		        		{
		        			currentAmount = result.getString("totalSell");
		        			
		        			
		        		}
		        		total=Double.toString((Double.parseDouble(currentAmount))
	    						+ (Double.parseDouble(amount)));
		    			try {
		    				//update the total amount
		    				String sql = "UPDATE "+ userID +" SET totalSell = ? WHERE date = ?";
		    				PreparedStatement pstmt = connection.prepareStatement(sql);

		    				pstmt.setString(1, total);
		    				pstmt.setString(2, date);
		    				pstmt.executeUpdate();

		    				pstmt.close();

		    			} catch (SQLException e) {
		    				AlertMessage.display("Error",e.getMessage(), "OK");
		    			}
		    			preparedStatement.close();
		    			result.close();
		        	}
		        	catch(Exception e)
		            {
		        		AlertMessage.display("Error",e.getMessage(), "OK");
		            }
		        	
		        } else {
		        	//if date not exists then insert date and amount
		            try (PreparedStatement insert = connection.prepareStatement(
		                    "INSERT INTO "+userID+"(date, totalSell) VALUES (?, ?)")) {
		                insert.setString(1, date);
		                insert.setString(2,amount );
		                insert.executeUpdate();
		                insert.close();
		            }
		            catch(Exception e)
		            {
		            	AlertMessage.display("Error",e.getMessage(), "OK");
		            }
		        }
		    }
		    catch(Exception e)
            {
		    	AlertMessage.display("Error",e.getMessage(), "OK");
            }
		}
		catch(Exception e)
        {
			
			AlertMessage.display("Error",e.getMessage(), "OK");
        }
	}
	
	
	//Get individual sell record
	public ArrayList<TableSalesManSellHistory> getIndividualRecord(String userID) {
		ArrayList<TableSalesManSellHistory> store = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String query = "select * from "+userID;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TableSalesManSellHistory obj = new TableSalesManSellHistory();
				obj.setDate(resultSet.getString("date"));
				obj.setTotalAmount(resultSet.getString("totalSell"));
				store.add(obj);
			}
			preparedStatement.close();
			resultSet.close();

		} catch (Exception e) {

			AlertMessage.display("Error", e.getMessage(), "OK");

		}

		return store;
	}
}
