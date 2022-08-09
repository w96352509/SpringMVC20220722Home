package spring.mvc.session12.entity;

public class MyStock {
	private String symbol; // 股票代號: 股票代號必須要有存在於市場中
	private Double price; // 買進價格: 買進價格必須是昨日收盤價的±10%之間
	private Integer amount; // 買進股數: 買進股數必須是1000的倍數(1000股=1張) 
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "MyStock [symbol=" + symbol + ", price=" + price + ", amount=" + amount + "]";
	}
	
	
}
