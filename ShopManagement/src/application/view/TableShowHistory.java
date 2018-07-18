package application.view;

public class TableShowHistory {

	String productName,productQuantity,productPrice,productTotalAmount,sellDate;
	
	

	public TableShowHistory(String productName, String productQuantity, String productPrice, String productTotalAmount,
			String sellDate) {
		super();
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.productTotalAmount = productTotalAmount;
		this.sellDate = sellDate;
	}

	
	public TableShowHistory() {
		super();
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductTotalAmount() {
		return productTotalAmount;
	}

	public void setProductTotalAmount(String productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	
}
