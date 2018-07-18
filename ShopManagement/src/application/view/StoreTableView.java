package application.view;

public class StoreTableView {
	private String name,IDNo,available,price,quantity,totalAmount;
	
	StoreTableView(){
		
	};
	
	public StoreTableView(String name, String iDNo, String available, String price, String quantity) {
		super();
		this.name = name;
		IDNo = iDNo;
		this.available = available;
		this.price = price;
		this.quantity = quantity;
	}

	public StoreTableView(String totalAmount) {
		super();
		this.totalAmount = totalAmount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public StoreTableView(String name, String iDNo, String available, String price) {
		super();
		this.name = name;
		IDNo = iDNo;
		this.available = available;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIDNo() {
		return IDNo;
	}

	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
