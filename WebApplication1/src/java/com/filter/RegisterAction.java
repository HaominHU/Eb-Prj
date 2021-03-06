package com.filter;

import com.register.RegisterDao;
import com.register.RegisterService;
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
		
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(pswd); 	
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