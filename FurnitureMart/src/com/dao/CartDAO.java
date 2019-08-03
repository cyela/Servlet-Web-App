package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.business.BufCart;
import com.business.Cart;
import com.business.OrderPlaced;
import com.dbconfig.DbUtil;

public class CartDAO {

	// -- adding selected items in buffer
	public String addBufCart(BufCart bufCart) throws ParseException {

		String productId = bufCart.getProductId();
		String email = bufCart.getEmailId();
		String dateAdd = bufCart.getDateAdded();
		String quantity = bufCart.getQuantity();
		String price = bufCart.getPrice();
		String name = bufCart.getProductName();

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date parsed = format.parse(dateAdd);
		java.sql.Date sql = new java.sql.Date(parsed.getTime());

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbUtil.getConnection();
			String query = "insert into BufCArt(productId,email,dateAdded,quantity,price,productname) VALUES(?,?,?,?,?,?)";

			ps = connection.prepareStatement(query);

			ps.setString(1, productId);
			ps.setString(2, email);
			ps.setDate(3, sql);
			ps.setString(4, quantity);
			ps.setString(5, price);
			ps.setString(6, name);

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("details inserted successfully");
				return "success";

			} else {
				System.out.println("not successfull");

			}
			connection.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;

	}

	// -- removing selected items in buffer
	public String remBuf(String productid, String email) throws ParseException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbUtil.getConnection();
			String query = "delete from BufCArt where productId=? and email=?";

			ps = connection.prepareStatement(query);
			ps.setString(1, productid);
			ps.setString(2, email);
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("details inserted successfully");
				return "success";

			} else {
				System.out.println("not successfull");

			}
			connection.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;

	}

	// -- updating quantity
	public String upBuf(String quantity, String productId) throws ParseException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbUtil.getConnection();
			String query = "UPDATE BufCArt  SET quantity = ? WHERE productID=?";

			ps = connection.prepareStatement(query);
			ps.setString(1, quantity);
			ps.setString(2, productId);
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("details inserted successfully");
				return "success";

			} else {
				System.out.println("not successfull");

			}
			connection.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;

	}

	// -- fetching items in buffer
	public ArrayList<BufCart> getBufItems() throws ParseException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = DbUtil.getConnection();
			String query = "Select * from BufCArt ";

			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			ArrayList<BufCart> prodId = new ArrayList<>();

			while (rs.next()) {
				BufCart buf = new BufCart();
				buf.setEmailId(rs.getString("email"));
				buf.setProductId(rs.getString("productId"));
				prodId.add(buf);

			}
			if (!prodId.isEmpty()) {
				return prodId;
			}
			connection.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;

	}

	// -- fetching items in buffer for the selected user
	public ArrayList<BufCart> getBufItemsList(String emailId) throws ParseException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = DbUtil.getConnection();
			String query = "select * from BufCArt where email=?";

			ps = connection.prepareStatement(query);
			ps.setString(1, emailId);
			rs = ps.executeQuery();
			ArrayList<BufCart> prodId = new ArrayList<>();

			while (rs.next()) {
				BufCart bcart = new BufCart();
				bcart.setProductId(rs.getString("productId"));
				bcart.setEmailId(rs.getString("email"));
				bcart.setProductName(rs.getString("productname"));
				bcart.setQuantity(rs.getString("quantity"));
				bcart.setPrice(rs.getString("price"));
				prodId.add(bcart);

			}
			if (!prodId.isEmpty()) {
				return prodId;
			}
			connection.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return null;

	}

	// -- adding selected items in order
	public String addOrder(OrderPlaced order) throws ParseException {

		String orderId = order.getOrderId();
		String email = order.getEmail();
		String totalCost = order.getTotalCost();
		String orderDate = order.getOrderDate();
		String orderStatus = order.getOrderStatus();

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date parsed = format.parse(orderDate);
		java.sql.Date sql = new java.sql.Date(parsed.getTime());

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbUtil.getConnection();
			String query = "insert into OrderPlaced(orderId,email,totalCost,orderDate,orderStatus) VALUES(?,?,?,?,?)";

			ps = connection.prepareStatement(query);

			ps.setString(1, orderId);
			ps.setString(2, email);
			ps.setFloat(3, Float.parseFloat(totalCost));
			ps.setDate(4, sql);
			ps.setString(5, orderStatus);

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("details inserted successfully");
				return "success";

			} else {
				System.out.println("not successfull");

			}
			connection.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;

	}

	// -- adding selected items in cart
	public String addCart(Cart cart) throws ParseException {

		String productId = cart.getProductId();
		String quantity = cart.getQuantity();
		String dateAdded = cart.getDateAdded();
		String orderId = cart.getOrderId();
		String price = cart.getPrice();

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date parsed = format.parse(dateAdded);
		java.sql.Date sql = new java.sql.Date(parsed.getTime());

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DbUtil.getConnection();
			String query = "insert into CartItems(productId,quantity,dateAdded,orderId,price) VALUES(?,?,?,?,?)";

			ps = connection.prepareStatement(query);

			ps.setString(1, productId);
			ps.setInt(2, Integer.parseInt(quantity));
			ps.setDate(3, sql);
			ps.setString(4, orderId);
			ps.setString(5, price);

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("details inserted successfully");
				return "success";

			} else {
				System.out.println("not successfull");

			}
			connection.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}
}