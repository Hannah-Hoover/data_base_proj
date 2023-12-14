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
import java.sql.ResultSet;
import java.sql.Timestamp;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private quoteDAO quoteDAO = new quoteDAO();
	    private treeDAO treeDAO = new treeDAO();
	    private clientDAO clientDAO = new clientDAO();
	    private contractorDAO contractorDAO = new contractorDAO();
	    private requestDAO requestDAO = new requestDAO();
	    private QuotesMessagesDAO QuotesMessagesDAO = new QuotesMessagesDAO();
	    private OrderInfoDAO OrderInfoDAO = new OrderInfoDAO();
	    private billDAO billDAO = new billDAO();
	    private BillsMessagesDAO BillsMessagesDAO = new BillsMessagesDAO();
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
         		System.out.println("The action is: listUserQuote");
         		listUserQuotes(request, response);
         		break;
        		
         	case "/updatequote":
         		System.out.println("The action is: updateQuote");
         		updateQuote(request, response);
         		break;
         	case "/updatequoteclient":
         		System.out.println("The action is: updateQuoteClient");
         		updateQuoteClient(request, response);
         		break;
         		
         	case "/newquote":
         		System.out.println("New Quote");
         		newQuote(request, response);
         		break;
         		
         	 case "/updatequotestatus":
        		 System.out.println("Client quitting quote");
        		 updateQuoteStatus(request, response);
        		 break;
        		 
         	case "/updatequotecontractor":
         		System.out.println("Contractor quitting quote");
         		updateQuoteContractor(request, response);
         		break;
         	case "/updateorderstatus":
         		System.out.println("Contractor quitting quote");
         		updateOrderStatus(request, response);
         		break;
         	case "/updatebillstatus":
         		System.out.println("Contractor quitting quote");
         		updateBillStatus(request, response);
         		break;
        	 
        	 case "/clientupdate":
        		 System.out.println("The action is: clientupdate");
        		 updateQuote(request, response);
        		 break;
        		 /*
        	 case "/contractorupdate":
        		 System.out.println("The action is: contractorupdate");
        		 contractorUpdateQuotes(request, response);
        		 break;
        		 */
        	 case "/clientresponse":
                 System.out.println("The action is: clientrepsonse");
             	 clientResponse(request, response);
                 break;
                 
                 
        	 case "/contractorresponse":
                 System.out.println("The action is: contractorrepsonse");
             	 contractorResponse(request, response);
                 break;
        	 case "/clientresponsebill":
                 System.out.println("The action is: clientrepsonse");
             	 clientResponseBill(request, response);
                 break;
                 
                 
        	 case "/contractorresponsebill":
                 System.out.println("The action is: contractorrepsonse");
             	 contractorResponseBill(request, response);
                 break;
                 
        	 case "/listUser": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	 case "/quoteMessage":
        		 System.out.println("The action is: quoteMessge");
                 quoteMessage(request, response);
                 break;
        	 case "/quoteMessageClient":
        		 System.out.println("The action is: quoteMessgeClient");
                 quoteMessageClient(request, response);
                 break;
        	 case "/billMessage":
        		 System.out.println("The action is: billMessge");
                 billMessage(request, response);
                 break;
        	 case "/billMessageClient":
        		 System.out.println("The action is: billMessgeClient");
                 billMessageClient(request, response);
                 break;
         	case "/request":
         		System.out.println("The action is: request");
         		request(request, response);
         		break;
         		
         	case "/listorders":
         		System.out.println("The action is: listOrders");
         		listOrders(request, response);
         		break;
         		
         	case "/listbills":
         		System.out.println("The action is: listbills");
         		listBills(request, response);
         		break;
         	case "/listuserbills":
         		System.out.println("The action is: listuserbills");
         		listuserBills(request, response);
         		break;
         		
         		
         		
         		
         		
         	case "/listbig":
         		System.out.println("The action is: listbig");
         		listBig(request, response);
         		break;
         	case "/listeasy":
         		System.out.println("The action is: listeasy");
         		listEasy(request, response);
         		break;
          	case "/listsingle":
         		System.out.println("The action is: listsingle");
         		listSingle(request, response);
         		break;
         	case "/listprospective":
         		System.out.println("The action is: listprospective");
         		listProspective(request, response);
         		break;
         	case "/listhighest":
         		System.out.println("The action is: listhighest");
         		listHighest(request, response);
         		break;
         	case "/listoverdue":
         		System.out.println("The action is: listoverdue");
         		listOverdue(request, response);
         		break;
         	case "/listbad":
         		System.out.println("The action is: listbad");
         		listBad(request, response);
         		break;
        	case "/listgood":
         		System.out.println("The action is: listgood");
         		listGood(request, response);
         		break;
        	case "/liststats":
         		System.out.println("The action is: liststats");
         		listStats(request, response);
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
			//request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("admin.jsp").forward(request, response);
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
	        
	        List<quote> listQuote = quoteDAO.listAllQuote();
	        request.setAttribute("listQuote", listQuote);     
	        RequestDispatcher dispatcher = request.getRequestDispatcher("clientactivitypage.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listUserQuote finished: 111111111111111111111111111111111111");
	    }

	    
	    private void newQuote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		  	int requestID=Integer.parseInt(request.getParameter("id"));
					request req = requestDAO.getRequest(requestID);
					request.setAttribute("req", req);
					RequestDispatcher dispatcher = request.getRequestDispatcher("contractorquote.jsp");       
			        dispatcher.forward(request, response);
					
				}
 
	    private void updateQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("contractorUpdateQuote started: 000000000000000000000000000");
	        
	        int quoteID=Integer.parseInt(request.getParameter("quoteID"));
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
	    
	    private void updateQuoteStatus(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("updateQuoteStatus started: 000000000000000000000000000");
	        String status = request.getParameter("status");
	        int quoteID = Integer.parseInt(request.getParameter("quoteID"));
	        
	        quote quote = quoteDAO.getQuote(quoteID);
	        quote.setStatus(status);
	        quoteDAO.update(quote);
	        System.out.print("after quote1");
	        //OrderInfo existing = OrderInfoDAO.getOrderInfoByQuote(quote.getQuoteID());
	       
	        if (status.equals("agree")){
	        	System.out.print("after quote2");
	        		String stat = "incomplete";
	        		OrderInfo order = new OrderInfo(quote.getQuoteID(), quote.getPrice(), quote.getStartTime(), quote.getEndTime(), stat);
	        		OrderInfoDAO.insert(order);
	        		System.out.print("after quote3");
	        }
	        clientPage(request, response , "");        
	        System.out.println("updateQuoteStatus finished: 000000000000000000000000000"); 
	        
	    }
	    public void updateOrderStatus(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException{
	    	System.out.println("updateQuoteStatus started: 000000000000000000000000000");
	        String status = request.getParameter("status");
	        int orderID = Integer.parseInt(request.getParameter("orderID"));
	        OrderInfo order = OrderInfoDAO.getOrderInfo(orderID);
	 
	        
	        order.setStatus(status);
	        OrderInfoDAO.update(order);
	        System.out.print("after quote1");
	        //OrderInfo existing = OrderInfoDAO.getOrderInfoByQuote(quote.getQuoteID());
	       
	        if (status.equals("complete")){
	        	System.out.print("after quote2");
	        		String stat = "pending";
	        		Timestamp current = new Timestamp(System.currentTimeMillis());
	        		
	        		bill bill = new bill(order.getOrderID(), current, null, order.getPrice(), 0, order.getPrice(), stat);
	        		billDAO.insertBill(bill);
	        		System.out.print("after quote3");
	        }
	        contractorPage(request, response , "");        
	        System.out.println("updateQuoteStatus finished: 000000000000000000000000000"); 
	    }
	    public void updateBillStatus(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException{
	    	
	    	System.out.println("updatBillStatus started: 000000000000000000000000000");
	    	
	        String status = request.getParameter("status");
	        int billID = Integer.parseInt(request.getParameter("billID"));
	        bill bill = billDAO.getBill(billID);
	        
	        bill.setStatus(status);
	        billDAO.update(bill);
	        System.out.print("after quote1");
	     
	        contractorPage(request, response , "");        
	        System.out.println("updateBillStatus finished: 000000000000000000000000000"); 
	    }
	    private void updateQuoteContractor(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("updateQuoteStatusContractor started: 000000000000000000000000000");
	        
	        String status = request.getParameter("status");
	        int quoteID = Integer.parseInt(request.getParameter("quoteID"));
	        quote quote = quoteDAO.getQuote(quoteID);
	        System.out.println("after get");
	        quote.setStatus(status);
	        
	        quoteDAO.update(quote);
	        contractorPage(request, response , "");
	        
	        System.out.println("updateQuoteStatusContractor finished: 000000000000000000000000000"); 
	        
	    }
	    private void updateQuoteClient(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("contractorUpdateQuote started: 000000000000000000000000000");
	        
	        int quoteID=Integer.parseInt(request.getParameter("quoteID"));
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
	        clientPage(request, response, "");
	     
	        System.out.println("clientUpdateQuote finished: 1111111111111111111111111111111");
	    }
	    private void clientResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int quoteID=Integer.parseInt(request.getParameter("quoteID"));
			quote res = quoteDAO.getQuote(quoteID);
			request.setAttribute("res", res);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ClientResponse.jsp");       
	        dispatcher.forward(request, response);
			
	    }
	    private void clientResponseBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int billID=Integer.parseInt(request.getParameter("billID"));
			bill bil = billDAO.getBill(billID);
			request.setAttribute("bil", bil);
	    }
	   
	    private void contractorResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int quoteID=Integer.parseInt(request.getParameter("quoteID"));
			quote res = quoteDAO.getQuote(quoteID);
			request.setAttribute("res", res);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ContractorResponse.jsp");       
	        dispatcher.forward(request, response);
			
		} 
	    private void contractorResponseBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int billID=Integer.parseInt(request.getParameter("billID"));
			bill bil = billDAO.getBill(billID);
			request.setAttribute("bil", bil);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ContractorResponseBill.jsp");       
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
		
		 private void listOrders(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException, ServletException {
		        System.out.println("listOrders started: 00000000000000000000000000000000000");
		        
		        List<OrderInfo> listOrders = OrderInfoDAO.listAllOrders();
		        request.setAttribute("listOrders", listOrders);       
		        RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
		        dispatcher.forward(request, response);
		     
		        System.out.println("listOrders finished: 111111111111111111111111111111111111");
		}
		 private void listBills(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException, ServletException {
		        System.out.println("listOrders started: 00000000000000000000000000000000000");
		        
		        List<bill> listBills = billDAO.listAllBills();
		        request.setAttribute("listBills", listBills);       
		        RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
		        dispatcher.forward(request, response);
		     
		        System.out.println("listOrders finished: 111111111111111111111111111111111111");
		}
		 
		 private void listuserBills(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException, ServletException {
		        System.out.println("listOrders started: 00000000000000000000000000000000000");
		        
		        List<bill> listBills = billDAO.listAllBills();
		        request.setAttribute("listBills", listBills);       
		        RequestDispatcher dispatcher = request.getRequestDispatcher("clientactivitypage.jsp");       
		        dispatcher.forward(request, response);
		     
		        System.out.println("listOrders finished: 111111111111111111111111111111111111");
		 }
		 /*
		 private void listUserBills(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException, ServletException {
		        System.out.println("listUserBills started: 00000000000000000000000000000000000");
		        
		        List<bill> listBills = billDAO.getBillsByOrderID();
		        request.setAttribute("listBills", listBills);     
		        RequestDispatcher dispatcher = request.getRequestDispatcher("clientactivitypage.jsp");       
		        dispatcher.forward(request, response);
		     
		        System.out.println("listUserBills finished: 111111111111111111111111111111111111");
		    }
		*/
		
	    private void request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("in request");
            
	    	Integer clientID = (Integer)request.getSession().getAttribute("clientID");
            System.out.println("client id is "+clientID);
            request.setAttribute("request", request);
            
	    	int ctid = 1;
	    	//int clid = 10;
	    	double p = 0.0;
	    	String st = "2001-10-10 22:22:22";
	    	String ed = "2001-11-11 22:21:20";
	    	String stat = "pending";
	    	
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
                RequestDispatcher dispatcher = request.getRequestDispatcher("clientactivitypage.jsp");       
 		        dispatcher.forward(request, response);
            }
	    }
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
	           
	     
	    
	           QuotesMessages quotemessage = new QuotesMessages(userID, quoteID, msgt, p, schstart, schend, note);
	           QuotesMessagesDAO.insert(quotemessage);
	           RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
		       dispatcher.forward(request, response);

		    
		    }
		    private void quoteMessageClient(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException, ServletException{ 
		    	System.out.println("in quotemessagesclient");
		    	
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
	           
	     
	    
	           QuotesMessages quotemessage = new QuotesMessages(userID, quoteID, msgt, p, schstart, schend, note);
	           QuotesMessagesDAO.insert(quotemessage);
	           RequestDispatcher dispatcher = request.getRequestDispatcher("clientactivitypage.jsp");       
		       dispatcher.forward(request, response);

		    
		    }
			private void billMessage(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException{ 
				System.out.println("in billsmessages");
				
				//may need to add response code here
			
			
			    //int userID = (Integer)request.getSession().getAttribute("clientID");
			    
			    int billID=Integer.parseInt(request.getParameter("billID"));
			    Integer userID = (Integer)request.getSession().getAttribute("clientID");
			    
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
			   
			
			
			   BillsMessages billmessage = new BillsMessages(userID, billID, p, schstart, schend, note);
			   BillsMessagesDAO.insert(billmessage);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			   dispatcher.forward(request, response);
			
			
			}
		private void billMessageClient(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException{ 
			System.out.println("in billsmessages");
			
			//may need to add response code here
		
		
		    //int userID = (Integer)request.getSession().getAttribute("clientID");
		    
		    int billID=Integer.parseInt(request.getParameter("billID"));
		    Integer userID = (Integer)request.getSession().getAttribute("clientID");
		    
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
		   
		
		
		   BillsMessages billmessage = new BillsMessages(userID, billID, p, schstart, schend, note);
		   BillsMessagesDAO.insert(billmessage);
		   RequestDispatcher dispatcher = request.getRequestDispatcher("clientactivitypage.jsp");       
		   dispatcher.forward(request, response);
		} 
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		/** 
		 * @see HttpServlet#HttpServlet()
	     */
	    protected void connect_func() throws SQLException {
	    	//uses default connection to the database
	        if (connect == null || connect.isClosed()) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	          connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
	            System.out.println(connect);
	        }
	    }
	    
	    public boolean database_login(String email, String password) throws SQLException{
	    	try {
	    		connect_func("root","pass1234");
	    		String sql = "select * from User where email = ?";
	    		preparedStatement = connect.prepareStatement(sql);
	    		preparedStatement.setString(1, email);
	    		ResultSet rs = preparedStatement.executeQuery();
	    		return rs.next();
	    	}
	    	catch(SQLException e) {
	    		System.out.println("failed login");
	    		return false;
	    	}
	    }
		//connect to the database 
	    public void connect_func(String username, String password) throws SQLException {
	        if (connect == null || connect.isClosed()) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	            connect = (Connection) DriverManager
	  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
	  			          + "useSSL=false&user=" + username + "&password=" + password);
	            System.out.println(connect);
	        }
	    }
	    
			
			private void listBig(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("listQuote started: 00000000000000000000000000000000000");
			    
			    connect_func();
			    
			    String sql = "SELECT u.userID AS clientID, u.firstName AS clientFirstName, " +
			            "       u.lastName AS clientLastName, COUNT(t.treeID) AS numberOfTreesCut " +
			            "FROM User u JOIN Quote q ON u.userID = q.clientID " +
			            "JOIN Tree t ON q.quoteID = t.quoteID " +
			            "WHERE u.role = 'Client' " +
			            "GROUP BY u.userID, u.firstName, u.lastName " +
			            "HAVING COUNT(t.treeID) = (" +
			            "   SELECT COUNT(t2.treeID) " +
			            "   FROM Tree t2 JOIN Quote q2 ON t2.quoteID = q2.quoteID " +
			            "   WHERE q2.clientID = u.userID " +
			            "   GROUP BY q2.clientID " +
			            "   ORDER BY COUNT(t2.treeID) DESC " +
			            "   LIMIT 1" +
			            ") " +
			            "ORDER BY numberOfTreesCut DESC";

			    PreparedStatement statement = connect.prepareStatement(sql);
			    ResultSet resultSet = statement.executeQuery();
			    
			    List<BigClient> bigClients = new ArrayList<>(); // Assuming BigClient is a class to hold client information
			    
			    while (resultSet.next()) {
			        int clientID = resultSet.getInt("clientID");
			        BigClient bigClient = new BigClient(clientID);
			        bigClients.add(bigClient);
			    }
			    
			    request.setAttribute("listBig", bigClients); // Set the list of big clients in the request scope

			    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
			    dispatcher.forward(request, response);

			}
			
			private void listEasy(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("listQuote started: 00000000000000000000000000000000000");
			    
			    List<quote> listQuote = quoteDAO.listAllQuote();
			    request.setAttribute("listQuote", listQuote);       
			    RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			    dispatcher.forward(request, response);
			 
			    System.out.println("listQuote finished: 111111111111111111111111111111111111");
			}
			
			private void listSingle(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("Listing Agreed Quotes with One Tree");

			    connect_func();

			    String sql = "SELECT q.quoteID " +
			            "FROM Quote q " +
			            "WHERE q.status = 'Agreed' " +
			            "AND q.quoteID IN (" +
			            "   SELECT quoteID FROM Tree GROUP BY quoteID HAVING COUNT(*) = 1" +
			            ")";

			    PreparedStatement statement = connect.prepareStatement(sql);
			    ResultSet resultSet = statement.executeQuery();

			    List<Integer> listSingle = new ArrayList<>();

			    while (resultSet.next()) {
			        int quoteID = resultSet.getInt("quoteID");
			        listSingle.add(quoteID);
			    }

			    request.setAttribute("listSingle", listSingle);

			    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
			    dispatcher.forward(request, response);
			}
			
			private void listProspective(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
				 System.out.println("Prosepective");

				    connect_func();

				    String sql = "SELECT u.userID, u.firstName, u.lastName " +
				            "FROM User u " +
				            "JOIN Quote q ON u.userID = q.clientID " +
				            "LEFT JOIN OrderInfo o ON q.quoteID = o.quoteID " +
				            "WHERE o.quoteID IS NULL";

				    PreparedStatement statement = connect.prepareStatement(sql);
				    ResultSet resultSet = statement.executeQuery();

				    List<client> listProspective = new ArrayList<>();

				    while (resultSet.next()) {
				        int userID = resultSet.getInt("userID");

				        client client = new client(userID);
				        listProspective.add(client);
				    }

				    request.setAttribute("listProspective", listProspective);

				    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
				    dispatcher.forward(request, response);
			}
			
			private void listHighest(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("listQuote started: 00000000000000000000000000000000000");
			    
			    List<quote> listQuote = quoteDAO.listAllQuote();
			    request.setAttribute("listQuote", listQuote);       
			    RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			    dispatcher.forward(request, response);
			 
			    System.out.println("listQuote finished: 111111111111111111111111111111111111");
			}
			
			private void listOverdue(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("listQuote started: 00000000000000000000000000000000000");
			    
			    List<quote> listQuote = quoteDAO.listAllQuote();
			    request.setAttribute("listQuote", listQuote);       
			    RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			    dispatcher.forward(request, response);
			 
			    System.out.println("listQuote finished: 111111111111111111111111111111111111");
			}
			
			private void listBad(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("listQuote started: 00000000000000000000000000000000000");
			    
			    List<quote> listQuote = quoteDAO.listAllQuote();
			    request.setAttribute("listQuote", listQuote);       
			    RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			    dispatcher.forward(request, response);
			 
			    System.out.println("listQuote finished: 111111111111111111111111111111111111");
			}
			
			private void listGood(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("listQuote started: 00000000000000000000000000000000000");
			    
			    List<quote> listQuote = quoteDAO.listAllQuote();
			    request.setAttribute("listQuote", listQuote);       
			    RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			    dispatcher.forward(request, response);
			 
			    System.out.println("listQuote finished: 111111111111111111111111111111111111");
			}
			
			private void listStats(HttpServletRequest request, HttpServletResponse response)
			        throws SQLException, IOException, ServletException {
			    System.out.println("listQuote started: 00000000000000000000000000000000000");
			    
			    List<quote> listQuote = quoteDAO.listAllQuote();
			    request.setAttribute("listQuote", listQuote);       
			    RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			    dispatcher.forward(request, response);
			 
			    System.out.println("listQuote finished: 111111111111111111111111111111111111");
			}



}
