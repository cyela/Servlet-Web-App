package com.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.business.Address;
import com.business.User;
import com.dao.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
@MultipartConfig
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		String url = "/userPage.jsp";
		UserDAO udao = new UserDAO();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("User");
		if (action.equals("address")) {
			String email = request.getParameter("email");
			request.setAttribute("email", email);

			url = "/address.jsp"; // the "join" page
		} else if (action.equals("addresschange")) {
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipcode = request.getParameter("zipcode");
			// ---storing address values in Address object
			Address adrs = new Address();
			adrs.setAddress(address);
			adrs.setCity(city);
			adrs.setState(state);
			adrs.setZipcode(zipcode);
			adrs.setEmail(email);
			// ---passing address object and saving into the database
			// delete existing address and then updating the address
			udao.delAddress(email);
			udao.updateAddress(adrs);
			Address newAdrs = udao.getAddress(email);

			request.setAttribute("address", newAdrs);
			request.setAttribute("user", user);

			url = "/userPage.jsp";

		} else if (action.equals("return")) {

			request.setAttribute("user", user);

			url = "/userPage.jsp";
		} else if (action.equals("picturechange")) {

			InputStream inputStream = null;
			Part filePart = request.getPart("photo");
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				// the InputStream will point to a stream that contains
				// the contents of the file
				inputStream = filePart.getInputStream();

				udao.updatePicture(user.getEmail(), inputStream);
				User userUp = udao.getUserInfo(user.getEmail());
				Address newAdrs = udao.getAddress(user.getEmail());

				request.setAttribute("address", newAdrs);
				request.setAttribute("user", userUp);

				url = "/userPage.jsp";
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}