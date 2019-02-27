package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet {

	UserService userService = new UserService();

	public void init() {
		HibernateUtil.configure();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Get a Bear from what was sent to me
		
		ObjectMapper om = new ObjectMapper();
		User user = om.readValue(request.getReader(), User.class);
		userService.create(user);
		response.setStatus(201);
		om.writeValue(response.getWriter(), user);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Check if a breed query parameter is set
		response.setHeader("Content-Type", "application/json");
		ObjectMapper om = new ObjectMapper().registerModule(new Hibernate5Module());
		
	//	String breed = request.getParameter("breed");
		//String weightOver = request.getParameter("weightOver");
		//String yellow = request.getParameter("yellow");
	/*	if (breed != null) {
			// if the breed parameter was set, find bears by breed
			//List<User> users = userService.getUsersByMessage(user);
		//	om.writeValue(response.getWriter(), users);
		} else if (weightOver != null) {
		//	List<Bear> bears = bearService.getBearsHeavierThan(Double.parseDouble(weightOver));
			//om.writeValue(response.getWriter(), bears);
		} else if (yellow != null) {
		//	om.writeValue(response.getWriter(), bearService.getYellowBears());
		} else {
			// otherwise, find bears by an id
			String[] arr = request.getRequestURI().split("/");
			int id = Integer.parseInt(arr[arr.length - 1]);
		//	Bear bear = bearService.getBear(id);
		//	om.writeValue(response.getWriter(), bear); */
		//}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		User users = om.readValue(req.getReader(), User.class);
		User other = userService.update(users);
		om.writeValue(resp.getWriter(), other);
	}
}
