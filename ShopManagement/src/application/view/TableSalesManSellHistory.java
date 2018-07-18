package application.view;

public class TableSalesManSellHistory {
	private String date,totalAmount;
	
	
	
	public TableSalesManSellHistory() {
		super();
	}

	public TableSalesManSellHistory(String date, String totalAmount) {
		super();
		this.date = date;
		this.totalAmount = totalAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
