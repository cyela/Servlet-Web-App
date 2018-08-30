package com.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.User;
import com.dao.UserDAO;
import com.business.Address;
import com.business.EmailUtil;
import com.business.PasswordUtil;
import com.business.Product;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println("action: "+action);
		HttpSession session=request.getSession(false); 
		User user=(User) session.getAttribute("User");
		UserDAO udao=new UserDAO();
		ArrayList<Product> prodList=udao.getProduct();
		
		if (action == null) {
            action = "join";  // default action
        }
	    String url="/index.jsp";
	    if(user==null){
	    	if(action.equals("home")){
	    		url="/index.jsp";
	    	}
	    	else if(action.equals("contact")){
	    		
	    		String msg=request.getParameter("subject");
	    		String name=request.getParameter("firstname");
	    		String email=request.getParameter("email");
	    		EmailUtil.sendEmail( msg, email);
	    		
	    		request.setAttribute("user", user);
            	request.setAttribute("product", prodList);
	    		request.setAttribute("contact", "contact");
               	url="/index.jsp";
			 }
	    }
	    else if(user!=null){
	    	
	    	if(action.equals("home")){
	    		request.setAttribute("user", user);
            	request.setAttribute("product", prodList);
               	url="/userPage.jsp";
	    	}
	    	else if(action.equals("contact")){
	    		
	    		
	    		String msg=request.getParameter("subject");
	    		String name=request.getParameter("firstname");
	    		String email=request.getParameter("email");
	    		EmailUtil.sendEmail( msg, email);
	    		request.setAttribute("contact", "contact");
	    		request.setAttribute("user", user);
            	request.setAttribute("product", prodList);
               	url="/userPage.jsp";
			 }
	    }
	    getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		 String action = request.getParameter("action");
		 if (action == null) {
	            action = "join";  // default action
	        }
		 String url="/index.jsp";
		 if (action.equals("join")) {
	            url = "/index.jsp";    // the "join" page
	        }
		 else if (action.equals("Login")) 
		 {
			  
			 
			    String userName = request.getParameter("username");
	            String Password = request.getParameter("Password");
	            
	            UserDAO udao=new UserDAO();
	            if(udao.checkAdmin(userName, Password)!=null){
	            	
	            	
	            	//--- add update an delete product--------//
	            	User user=udao.checkAdmin(userName, Password);
	            
	            	ArrayList<Product> prodList=udao.getProduct();
	            	//--creating session for user
	                HttpSession session = request.getSession();
	            	session.setAttribute("User", user);
	            	session.setAttribute("product", prodList);
	            	
	            	request.setAttribute("product", prodList);
	            	request.setAttribute("user", user);
	            	request.setAttribute("Login", "success");
	            	url="/adminPage.jsp";
	            	
	            } else
					try {
						if(udao.checkUser(userName, Password)!=null)
						{
							
							//---retrieve products from data base and display them
							
							ArrayList<Product> prodList=udao.getProduct();
							User user=udao.checkUser(userName, Password);
							Address adrs=udao.getAddress(userName);
						
							// creating session for admin
							HttpSession session = request.getSession();
							session.setAttribute("User", user);   
							session.setAttribute("product", prodList);
							session.setAttribute("address", adrs);
							
							request.setAttribute("user", user);
							request.setAttribute("product", prodList);
							request.setAttribute("address", adrs);
							request.setAttribute("Login", "success");
							url="/userPage.jsp";
							
						}else
						{
							
							request.setAttribute("error", "Incorrect User Name or Password");
							request.setAttribute("Login", "fail");
							url = "/index.jsp";
						}
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            
			 
	        }
		 else if(action.equals("SignIn"))
		 {
	        	
	        	url="/signup.jsp";
	     }
		 else if(action.equals("return")){
			 HttpSession session=request.getSession(false); 
		     User user=(User) session.getAttribute("User");
				
			 request.setAttribute("user", user);
				
				url="/userPage.jsp";
		 }
		 
		 getServletContext()
         .getRequestDispatcher(url)
         .forward(request, response);
	}

}
