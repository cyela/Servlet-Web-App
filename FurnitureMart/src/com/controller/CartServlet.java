package com.controller;

import java.awt.List;
import java.io.IOException;
import java.net.HttpCookie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.Address;
import com.business.BufCart;
import com.business.Cart;
import com.business.OrderPlaced;
import com.business.Product;
import com.business.User;
import com.dao.CartDAO;
import com.dao.UserDAO;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		String productId=request.getParameter("productId");
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("User");
		UserDAO udao=new UserDAO();
		ArrayList<Product> prodList=udao.getProduct();
		String url="/userPage.jsp";
		System.out.println("came"+action);
		//-- adding product id into bufercart table
		if(action.equals("cart_item")){
			System.out.println("came inside");
			BufCart bcart=new BufCart();
			bcart.setProductId(productId);
			bcart.setEmailId(user.getEmail());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			bcart.setDateAdded(dateFormat.format(date));
			bcart.setQuantity("1");
			
			for(int i=0;i<prodList.size();i++){
				if(prodList.get(i).getProductId().equals(productId)){
					bcart.setPrice(prodList.get(i).getProductprice());
					bcart.setProductName(prodList.get(i).getProductname());
				}
			}
			CartDAO cdao=new CartDAO();
			
			try {
				
				int check=0;
				ArrayList<BufCart> bufList=cdao.getBufItems();
				if(bufList!=null)
				{
					for(int i=0;i<bufList.size();i++){
						if(bufList.get(i).getEmailId().equals(user.getEmail()) && bufList.get(i).getProductId().equals(productId)){
							check++;
							
						}
					}
					if(check!=0)
					{
						//-already added
						request.setAttribute("cart_item", "exists");
						request.setAttribute("user", user);
			        	request.setAttribute("product", prodList);
						
						url="/userPage.jsp";
					}else{
						cdao.addBufCart(bcart);
						request.setAttribute("user", user);
			        	request.setAttribute("product", prodList);
						request.setAttribute("cart_item", "added");
						url="/userPage.jsp";
					}
				}else{
					cdao.addBufCart(bcart);
					request.setAttribute("user", user);
		        	request.setAttribute("product", prodList);
					request.setAttribute("cart_item", "added");
					url="/userPage.jsp";
					
				}
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(action.equals("cart_remove")){
			String BufId=request.getParameter("productId");
			CartDAO cdao=new CartDAO();
			ArrayList<BufCart> bufList=new ArrayList<BufCart>();
			try {
				cdao.remBuf(BufId,user.getEmail());
				bufList=cdao.getBufItemsList(user.getEmail());
				if(bufList!=null){
					float sum=0;
					for(int i=0;i<bufList.size();i++){
						sum=sum+(Float.valueOf(bufList.get(i).getPrice())*Float.valueOf(bufList.get(i).getQuantity()));
					}
					
					request.setAttribute("Tot", sum);
					request.setAttribute("bufitems", bufList);
					request.setAttribute("user", user);
				    url="/cartitems.jsp";
				}else{
					request.setAttribute("user", user);
		        	request.setAttribute("product", prodList);
		        	request.setAttribute("cart", "empty");
		           	url="/userPage.jsp";
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(action.equals("cart_update")){
			System.out.println("came in update");
			String BufId=request.getParameter("productId");
			String quantity=request.getParameter("quantity");
			CartDAO cdao=new CartDAO();
			ArrayList<BufCart> bufList=new ArrayList<BufCart>();
			try {
				cdao.upBuf(quantity, BufId);
				bufList=cdao.getBufItemsList(user.getEmail());
				float sum=0;
				for(int i=0;i<bufList.size();i++){
					sum=sum+(Float.valueOf(bufList.get(i).getPrice())*Float.valueOf(bufList.get(i).getQuantity()));
				}
				
				request.setAttribute("Tot", sum);
				request.setAttribute("bufitems", bufList);
				request.setAttribute("user", user);
			    url="/cartitems.jsp";
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(action.equals("view_cart")){
			
			CartDAO cdao=new CartDAO();
			System.out.println("cameinside");
			ArrayList<BufCart> bufList=new ArrayList<BufCart>();
			try {
				System.out.println("userid"+user.getEmail());
				bufList=cdao.getBufItemsList(user.getEmail());
				
				if(bufList==null){
					request.setAttribute("user", user);
		        	request.setAttribute("product", prodList);
		        	request.setAttribute("cart", "empty");
		           	url="/userPage.jsp";
					
					
				}else
				{
					float sum=0;
					for(int i=0;i<bufList.size();i++){
						sum=sum+(Float.valueOf(bufList.get(i).getPrice())*Float.valueOf(bufList.get(i).getQuantity()));
					}
					
					request.setAttribute("Tot", sum);
					request.setAttribute("bufitems", bufList);
					request.setAttribute("user", user);
				    url="/cartitems.jsp";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(action.equals("place")){
			CartDAO cdao=new CartDAO();
			
			
			ArrayList<BufCart> bufList=new ArrayList<BufCart>();
			try {
				bufList=cdao.getBufItemsList(user.getEmail());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<bufList.size();i++){
				Cart cart=new Cart();
				OrderPlaced order=new OrderPlaced();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				//-- order details
				order.setEmail(user.getEmail());
				order.setOrderDate(dateFormat.format(date));
				order.setOrderId(Integer.toString(generateRandomNumber(111,999)));
				order.setOrderStatus("pending");
				order.setTotalCost(Float.toString( Float.parseFloat(bufList.get(i).getPrice())* Float.parseFloat(bufList.get(i).getQuantity())));
				//--cart details
				 cart.setProductId(bufList.get(i).getProductId());
				 cart.setOrderId(order.getOrderId());
				 cart.setPrice(bufList.get(i).getPrice());
				 cart.setQuantity(bufList.get(i).getQuantity());
				 cart.setDateAdded(dateFormat.format(date));
				 
				 try {
					cdao.addOrder(order);
					cdao.addCart(cart);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			
			for(int i=0;i<bufList.size();i++){
				try {
					cdao.remBuf(bufList.get(i).getProductId(), user.getEmail());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("user", user);
        	request.setAttribute("product", prodList);
        	request.setAttribute("cart", "placed");
           	url="/userPage.jsp";
			
		}
	    
		getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		
	}
	//----unique id for cart 
	public int generateRandomNumber(int start, int end ){
	    Random random = new Random();
	    long fraction = (long) ((end - start + 1 ) * random.nextDouble());
	    return ((int)(fraction + start));
	}
}
