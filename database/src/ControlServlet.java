import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private quotesDAO quotesDAO = new quotesDAO();
	    private clientDAO clientDAO = new clientDAO();
	    private contractorDAO contractorDAO = new contractorDAO();
	    private requestDAO requestDAO = new requestDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet(){}
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	 case "/request":
        		 System.out.println("The action is: request");
        		 request(request, response);
        		 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listQuotes(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<quotes> listQuotes = quotesDAO.listQuotes();
	        request.setAttribute("listQuotes", listQuotes);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listRequest(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listRequest started: 00000000000000000000000000000000000");

	     
	        List<request> listRequest = requestDAO.listAllRequests();
	        request.setAttribute("listRequest", listRequest);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("RequestList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listRequest finished: 111111111111111111111111111111111111");
	    }
	    	   
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    private void contractorPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("contractor view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	    }
	    
	    private void clientPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("client view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("clientactivitypage.jsp").forward(request, response);
	    }
	   
	   protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if(email.equals("david@gmail.com")) {
	    		 contractor contractor = contractorDAO.getContractor(email);
	    		 if (contractor != null && contractor.getPassword().equals(password)) {
		    	        // Valid user, set session attributes
		    	        session = request.getSession();
		    	        session.setAttribute("username", email);
		    	        System.out.println("Login Successful! Redirecting to contractor page");
		    	        contractorPage(request, response, "");}
		    	 }
	    	 else{
		    	        request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
		    	        request.getRequestDispatcher("login.jsp").forward(request, response);
		    	 }
	    	 
	    	 /*}else{
	    	 
	    	 client client= clientDAO.getClient(email);
	    
	    	 
	    	 if (client != null && client.getPassword().equals(password)) {
	    	        // Valid user, set session attributes
	    	        session = request.getSession();
	    	        session.setAttribute("username", email);

	    	        // Redirect based on the user's role
	    	        if ("root".equals(email)) {
	    	            System.out.println("Login Successful! Redirecting to root");
	    	            rootPage(request, response, "");
	    	        } else if ("david@gmail.com".equals(email)) {
	    	            System.out.println("Login Successful! Redirecting to contractor page");
	    	            contractorPage(request, response, "");
	    	        } else {
	    	            System.out.println("Login Successful! Redirecting to client page");
	    	            clientPage(request, response, "");
	    	        }
	    	    } else {
	    	        request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
	    	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    	    }
	    	 }
	    	 */
	    	}
	          
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String role = request.getParameter("role");
	   	 	String address = request.getParameter("address");
	   	 	String creditcard = request.getParameter("creditcard");
	   	 	String phone = request.getParameter("phone");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if(role.equals("client")) {
	   	 		if (password.equals(confirm)) {
	   	 			if (!clientDAO.checkClientEmail(email)) {
	   	 				System.out.println("Registration Successful! Added to database");
	   	 				client clients = new client(email,firstName, lastName, password, address, creditcard, phone);
	   	 				clientDAO.insertClient(clients);
	   	 				response.sendRedirect("login.jsp"); 
	   	 			}
	   	 			else {
	   	 				System.out.println("Username taken, please enter new username");
		   	 			request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		   	 			request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 			}
	   	 		}
	   	 		else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   	 		request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   	 		request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 		} 
	   	 	}else if(role.equals("contractor")) {
	   	 		if (password.equals(confirm)) {
	   	 			if (!contractorDAO.checkContractorEmail(email)) {
	   	 				System.out.println("Registration Successful! Added to database");
	   	 				contractor contractors = new contractor(email,firstName, lastName, password) ;
	   	 				contractorDAO.insertContractor(contractors);
	   	 				response.sendRedirect("login.jsp"); 
	   	 			}
	   	 			else {
	   	 				System.out.println("Username taken, please enter new username");
		   	 			request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		   	 			request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 			}
	   	 		}
	   	 		else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   	 		request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   	 		request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 		} 
	   	 	}
	   	 	
	    }  
	    
	    private void request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
            String location = request.getParameter("location");
            String height = request.getParameter("height");
            String proximity = request.getParameter("proximity");
            String sizeDiameter = request.getParameter("diameter");
        	String photodata1 = request.getParameter("Photo 1");
            String photodata2 = request.getParameter("Photo 2");
            String photodata3 = request.getParameter("Photo 3");
            String note = request.getParameter("note");
            
            request requests = new request(location, height, proximity, sizeDiameter, photodata1, photodata2, photodata3, note);
            requestDAO.insert(requests);
            response.sendRedirect("listRequest");
            //request.setAttribute("treeCount", treeCount);
            //request.getRequestDispatcher("clientquote.jsp").forward(request, response);
        
    }
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}    
}
