package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RegisterAction extends HttpServlet {	
	
	private RegisterService service;
	
	public RegisterAction() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		
		String username = request.getParameter("username");
		String pswd = request.getParameter("pswd");
                String name = request.getParameter("name");
                String email= request.getParameter("email");
                String phone= request.getParameter("phone");
		String street= request.getParameter("street");
                String city= request.getParameter("city");
                String state= request.getParameter("state");
                String zip= request.getParameter("zip");
                
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(pswd); 	
                params.add(name);
                params.add(email);
                params.add(phone);
                params.add(street);
                params.add(city);
                params.add(state);
                params.add(zip);
		boolean flag = service.registerUser(params);
		if (flag) {
			response.sendRedirect(path+"/index.jsp");
		}
		
		
		
		out.flush();
		out.close();
	}

	
	public void init() throws ServletException {
		
		service = new RegisterDao();
	}

}