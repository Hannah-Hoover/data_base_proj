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
	    private quoteDAO quoteDAO = new quoteDAO();
	    private treeDAO treeDAO = new treeDAO();
	    private clientDAO clientDAO = new clientDAO();
	    private contractorDAO contractorDAO = new contractorDAO();
	    private requestDAO requestDAO = new requestDAO();
	    private QuotesMessagesDAO QuotesMessagesDAO = new QuotesMessagesDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    String sessionID;
	    
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
        		
        	case "/logout":
        		logout(request,response);
        		break;
        	
        	case "/register":
        		register(request, response);
        		break;
        		
        	case "/root":
        		rootPage(request,response, "");
        		break;	
        		
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        		
        	 case "/contractorpage":
        		 contractorPage(request, response, "");
        		 break;
        		 
        	 case "/clientpage":
        		 clientPage(request, response, "");
        		 break;
        		 
        	 case "/quote":
                 System.out.println("The action is: quote");
             	 Quote(request, response);
                 break;
                 
         	case "/listquote":
         		System.out.println("The action is: listQuote");
         		listQuote(request, response);
         		break;
         		
         	case "/listuserquote":
         		System.out.println("The action is: listQuote");
         		listUserQuotes(request, response);
         		break;
        		
         	case "/updatequote":
         		System.out.println("The action is: updateQuote");
         		updateQuote(request, response);
         		break;
         		
         	case "/newquote":
         		System.out.println("New Quote");
         		newQuote(request, response);
         		break;
         		
         		/*
         	 case "/updatequotestatus":
        		 System.out.println("Client quitting quote");
        		 UpdateQuoteStatus(request, response);
        		 break;
        		 
         	case "/updatequotecontractor":
         		System.out.println("Contractor quitting quote");
         		UpdateQuoteContractor(request, response);
         		break;
        	 *//*
        	 case "/clientupdate":
        		 System.out.println("The action is: clientupdate");
        		 updateQuote(request, response);
        		 break;
        	 case "/contractorupdate":
        		 System.out.println("The action is: contractorupdate");
        		 contractorUpdateQuotes(request, response);
        		 break;
        		 
        	 case "/clientresponse":
                 System.out.println("The action is: clientrepsonse");
             	 clientResponse(request, response);
                 break;
                 */
                 
        	 case "/contractorresponse":
                 System.out.println("The action is: contractorrepsonse");
             	 contractorResponse(request, response);
                 break;
                 
        	 case "/listUser": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	 case "/quoteMessage":
        		 System.out.println("The action is: quoteMessge");
                 quoteMessage(request, response);
                 
        	
         	case "/request":
         		System.out.println("The action is: request");
         		request(request, response);
         		break;
         		
       
	  
      
        	}}
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        
		   protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		    	 String email = request.getParameter("email");
		    	 String password = request.getParameter("password");
		    	 String role = request.getParameter("role");
		    	
		    	 
		    	 
		    	 if(email.equals("root") && password.equals("pass1234")){
		    		 if (role.equals("admin")) {
			    	        System.out.println("Login Successful! Redirecting to root");
			    	        session = request.getSession();
			    	        session.setAttribute("username",  email);
			    	        rootPage(request,response, "");
		    		 }
		    	 }
		    	 else if(userDAO.isValid(email,password)) {
		    		 if (role.equals("client")) {
		    			 currentUser = email;
		    			 user user = userDAO.getUser(email);
		    			 request.getSession().setAttribute("clientID", user.getUserID());
		    			 System.out.println("Login Successful! Redirecting");
		    			 request.getRequestDispatcher("clientactivitypage.jsp").forward(request, response);
		    		 }
		    		else if (role.equals("contractor")) {
			    		 currentUser = email;
			   			 System.out.println("Login Successful! Redirecting");
			   			 user user = userDAO.getUser(email);
		    			 request.getSession().setAttribute("clientID", user.getUserID());
			   			 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
					     }
			    	 }  		 
		    	 else {
		    	        request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
		    	        request.getRequestDispatcher("login.jsp").forward(request, response);
		    	 }
		    	 
		   }
		   
		   
		    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		    	currentUser = "";
	        		response.sendRedirect("login.jsp");
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
	   	 	
	   	 	
	   		if (password.equals(confirm)) {
   	 			if (!userDAO.checkEmail(email)) {
   	 				System.out.println("Registration Successful! Added to database");
   	 				user users = new user(email,password, firstName, lastName, role, address, creditcard, phone);
   	 				userDAO.insert(users);
   	 				response.sendRedirect("login.jsp"); 
   	 			}
   	 			else {
   	 				System.out.println("Username taken, please enter new username");
	   	 			request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
	   	 			request.getRequestDispatcher("register.jsp").forward(request, response);
   	 			}
   	 		}
	    }
	    
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void contractorPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("contractor view");
	    	request.setAttribute("listQuote", quoteDAO.listAllQuote());
	    	request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	    }
	    
	    private void clientPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("client view");
	    	int clientID = Integer.parseInt(request.getSession().getAttribute("clientID").toString());
			request.setAttribute("listQuote", quoteDAO.getQuotesByClientID(clientID));
	    	request.getRequestDispatcher("clientactivitypage.jsp").forward(request, response);
	    }
	    
	    
	    private void Quote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("in quote");
            String contractorID = request.getParameter("contractorID");
            String clientID = request.getParameter("clientID");
            String price = request.getParameter("price");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            String status = request.getParameter("status");
       
            
            session = request.getSession();

            
            quote quote = new quote(Integer.parseInt(contractorID), Integer.parseInt(clientID), Double.parseDouble(price), startTime, endTime, status);
            quoteDAO.insertQuote(quote);
            
            
        	System.out.println("Quote Successful! Added to database");
        	contractorPage(request, response, "");
        
    } 
	    private void listQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listQuote started: 00000000000000000000000000000000000");
	        
	        List<quote> listQuote = quoteDAO.listAllQuote();
	        request.setAttribute("listQuote", listQuote);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listQuote finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listUserQuotes(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUserQuote started: 00000000000000000000000000000000000");
	        
	        List<quote> listUserQuote = quoteDAO.listUserQuotes();
	        request.setAttribute("listUserQuotes", listUserQuote);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("clientactivitypage.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listUserQuote finished: 111111111111111111111111111111111111");
	    }
	    
	    /*
	    private void updateQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("updateQuote started: 000000000000000000000000000");
	     
	       // String status = request.getParameter("status");
	        int quoteID=Integer.parseInt(request.getParameter("quoteID"));
	      //System.out.println("name:" + name + ", address: "+address + ", status:" + status);
	        
	        quote quotes = quoteDAO.getQuote(quoteID);//new quote(contractorID, clientID, price, startTime, endTime, status);
	        System.out.print("after quote");
	        quoteDAO.update(quotes);
	        System.out.println("Ask the browser to call the list action next automatically");
	        contractorPage(request, response, "");
	        //clientPage(request, response, "");
	        //.out.println(price);
	     
	        System.out.println("updateQuotes finished: 1111111111111111111111111111111");
	    }
	    
	 */
	    /*
	    private void updateQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("updateQuotecontractor started: 000000000000000000000000000");
	       
	        // get the id of the people we want to delete from the parameter
	        int id = Integer.parseInt(request.getParameter("quoteID"));
	        String status = request.getParameter("status");
	        System.out.println(id);
	        System.out.println(status);
	        //People people = new People(id);
	        // delete this people
	        quote quotes = quoteDAO.getQuote(id);
	        quotes.setStatus(status);
	        quoteDAO.update(quotes);
	        System.out.println("Ask the browser to call the list action next automatically");
	        
	        // instead redirect to another jsp, we redirect to another action, simulating that the user click a list link or button, benefit: save one click from the user    
	        contractorPage(request, response, "");
	
	        
	        System.out.println("updateQuotecontractor finished: 1111111111111111111111111111111");
	    }*/
	    
	    private void newQuote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		  	int requestID=Integer.parseInt(request.getParameter("id"));
					request req = requestDAO.getRequest(requestID);
					request.setAttribute("req", req);
					RequestDispatcher dispatcher = request.getRequestDispatcher("contractorquote.jsp");       
			        dispatcher.forward(request, response);
					
				}
	    /*
	    // to present an update form to update an  existing Student
	    private void UpdateQuoteStatus(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("updateQuote started: 000000000000000000000000000");

	        // get the id of the people we want to delete from the parameter
	        int id = Integer.parseInt(request.getParameter("id"));
	        String status = request.getParameter("status");
	        //People people = new People(id);

	        // delete this people
	        quote quotes = quoteDAO.getQuote(id);
	        quotes.setStatus(status);
	        quoteDAO.update(quotes);
	        //quotesDAO.delete(id);
	        
	        System.out.println("Ask the browser to call the list action next automatically");
	        
	        // instead rediret to another jsp, we redirect to another action, simulating that the user click a list link or button, benefit: save one click from the user    
	        contractorPage(request, response, "");
	        //clientPage(request, response, "");
	        
	        System.out.println("deletePeople finished: 1111111111111111111111111111111");
	    } 
	    
	    */   
	    private void updateQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("contractorUpdateQuote started: 000000000000000000000000000");
	        
	        int quoteID=Integer.parseInt(request.getParameter("quoteID"));
	        //int contractorID=Integer.parseInt(request.getParameter("contractorID"));
	        //int clientID=Integer.parseInt(request.getParameter("clientID"));
	        double price = Double.parseDouble(request.getParameter("price"));
	        String startTime = request.getParameter("startTime");
	        String endTime = request.getParameter("endTime");
	        String status = request.getParameter("status");
	
	      //System.out.println("name:" + name + ", address: "+address + ", status:" + status);
	        
	        quote quote = quoteDAO.getQuote(quoteID);//new quote(contractorID, clientID, price, startTime, endTime, status);
	        quote.setPrice(price);
	        quote.setStartTime(startTime);
	        quote.setEndTime(endTime);
	        quote.setStatus(status);
	     
	        System.out.print("after quote");
	        quoteDAO.update(quote);
	        System.out.println("Ask the browser to call the list action next automatically contractor");
	        contractorPage(request, response, "");
	     
	        System.out.println("contractorUpdateQuote finished: 1111111111111111111111111111111");
	    }
	  
	    
	   
	    
	    
	    private void clientResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int quoteID=Integer.parseInt(request.getParameter("id"));
			quote res = quoteDAO.getQuote(quoteID);
			request.setAttribute("res", res);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ClientResponse.jsp");       
	        dispatcher.forward(request, response);
			
	    }
	   
	    private void contractorResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int quoteID=Integer.parseInt(request.getParameter("quoteID"));
			quote res = quoteDAO.getQuote(quoteID);
			request.setAttribute("res", res);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ContractorResponse.jsp");       
	        dispatcher.forward(request, response);
			
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
		
		
		
	    private void request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("in request");
            
	    	Integer clientID = (Integer)request.getSession().getAttribute("clientID");
            System.out.println("client id is "+clientID);
            request.setAttribute("request", request);
            
	    	int ctid = 11;
	    	//int clid = 10;
	    	double p = 0.0;
	    	String st = "2001-10-10 22:22:22";
	    	String ed = "2001-11-11 22:21:20";
	    	String stat = "0";
	    	
	    	quote quote = new quote(ctid, clientID, p, st, ed, stat);
	    	quote = quoteDAO.insertQuote(quote);
	    	int quoteID = quote.getQuoteID();
	    	
	    	System.out.print(quoteID);
	    	
            int numTrees = Integer.parseInt(request.getParameter("numTrees"));
            
            List<tree> trees = new ArrayList<>();
            
            for(int i = 1; i <= numTrees; i++) {
                String location = request.getParameter("location" + i);
                String height = request.getParameter("height" + i);
                String proximity = request.getParameter("proximity" + i);
                String sizeDiameter = request.getParameter("sizeDiameter" + i);
                String photodata1 = request.getParameter("photo1" + i);
                String photodata2 = request.getParameter("photo2" + i);
                String photodata3 = request.getParameter("photo3" + i);
            

                tree tree = new tree(quoteID, location, height, proximity, sizeDiameter, photodata1, photodata2, photodata3);
                treeDAO.insertTree(tree);    
            }
	    }
	   
		    /*
		    private void listRequest(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException, ServletException {
		        System.out.println("listRequest started: 00000000000000000000000000000000000");

		        List<request> listRequest = requestDAO.listAllRequests();
		        request.setAttribute("listRequest", listRequest);       
		        RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
		        dispatcher.forward(request, response);
		     
		        System.out.println("listRequest finished: 111111111111111111111111111111111111");

		    }    */

		    private void quoteMessage(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException, ServletException{ 
		    	System.out.println("in quotemessages");
		    	
		    	//may need to add response code here
		
	        
	            int userID = (Integer)request.getSession().getAttribute("clientID");
	            
	            int quoteID=Integer.parseInt(request.getParameter("quoteID"));
	            String msgtime = request.getParameter("msgtime");
	            String price = request.getParameter("price");
	            
	        	String schedulestart = request.getParameter("schedulestart");
	            String scheduleend = request.getParameter("scheduleend");
	          
	            String note = request.getParameter("note");
		    
	            //request.setAttribute("request", request);
	            
	           String msgt= "12:12:12";
	           double p= 10.00;
	           String schstart= "11:11:11";
	           String schend= "12:12:12";
	           
	     
	           System.out.println("client1");
	           QuotesMessages quotemessage = new QuotesMessages(userID, quoteID, msgt, p, schstart, schend, note);
	           QuotesMessagesDAO.insert(quotemessage);
	           RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
		       dispatcher.forward(request, response);
	           System.out.println("client2");
	          // System.out.println(x);
		       System.out.println("client3");
		    
		    }
	            	
		     
		     
}
