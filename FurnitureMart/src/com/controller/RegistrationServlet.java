package com.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.business.PasswordUtil;
import com.business.User;
import com.dao.UserDAO;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String cancel=request.getParameter("cancel");
		String url="/signup.jsp";
		
		if(action.equals("sign")){
			String firstName=request.getParameter("firstname");
			String lastName=request.getParameter("lastname");
			String email=request.getParameter("email");
			String gender=request.getParameter("gender");
			String dateOfBirth=request.getParameter("bday");
			String password=request.getParameter("Password");
			String repassword=request.getParameter("rePassword");
			String phonenumber=request.getParameter("phonenumber");
			
			User user=new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPhonenumber(phonenumber);
			user.setDOB(dateOfBirth);
			user.setGender(gender);
			
			if(password.equals(repassword)){
				//-- password hashing
				PasswordUtil putil=new PasswordUtil();
				String salt=putil.getSalt();
				try {
					String newPass=putil.hashAndSaltPassword(password, salt);
					System.out.println(newPass);
					user.setPassword(newPass);
					user.setSalt(salt);
					System.out.println("user"+user.toString());
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			UserDAO udao=new UserDAO();
			
			String res=udao.registerUser(user);
			if(res!=null){
				request.setAttribute("SignSuccess", "SignSuccess");
				 url="/index.jsp";
			}
			
			
		}
		else if(action.equals("return")){
			 url="/index.jsp";
		 }
	        
		getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
		
	}

}
