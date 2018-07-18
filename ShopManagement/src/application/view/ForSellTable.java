package application.view;

public class ForSellTable {
	String name,quantity,totalPrice,available,guarantee;
	

	ForSellTable(){
		
	};
	public String getGuarantee() {
		return guarantee;
	}
	
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public ForSellTable(String name, String quantity, String totalPrice,String guarantee) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.guarantee=guarantee;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
