package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    try {
	       
	        Connection conn = getConnection();
	        if(request.getParameter("productId")!=null){
		        PreparedStatement stmt = conn.prepareStatement("select productImage from Product where productId=?");
		        stmt.setLong(1, Long.valueOf(request.getParameter("productId")));
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            response.getOutputStream().write(rs.getBytes("productImage"));
		        }
	        }else if(request.getParameter("email")!=null){
	        	System.out.println("Came in image");
	        	System.out.println(request.getParameter("email"));
	        	PreparedStatement stmt = conn.prepareStatement("select image from User where email=?");
		        stmt.setString(1, request.getParameter("email"));
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            response.getOutputStream().write(rs.getBytes("image"));
		        }
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static Connection getConnection() throws ClassNotFoundException,SQLException 
	{
		 Class.forName("com.mysql.jdbc.Driver");
		 String dbName = "muscle";
		 String userName = "root";
		 String password = "Telugu@1";
		 String hostname = "localhost";
		 String port = "3306";
		 String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName +
				 "?user=" + userName + "&password=" + password;
				  return DriverManager.getConnection(jdbcUrl);
			 
	}
}
