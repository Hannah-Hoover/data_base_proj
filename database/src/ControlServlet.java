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
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
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
	    
	    private void listRequest(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listRequest started: 00000000000000000000000000000000000");

	     
	        List<request> listRequest = userDAO.listAllRequests();
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
	    	 
	    	 user user= userDAO.getUser(email);
	    
	    	 
	    	 if (user != null && user.getPassword().equals(password)) {
	    	        // Valid user, set session attributes
	    	        session = request.getSession();
	    	        session.setAttribute("username", email);
	    	        session.setAttribute("role", user.getRole());

	    	        // Redirect based on the user's role
	    	        if ("root".equals(email) && "admin".equals(user.getRole())) {
	    	            System.out.println("Login Successful! Redirecting to root");
	    	            rootPage(request, response, "");
	    	        } else if ("david@gmail.com".equals(email) && "contractor".equals(user.getRole())) {
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
	          
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String birthday = request.getParameter("birthday");
	   	 	String role = request.getParameter("role");
	   	 	//role = (role != null && role.equals("")) ? role : "client";
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code"); 	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email,firstName, lastName, password, birthday, role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, 1000,0);
		   	 		userDAO.insert(users);
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
	    
	    private void request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int treeCount = Integer.parseInt(request.getParameter("treeCount"));
	        
	        // List to store error messages
	        List<String> errorMessages = new ArrayList<>();

	        for (int i = 1; i <= treeCount; i++) {
	            // Process each tree's data
	            String location = request.getParameter("location" + i);
	            String height = request.getParameter("height" + i);
	            String proximity = request.getParameter("proximity" + i);
	            String sizeDiameter = request.getParameter("diameter" + i);
	        	String photodata1 = request.getParameter("Photo 1");
	            String photodata2 = request.getParameter("Photo 2");
	            String photodata3 = request.getParameter("Photo 3");
	            String note = request.getParameter("note" + i);
	        
	            request.setAttribute("treeCount", treeCount);
	            request.getRequestDispatcher("clientquote.jsp").forward(request, response);
	        }
	    }
	       
	        

	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


