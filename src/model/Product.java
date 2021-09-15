package model;

public class Product {
	private int productid;
	private String productname;
	private int minsell;
	private int price;
	private int quantity;
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getMinsell() {
		return minsell;
	}
	public void setMinsell(int minsell) {
		this.minsell = minsell;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
