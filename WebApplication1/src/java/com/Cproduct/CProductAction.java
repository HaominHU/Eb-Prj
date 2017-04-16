package com.Cproduct;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.*;
import javax.servlet.http.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.util.DividePage;
import com.util.UUIDTools;
import java.util.Iterator;

public class CProductAction extends HttpServlet {

	private CProductService service;
	/**
	 * Constructor of the object.
	 */
	public CProductAction() {
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
			addProduct(request,response);
		}else if (action_flag.equals("search")) {
			searchProduct(request,response);
		}else if (action_flag.equals("addtocart")) {
			addShopcart(request,response);
		}else if (action_flag.equals("view")) {
			viewProduct(request,response);
		}else if (action_flag.equals("rank")) {
			rankProduct(request,response);
		}else if (action_flag.equals("nosearch")) {
			novelSearch(request,response);
		}
                else if (action_flag.equals("edsearch")) {
			educationSearch(request,response);
		}
                else if (action_flag.equals("bisearch")) {
			literatureSearch(request,response);
		}
                else if(action_flag.equals("checkout")) {
                        checkOut(request,response);
                }
                else if(action_flag.equals("mytrade")){
                        viewMytrade(request,response);
                }
                else if(action_flag.equals("viewShoppingCart")){
                        viewShoppingCart(request,response);
                }


		
		
