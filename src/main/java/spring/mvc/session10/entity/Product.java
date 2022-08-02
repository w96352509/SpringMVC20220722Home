package spring.mvc.session10.entity;

public class Product {

	private String  productName;
	private Integer amount;
	private Double  price;
	
	
	public Product() {
       
	}
	
	public Product(String productName, Integer amount, Double price) {
        this.productName = productName;
		this.amount = amount;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", amount=" + amount + ", price=" + price + "]";
	}
	
}
