package application.view;

public class StoreForNotificationFile {
	private String name,careOf,address,dateOfBirth,NIDNo,phoneNo,userID,password,photoPath;
	StoreForNotificationFile()
	{
		
	}
	
	public StoreForNotificationFile(String name, String careOf, String address, String phoneNo, String userID) {
		super();
		this.name = name;
		this.careOf = careOf;
		this.address = address;
		this.phoneNo = phoneNo;
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCareOf() {
		return careOf;
	}

	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNIDNo() {
		return NIDNo;
	}

	public void setNIDNo(String nIDNo) {
		NIDNo = nIDNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
}
