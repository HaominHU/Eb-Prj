
package com.Order;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;



import com.util.UUIDTools;

public class OrderAction extends HttpServlet{
        private OrderService service;
        
        public OrderAction() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
                
		
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addOrder(request,response);
		}
//		else if (action_flag.equals("search")) {
//			listOrder(request,response);
//		}
                else if (action_flag.equals("view")) {
			viewOrder(request,response);
		}               
                
		out.flush();
		out.close();
	}
        
        
                
        

	private void viewOrder(HttpServletRequest request,
			HttpServletResponse response) {

                List<Map<String, Object>> list = null;
                List<Object> params = new ArrayList<Object>();
                String oid = request.getParameter("oid");
                params.add(oid);
                list = service.viewOrder(params);
                request.setAttribute("listOrder",list);
                
                try{
                        request.setAttribute("listOrder", list);
                        request.getRequestDispatcher("/viewOrder.jsp").forward(request, response);
                } catch(Exception e){
                    e.printStackTrace();
                }
                		
	}

//	private void listOrder(HttpServletRequest request,
//			HttpServletResponse response) {
//		
//		String ordName = request.getParameter("username");	
//		
//		if (ordName == null) {
//			ordName = "";
//		}
//		
//		List<Map<String, Object>> list = null;
//		try {
//                        int a=1;
//			list = service.listOrder(ordName);
//			request.setAttribute("listOrder", list);
//			
//			request.setAttribute("ordName",ordName );
//			request.getRequestDispatcher("/Cmain.jsp").forward(request, response); 
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}		
//		
//	}


	private void addOrder(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		String  path = request.getContextPath();		
//		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//		servletFileUpload.setFileSizeMax(3*1024*1024);
//		servletFileUpload.setSizeMax(6*1024*1024);
//		List<FileItem> list = null;		
		List<Object> params1 = new ArrayList<Object>();
                
                String sum=request.getParameter("sum");
                String username=request.getParameter("username");
                
                String[] bookname=request.getParameterValues("bookname");
                String[] bookquantity=request.getParameterValues("bookquantity");
                
                                
		String key = UUIDTools.getUUID();
                params1.add(key); 
                params1.add(username);
                params1.add(sum);
		try {
		
                        for(int i = 0; i<bookname.length; i++){
                            List<Object> params = new ArrayList<Object>();
                            params.add(key);
                            params.add(bookname[i]);
                            params.add(bookquantity[i]);
                            boolean flag = service.addDetail(params);
                        }
			
			
			boolean flag1 = service.addOrder(params1);
			if (flag1) {
				
                            delSell(request,response);
				
			}
				
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        }
        
        private void delSell(HttpServletRequest request, HttpServletResponse response){
				String[] ids = request.getParameterValues("bookid");
				
				boolean flag = service.delSell(ids);
				if (flag) {
					try {
						request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
						
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
        
        private void addDetail(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException{
		
		String  path = request.getContextPath();		
//		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//		servletFileUpload.setFileSizeMax(3*1024*1024);
//		servletFileUpload.setSizeMax(6*1024*1024);
//		List<FileItem> list = null;		
		List<Object> params = new ArrayList<Object>();
		
                
                String bookname=request.getParameter("bookname");
                String bookquantity=request.getParameter("bookquantity");
                
                
                params.add(bookname);
                params.add(bookquantity);
		try {
			
			boolean flag = service.addDetail(params);
			if (flag) {
				
				response.sendRedirect(path+"/Cmain.jsp");
			}
				
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        }

	public void init() throws ServletException {
		
		service = new OrderDao();
	}

}
