package spark.template.freemarker.erhankilic.finalproje.birim;

public class Product {
	
	  	private int productId;
	    private String productName;
	    private String productCategory;
	    private int price;

	    public Product(int productId, String productName, String productCategory, int price) {
	        this.productId = productId;
	        this.productName = productName;
	        this.productCategory = productCategory;
	        this.price = price;
	    }

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductCategory() {
			return productCategory;
		}

		public void setProductCategory(String productCategory) {
			this.productCategory = productCategory;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

	 
}
