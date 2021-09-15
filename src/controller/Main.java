package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.LoginDAO;
import dao.ProductDAO;
import dao.TransactionDAO;
import model.Login;
import model.Product;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x,option;
		Login login = new Login();
		Product product = new Product();
		LoginDAO logindao = new LoginDAO();
		ProductDAO productdao = new ProductDAO();
		TransactionDAO transaction = new TransactionDAO();
		
		do
		{
			System.out.println("1.Admin");
			System.out.println("2.Agent");
			System.out.println("3.Exit");
			System.out.println("---------------------------------------------");
			x = Integer.parseInt(br.readLine());
			
			switch(x)
			{
			case 1:
				System.out.println("---------------------------------------------");
				System.out.println("Enter username");
				String username = br.readLine();
				System.out.println("Enter password");
				String password = br.readLine();
				login.setPassword(password);
				login.setUsername(username);
				if(logindao.validate(login)==true)
				{
					System.out.println("---------------------------------------------");
					System.out.println("Login Successful!");
					do 
					{
						System.out.println("---------------------------------------------");
						System.out.println("1.Add Product");
						System.out.println("2.Display inventory details");
						System.out.println("3.Logout");
						System.out.println("---------------------------------------------");
						option = Integer.parseInt(br.readLine());
						switch(option)
						{
						case 1: System.out.println("Enter product id");
						int productid = Integer.parseInt(br.readLine());
						System.out.println("Enter product name");
						String productname = br.readLine();
						System.out.println("Enter min sell quantity");
						int minsell = Integer.parseInt(br.readLine());
						System.out.println("Enter the price");
						int price = Integer.parseInt(br.readLine());
						System.out.println("Enter the quantity");
						int quantity = Integer.parseInt(br.readLine());
						product.setMinsell(minsell);
						product.setPrice(price);
						product.setProductid(productid);
						product.setProductname(productname);
						product.setQuantity(quantity);
						productdao.addProduct(product);
						System.out.println("---------------------------------------------");
						break;
						case 2:productdao.display();
						break;
						case 3:
							break;
						}
					}
					while(option!=3);
				}
				else
				{
					System.out.println("Login Unsuccessful");
					System.out.println("---------------------------------------------");
				}
				break;
			case 2: 
				System.out.println("---------------------------------------------");
				System.out.println("Enter username");
				String username1 = br.readLine();
				System.out.println("Enter password");
				String password1 = br.readLine();
				login.setPassword(password1);
				login.setUsername(username1);
				if(logindao.validate(login)==true)
				{
					do
					{
						System.out.println("---------------------------------------------");
						System.out.println("Login Successful!");
						System.out.println("---------------------------------------------");
						System.out.println("1.Buy/Sell");
						System.out.println("2.Transaction Details");
						System.out.println("3.Logout");
						System.out.println("---------------------------------------------");
						option = Integer.parseInt(br.readLine());
						switch(option)
						{
						case 1: System.out.println("Buy/Sell");
							String agent = br.readLine();
							int productid;
							if(agent.equals("Buy"))
							{
								System.out.println("---------------------------------------------");
								System.out.println("Enter product id");
								productid = Integer.parseInt(br.readLine());
								System.out.println("Enter product name");
								String productname = br.readLine();
								System.out.println("Enter min sell quantity");
								int minsell = Integer.parseInt(br.readLine());
								System.out.println("Enter the price");
								int price = Integer.parseInt(br.readLine());
								System.out.println("Enter the quantity");
								int quantity = Integer.parseInt(br.readLine());
								product.setMinsell(minsell);
								product.setPrice(price);
								product.setProductid(productid);
								product.setProductname(productname);
								product.setQuantity(quantity);
								productdao.addProduct(product);
								transaction.buyDetails(product,agent);
								System.out.println("---------------------------------------------");
								
							}
							else if(agent.equals("Sell"))
							{
								System.out.println("---------------------------------------------");
								System.out.println("Enter product id");
								productid = Integer.parseInt(br.readLine());
								System.out.println("Enter the quantity");
								int quantity = Integer.parseInt(br.readLine());
								if(productdao.quantityAvailable(quantity, productid))
								{
									int total = productdao.totalcost(quantity, productid);
									System.out.println("---------------------------------------------");
									System.out.println("Total cost is "+total);
									System.out.println("---------------------------------------------");
									System.out.println("Confirm booking(Yes/No)");
									String booking = br.readLine();
									System.out.println("---------------------------------------------");
									if(booking.equalsIgnoreCase("Yes"))
									{
										transaction.sellDetails(productid,agent,quantity);
									}
								}
								else
								{
									System.out.println("Product not available");
								}
							}
							break;
						case 2: transaction.display();
						break;
						case 3: break;
						}
					}
					while(option!=3);
				}
				else
				{
					System.out.println("Login Unsuccessful");
				}
				break;
				case 3: System.exit(0);
				break;
			}	
		}
		while(x!=3);
	}
}
