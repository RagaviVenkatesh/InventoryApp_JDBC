package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionManager.ConnectionManager;
import model.Product;

public class ProductDAO {

	public void addProduct(Product product) throws ClassNotFoundException, SQLException {
		int id = product.getProductid();
		String name = product.getProductname();
		int quantity = product.getQuantity();
		int price = product.getPrice();
		int minsell = product.getMinsell();
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		String sql = "insert into product(productid,productname,minsell,price,quantity)values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2,name);
		ps.setInt(3, minsell);
		ps.setInt(4, price);
		ps.setInt(5, quantity);
		
		ps.executeUpdate();
		conn.closeConnection();
	}

	public void display() throws ClassNotFoundException, SQLException {
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from product");
		
		while(rs.next()) {
			System.out.println("---------------------------------------------");
			System.out.println(rs.getInt("productid")+" "+rs.getString("productname")+" "+rs.getInt("minsell")+" "+
		rs.getInt("price")+" "+rs.getInt("quantity"));
		}
		conn.closeConnection();
	}
	
	public boolean quantityAvailable(int quantity, int productid) throws ClassNotFoundException, SQLException
	{
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		PreparedStatement st = con.prepareStatement("select quantity,minsell from product where productid=?");
		st.setInt(1, productid);
		ResultSet rs = st.executeQuery();
		
		if(rs.next())
		{
			if(rs.getInt("quantity")>=quantity && rs.getInt("minsell")>=quantity) {
				conn.closeConnection();
				return true;
			}
			else
			{
				conn.closeConnection();
				return false;
			}
		}
		return false;		
	}
	
	public int totalcost(int quantity,int productid) throws ClassNotFoundException, SQLException
	{
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		PreparedStatement st = con.prepareStatement("select price from product where productid=?");
		st.setInt(1, productid);
		int total = 0;
		ResultSet rs = st.executeQuery();
		if(rs.next())
		{
			total = quantity * rs.getInt("price");
		}
		conn.closeConnection();
		return total;
	}	
}
