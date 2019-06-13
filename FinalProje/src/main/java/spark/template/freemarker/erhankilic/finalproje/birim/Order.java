package spark.template.freemarker.erhankilic.finalproje.birim;

public class Order {
	
	private int orderId;
	private String userName;
	private String productIDs;
	private int sumPrice;

	public Order(int orderId, String userName, String productIDs, int sumPrice) {
        this.orderId = orderId;
        this.userName = userName;
        this.productIDs = productIDs;
        this.sumPrice = sumPrice;
    }

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductIDs() {
		return productIDs;
	}

	public void setProductIDs(String productIDs) {
		this.productIDs = productIDs;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

}
