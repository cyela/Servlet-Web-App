package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.Product;
import com.business.User;
import com.dao.UserDAO;
import javax.servlet.http.Part;

@WebServlet("/ProductServlet")
@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String action_productId = request.getParameter("productId");
		String url="/adminPage.jsp";
		
		
		 HttpSession session=request.getSession(false); 
		
		
			User user=(User) session.getAttribute("User");
			
			if(action.equals("add")){
				InputStream inputStream = null; 
				String description=request.getParameter("desc");
				String productname=request.getParameter("name");
				String productprice=request.getParameter("price");
				String quantity=request.getParameter("quantity");
				 Part filePart = request.getPart("photo");
			        if (filePart != null) {
			            // prints out some information for debugging
			            System.out.println(filePart.getName());
			            System.out.println(filePart.getSize());
			            System.out.println(filePart.getContentType());

			            //obtains input stream of the upload file
			            //the InputStream will point to a stream that contains
			            //the contents of the file
			            inputStream = filePart.getInputStream();
			        }		
				
				
				
				Product product=new Product();
				int productId = ThreadLocalRandom.current().nextInt(10000, 99999);
				product.setProductId(Integer.toString(productId));
				product.setDescription(description);
				product.setProductname(productname);
				product.setProductprice(productprice);
				product.setQuantity(quantity);
				 
				
				UserDAO udao=new UserDAO();
				udao.addProduct(product,inputStream);
				ArrayList<Product> prodList=udao.getProduct();
				
	        	request.setAttribute("user", user);
	        	request.setAttribute("product", prodList);
	        	request.setAttribute("function", "add");
	        	
	        	url="/adminPage.jsp";
				
			}
			else if(action.equals("edit")){
				String productId=request.getParameter("productId");
				System.out.println("productId"+productId);
				Product editProd=new Product();
				UserDAO udao=new UserDAO();
				ArrayList<Product> prodList=udao.getProduct();
				for(int i=0;i<prodList.size();i++){
					if(prodList.get(i).getProductId().equals(productId)){
						System.out.println("edited"+prodList.get(i).getProductId());
						editProd=prodList.get(i);
					}
				}
				if(editProd!=null){
					request.setAttribute("user", user);
		        	request.setAttribute("editprod", editProd);
		        	url="/editItem.jsp";
				}
				
			}else if(action.equals("edit_item")){
				System.out.println("came");
				String productId=request.getParameter("productId");
				String description=request.getParameter("desc");
				String productname=request.getParameter("name");
				String productprice=request.getParameter("price");
				String quantity=request.getParameter("quantity");
				
				Product product=new Product();
				product.setProductId(productId);
				product.setDescription(description);
				product.setProductname(productname);
				product.setProductprice(productprice);
				product.setQuantity(quantity);
				 
				System.out.println("quantitynew"+product.getQuantity());
				UserDAO udao=new UserDAO();
				udao.editProduct(product);
				ArrayList<Product> prodList=udao.getProduct();
				request.setAttribute("user", user);
	        	request.setAttribute("product", prodList);
	        	request.setAttribute("function", "edit");
	        	url="/adminPage.jsp";
				
			}else if(action.equals("remove")){
				String productId=request.getParameter("productId");
				
				UserDAO udao=new UserDAO();
				udao.removeProduct(productId);
				ArrayList<Product> prodList=udao.getProduct();
				request.setAttribute("user", user);
	        	request.setAttribute("product", prodList);
	        	request.setAttribute("function", "remove");
	        	url="/adminPage.jsp";
				
			}
			else if(action.equals("return")){
				UserDAO udao=new UserDAO();
				ArrayList<Product> prodList=udao.getProduct();
				request.setAttribute("user", user);
	        	request.setAttribute("product", prodList);
				 url="/adminPage.jsp";
			 }

			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        response.setDateHeader("Expires", 0);
			
		
		
		
		
		getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
		
		
		
	}

}
