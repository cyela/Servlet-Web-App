package com.business;

import java.io.Serializable;
import java.sql.Blob;

public class Product implements Serializable {
	private String productId;
	private String description;
	private String productname;
	private String productprice;
	private String quantity;
	private Blob prodImage;

	public Blob getProdImage() {
		return prodImage;
	}

	public void setProdImage(java.sql.Blob blob) {
		this.prodImage = blob;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductprice() {
		return productprice;
	}

	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", productname=" + productname
				+ ", productprice=" + productprice + ", quantity=" + quantity + ", prodImage=" + prodImage + "]";
	}

	public Product(String productId, String description, String productname, String productprice, String quantity,
			Blob prodImage) {
		super();
		this.productId = productId;
		this.description = description;
		this.productname = productname;
		this.productprice = productprice;
		this.quantity = quantity;
		this.prodImage = prodImage;
	}

	public Product() {
		super();
	}

}
