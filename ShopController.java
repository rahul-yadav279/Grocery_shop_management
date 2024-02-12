package com.qsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;
import com.qsp.shop.model.Product;

public class ShopController {
	public int addProducts(ArrayList<Product> products, int count) {
		Connection connection = null;
		int rowsAffected = 0;
		try {
			// Step 1 Load Driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			// Step 2 Establish Connection
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);

			// Step 3 Create Statement
			String query = "INSERT INTO product VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			for (Product obj : products) {
				preparedStatement.setInt(1, obj.getId());
				preparedStatement.setString(2, obj.getName());
				preparedStatement.setInt(3, obj.getPrice());
				preparedStatement.setInt(4, obj.getQuantity());
				preparedStatement.setBoolean(5, obj.isp_availability());

				preparedStatement.addBatch();
			}

			// Step 4 Execute Statement
			int[] executeBatch = preparedStatement.executeBatch();
			for (int i = 0; i < executeBatch.length; i++) {
				rowsAffected += executeBatch[i];
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
	}

	public ResultSet fetchProduct(int productId) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			// Step 1 Load Driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			// Step 2 Establish Connection
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);

			// Step 3 Create Statement
			String str = "SELECT * FROM product WHERE p_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(str);
			preparedStatement.setInt(1, productId);

			// Step 4 Execute Statement
			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}

	public int removeProduct(int productId) {
		Connection connection = null;
		int productRemoved = 0;
		try {
			// Step 1 Load Driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			// Step 2 Establish Connection
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);

			// Step 3 Create Statement
			String str = "DELETE FROM product WHERE p_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(str);
			preparedStatement.setInt(1, productId);

			// Step 4 Execute Statement
			productRemoved = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return productRemoved;
	}
	public int updateProduct(Product productUpdate) {
		Connection connection = null;
		int rowsAffected = 0;
		try {
			// Step 1 Load Driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			// Step 2 Establish Connection
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);

			// Step 3 Create Statement
			String str = "UPDATE product SET p_name = ?, p_price = ?, p_quantity = ?, p_availability = ? WHERE p_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(str);
			preparedStatement.setString(1, productUpdate.getName());
			preparedStatement.setInt(2, productUpdate.getPrice());
			preparedStatement.setInt(3, productUpdate.getQuantity());
			preparedStatement.setBoolean(4, productUpdate.isp_availability());
			preparedStatement.setInt(5, productUpdate.getId());
			
			// Step 4 Execute Statement
			rowsAffected = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
	}
}
