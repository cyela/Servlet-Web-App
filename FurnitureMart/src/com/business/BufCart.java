package com.business;

import java.io.Serializable;

public class BufCart implements Serializable {
	private String ProductId;
	private String ProductName;
	private String emailId;
	private String DateAdded;
	private String quantity;
	private String price;

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDateAdded() {
		return DateAdded;
	}

	public void setDateAdded(String dateAdded) {
		DateAdded = dateAdded;
	}

	public BufCart(String productId, String productName, String emailId, String dateAdded, String quantity,
			String price) {
		super();
		ProductId = productId;
		ProductName = productName;
		this.emailId = emailId;
		DateAdded = dateAdded;
		this.quantity = quantity;
		this.price = price;
	}

	public BufCart() {
		super();
	}

}
