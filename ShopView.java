package com.qsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class ShopView {
	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		do {
			System.out.println("Select operation to perform : ");
			System.out.println(
					"1.Add product\n2.Remove product\n3.Update product details\n4.Fetch product details\n0.Exit");
			System.out.print("Enter digit respective to desired option : ");
			int userInput = 100;
			try {
				userInput = myInput.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
			}
			myInput.nextLine();
			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println();
				System.out.println("- - - - - EXITED - - - - -");
				System.exit(0);
				break;
			case 1:
				ArrayList<Product> products = new ArrayList<Product>();
				while (true) {
					Product temp = new Product();
					System.out.println("Enter product id : ");
					int i_p_id = myInput.nextInt();
					temp.setId(i_p_id);
					myInput.nextLine();
					System.out.println("Enter product name : ");
					String i_p_name = myInput.nextLine();
					temp.setName(i_p_name);
					System.out.println("Enter price : ");
					int i_p_price = myInput.nextInt();
					temp.setPrice(i_p_price);
					myInput.nextLine();
					System.out.println("Enter quantity : ");
					int i_p_quantity = myInput.nextInt();
					temp.setQuantity(i_p_quantity);
					myInput.nextLine();
					boolean i_p_availability = false;
					if (i_p_quantity > 0) {
						i_p_availability = true;
					}
					temp.setp_availability(i_p_availability);
					products.add(temp);
					System.out.println("- - - - Do you want to continue inserting? If you want to exit enter 1");
					String userChoice = myInput.nextLine();
					if (userChoice.equals("1")) {
						break;
					}
				}
				int rowsAffected = shopController.addProducts(products, products.size());
				if (rowsAffected < products.size()) {
					System.out.println("Some error happened while inserting data");
				} else {
					System.out.println(rowsAffected + " products have been added.");
				}
				System.out.println();
				break;
			case 2:
				System.out.println("Enter the id of the product to be removed : ");
				int product_Id = myInput.nextInt();
				int removeProduct = shopController.removeProduct(product_Id);
				System.out.println("Number of rows affected : " + removeProduct);
				System.out.println();
				break;
			case 3:
				Product temp = new Product();
				System.out.println("Enter the product id : ");
				int productIdtoUpdate = myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchProductforUpdate = shopController.fetchProduct(productIdtoUpdate);
				
				try {
					if (fetchProductforUpdate.next()) {
						temp.setId(productIdtoUpdate);
						temp.setName(fetchProductforUpdate.getString(2));
						temp.setPrice(fetchProductforUpdate.getInt(3));
						int quantity = fetchProductforUpdate.getInt(4);
						temp.setQuantity(fetchProductforUpdate.getInt(4));
						if(quantity<1) {
							temp.setp_availability(false);
						}else {
							temp.setp_availability(true);
						}
						System.out.println("Do you want to change name? If yes enter '0' : ");
						byte choice = myInput.nextByte();
						if(choice==0) {
							System.out.println("Enter the new name : ");
							temp.setName(myInput.next());
						}
						System.out.println("Do you want to change price? If yes enter '0' : ");
						choice = myInput.nextByte();
						if(choice==0) {
							System.out.println("Enter the new price : ");
							temp.setPrice(myInput.nextInt());
							myInput.nextLine();
						}
						System.out.println("Do you want to change quantity? If yes enter '0' : ");
						choice = myInput.nextByte();
						if(choice==0) {
							System.out.println("Enter the new quantity : ");
							int newQuantity = myInput.nextInt();
							myInput.nextLine();
							if(newQuantity<1) {
								temp.setp_availability(false);
							}else {
								temp.setp_availability(true);
							}
						}
						int countRecords = shopController.updateProduct(temp);
						System.out.println("Number of records affected : "+countRecords);
					} else {
						System.out.println("Product with id " + productIdtoUpdate + " does not exists");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println();
				break;
			case 4:
				System.out.println("Enter the product id : ");
				int productId = myInput.nextInt();
				myInput.nextLine();
				ResultSet resultSet = shopController.fetchProduct(productId);
				try {
					boolean next = resultSet.next();
					if (next) {
						System.out.println("id : " + resultSet.getInt(1));
						System.out.println("name : " + resultSet.getString(2));
						System.out.println("price : " + resultSet.getInt(3));
						System.out.println("quantity : " + resultSet.getInt(4));
						if (resultSet.getBoolean(5)) {
							System.out.println("Availability : availabe");
						} else {
							System.out.println("Availability : not available");
						}
					} else {
						System.out.println("Product with id " + productId + " does not exisits");
						System.out.println();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("-----Invalid Input-----");
				break;
			}
		} while (true);
	}

}
