package spring.mvc.session14.entity;

public class Div {

	int x ;
	int y;
	int result;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Div [x=" + x + ", y=" + y + ", result=" + result + "]";
	}
	
	
}