		out.flush();
		out.close();
	}

	private void viewProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		Map<String, Object> map = service.viewProduct(bookid);
                
		request.setAttribute("bookDetail", map);
		try {
			request.getRequestDispatcher("/viewProduct.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
        
       
        
        private void addToCart(HttpServletRequest request,
			HttpServletResponse response) {
                
                
		String[] ids = request.getParameterValues("ids");
                
//                for(int i=0;i<ids.length;i++){
//                System.out.println(i +   "s" +ids[i]);
//                }
		List<Map<String,Object>> map = service.addToCart(ids);

		//request.setAttribute("bookCart", map);
                for(Map<String,Object> map1:map){
                Object bookid=map1.get("bookid");
                Object bookname=map1.get("bookname");
            
                List<Object> params=new ArrayList<Object>();
                params.add(bookid);
                params.add(bookname);
                }
		try {
			//request.getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
                        viewShoppingCart(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
        
        private void addShopcart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
		String path = request.getContextPath();
		boolean flag1 = false ;
		//List<Object> params1 = new ArrayList<Object>();
		//String key = UUIDTools.getUUID();
                String username=request.getParameter("username");
                //String[] bookname=request.getParameterValues("ids");
		String[] ids = request.getParameterValues("ids");
		try{

			for(int i = 0; i<ids.length; i++){
				List<Object> params1 = new ArrayList<Object>();
				//params1.add(key);
                                params1.add(ids[i]);
				params1.add(username);
				flag1 = service.addShopcart(params1);
            }
			
			if(flag1){
				//response.sendRedirect(path+"/shoppingcart.jsp");
				//request.getRequestDispatcher("/shoppingcart.jsp").forward(request,response);
				addToCart(request, response);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

        
        private void checkOut(HttpServletRequest request,
			HttpServletResponse response) {
                
                
		String[] ids = request.getParameterValues("ids");
                
//                for(int i=0;i<ids.length;i++){
//                System.out.println(i +   "s" +ids[i]);
//                }
		List<Map<String,Object>> map = service.checkOut(ids);

		request.setAttribute("checkOut", map);
		try {
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
                        
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	private void searchProduct(HttpServletRequest request,
			HttpServletResponse response) {
		
		String bookName = request.getParameter("bookname");	
//		
		if (bookName == null) {
			bookName = "";
		}
		
		
		

		
		List<Map<String, Object>> list = null;
		try {
			list = service.listProduct(bookName);
			request.setAttribute("listBook", list);
			request.setAttribute("bookName",bookName );
			request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
		} catch (Exception e) {
			// exception
			e.printStackTrace();
		}		
		
	}

//	private void listProduct(HttpServletRequest request,
//			HttpServletResponse response) {
//		String productName = request.getParameter("proname");	
//	
//		
//		if (productName == null) {
//			productName = "";
//		}
//		
//		
//		
//		int totalRecord = service.getItemCount(productName); 
//		
//		
//		List<Map<String, Object>> list = null;
//		try {
//			list = service.listProduct(productName);
//			request.setAttribute("listProduct", list);
//			
//			request.setAttribute("productName",productName );
//			request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//		
//	}
        
        private void rankProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
            
                String bookName = request.getParameter("bookname");	
		
		if (bookName == null) {
			bookName = "";
		}
		
		
		
		
		
		
		List<Map<String, Object>> list = null;
		try {
			list = service.rankProduct(bookName);
			request.setAttribute("listBook", list);
			
			request.setAttribute("bookName",bookName );
			request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
            
        }

	private void addProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//submit file (later
		String  path = request.getContextPath();		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3*1024*1024);
		servletFileUpload.setSizeMax(6*1024*1024);
		List<FileItem> list = null;		
		List<Object> params = new ArrayList<Object>();
		params.add(UUIDTools.getUUID()); //key
		try {
			list = servletFileUpload.parseRequest(request);				
			
			for(FileItem fileItem : list){
				if (fileItem.isFormField()) {//text
					String fileItemName = fileItem.getFieldName(); 
					String fileItemValue = fileItem.getString("utf-8");//<input>
					if (fileItemName.equals("proname")) {
						params.add(fileItemValue); // proname
					}else if (fileItemName.equals("proprice")) {
						params.add(fileItemValue);// proprice
					}else if (fileItemName.equals("proaddress")) {
						params.add(fileItemValue);// proaddress
					}					
				}/*else{ //非文本字段					
					
					String imageName = fileItem.getName(); //
					params.add(imageName);//  proimage			
					//String path = request.getRealPath("/upload");
					String upload_dir = request.getServletContext().getRealPath("/upload");
					File uploadFile = new File(upload_dir+"/"+imageName);
					System.out.println("---upload_dir--->>"+uploadFile);
					fileItem.write(uploadFile);						
				}*/				
			}
			
			// add to db
			boolean flag = service.addProduct(params);
			if (flag) {
				
				response.sendRedirect(path+"/main.jsp");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
        
        private void novelSearch(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bookType = "novel";	
		
		if (bookType == null) {
			bookType = "";
		}
		
		
		
		
		
		
		List<Map<String, Object>> list = null;
		try {
			list = service.kindSearch(bookType);
			request.setAttribute("listBook", list);
			
			
			request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}
         private void educationSearch(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String productName = "education";	
		
		if (productName == null) {
			productName = "";
		}
		
		List<Map<String, Object>> list = null;
		try {
			list = service.kindSearch(productName);
			request.setAttribute("listBook", list);
			
			
			request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}
        private void literatureSearch(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bookkind = "Literature";	
		
		
		if (bookkind == null) {
			bookkind = "";
		}

		List<Map<String, Object>> list = null;
		try {
			list = service.kindSearch(bookkind);
			request.setAttribute("listBook", list);
			
			
			request.getRequestDispatcher("/Cmain.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}
        
        public void viewMytrade(HttpServletRequest request,
			HttpServletResponse response){
            
            List<Map<String, Object>> list = null;
                List<Object> params = new ArrayList<Object>();
                String bookseller = request.getParameter("username");
//                String bookseller="xinhua";
                params.add(bookseller);
                list = service.viewMytrade(params);
//                request.setAttribute("listOrder",list);
                
                try{
                        request.setAttribute("listTrade", list);
						request.getRequestDispatcher("/main.jsp").forward(request, response);
                } catch(Exception e){
                    e.printStackTrace();
                }
        }

	public void viewShoppingCart(HttpServletRequest request,
			HttpServletResponse response){
            
            List<Map<String, Object>> list = null;
                List<Object> params = new ArrayList<Object>();
                String bookseller = request.getParameter("username");
                
                params.add(bookseller);
                list = service.viewShoppingCart(params);

                List<Object> params1 = new ArrayList<Object>();
                String[] a=new String[list.size()];
                int i=0;
                for(Map<String,Object> map:list){
                    Object username=map.get("scid");
                    String username1=username.toString();
                    a[i]=username1;
                    i++;
                }
                List<Map<String,Object>> map= new ArrayList<Map<String,Object>>();
                map=service.checkOut(a);
                
                try{
                        request.setAttribute("bookCart", map);
			request.getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
                } catch(Exception e){
                    e.printStackTrace();
                }
        }
        
        
	public void init() throws ServletException {
		// Put your code here
		service = new CProductDao();
	}

}
