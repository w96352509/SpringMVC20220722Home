package spring.mvc.session12.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.mvc.session12.entity.MyStock;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Component
public class MyStockValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 先判斷傳進來的 clazz 是否是 MyClass 類
		return MyStock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 基礎驗證
		ValidationUtils.rejectIfEmpty(errors, "symbol", "mystock.symbol.notEmpty");
		ValidationUtils.rejectIfEmpty(errors, "price", "mystock.price.notEmpty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "mystock.amount.notEmpty");
		
		MyStock myStock = (MyStock)target;
		// 進階驗證
		try {
			Stock stock = YahooFinance.get(myStock.getSymbol());
			// 股票代號必須要有存在於市場中
			if(stock == null) {
				errors.rejectValue("symbol", "mystock.symbol.notFound");
			} else {
				// 昨日收盤價
				double previousClose = stock.getQuote().getPreviousClose().doubleValue();
				// 最新成交價
				double price = stock.getQuote().getPrice().doubleValue();
				System.out.println(price);
				// 驗證買進價格必須是昨日收盤價的±10%之間
				if(myStock.getPrice() != null && (myStock.getPrice() > previousClose * 1.1 || 
						myStock.getPrice() < previousClose * 0.9)) {
					errors.rejectValue("price", "mystock.price.range",new Object[] {previousClose * 0.9 , previousClose * 1.1},"買進價格必須是昨日收盤價的±10%之間");
					errors.reject("price", 
							String.format("作收:%.2f 最新成交價:%.2f", previousClose, price));
				}
				// 買進股數必須是1000的倍數(1000股=1張)
				if(myStock.getAmount() != null && (myStock.getAmount() < 1000 || myStock.getAmount() % 1000 != 0)) {
					errors.rejectValue("amount", "mystock.amount.error");
				}
			}
		} catch (Exception e) {
			errors.rejectValue("symbol", "yahoo.finance.error");
		}
		
		
		
	}

	
}
