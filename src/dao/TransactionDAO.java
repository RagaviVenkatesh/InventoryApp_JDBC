package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionManager.ConnectionManager;
import model.Product;

public class TransactionDAO {

	public void buyDetails(Product product, String agent) throws ClassNotFoundException, SQLException {
		
		int id = product.getProductid();
		String name = product.getProductname();
		int quantity = product.getQuantity();
		int price = product.getPrice();
		
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		String sql = "insert into Transaction(productid,productname,quantity,price,transaction)values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2,name);
		ps.setInt(3, quantity);
		ps.setInt(4, price);
		ps.setString(5, agent);		
		ps.executeUpdate();
		conn.closeConnection();		
	}

	public void sellDetails(int productid, String agent, int quantity) throws ClassNotFoundException, SQLException {
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		int rem = 0;
		PreparedStatement st;
		st = con.prepareStatement("select * from product where productid=?");
		st.setInt(1, productid);
		ResultSet rs = st.executeQuery();
		if(rs.next())
		{
			rem = rs.getInt("quantity") - quantity;
		}
		String sql = "insert into transaction(productid,productname,quantity,price,transaction)values(?,?,?,?,?)";
		st= con.prepareStatement(sql);
		st.setInt(1, productid);
		st.setString(2, rs.getString("productname"));
		st.setInt(3, rs.getInt("quantity"));
		st.setInt(4, rs.getInt("price"));
		st.setString(5, agent);
		st.executeUpdate();
		
		String sql1 = "update product set quantity=? where productid=?";
		st= con.prepareStatement(sql1);
		st.setInt(1, rem);
		st.setInt(2, productid);
		st.executeUpdate();
		conn.closeConnection();	
	}

	public void display() throws ClassNotFoundException, SQLException {
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from transaction");
		
		while(rs.next()) {
			System.out.println("---------------------------------------------");
			System.out.println(rs.getInt("productid")+" "+rs.getString("productname")+" "+rs.getInt("quantity")+" "+
		rs.getInt("price")+" "+rs.getString("transaction"));
		}
		conn.closeConnection();
		
	}

}
