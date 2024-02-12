package com.qsp.shop.model;

public class Product {
	
	private int p_id;
	private String p_name;
	private int p_price;
	private int p_quantity;
	private boolean p_availability;
	
	public int getId() {
		return p_id;
	}
	public void setId(int p_id) {
		this.p_id = p_id;
	}
	public String getName() {
		return p_name;
	}
	public void setName(String p_name) {
		this.p_name = p_name;
	}
	public int getPrice() {
		return p_price;
	}
	public void setPrice(int p_price) {
		this.p_price = p_price;
	}
	public int getQuantity() {
		return p_quantity;
	}
	public void setQuantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}
	public boolean isp_availability() {
		return p_availability;
	}
	public void setp_availability(boolean p_availability) {
		this.p_availability = p_availability;
	}
	
	
}
