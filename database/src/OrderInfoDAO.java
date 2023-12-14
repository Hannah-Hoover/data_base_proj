	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.Statement;
	import java.sql.PreparedStatement;
	//import java.sql.Connection;
	//import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	//import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	/**
	 * Servlet implementation class Connect
	 */
	@WebServlet("/OrderInfoDAO")
public class OrderInfoDAO {
		
		private static final long serialVersionUID = 1L;
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		public OrderInfoDAO(){}
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
	
		    public List<OrderInfo> listAllOrders() throws SQLException {
		    	System.out.print("In the quotes list function");
		        List<OrderInfo> listOrders = new ArrayList<OrderInfo>();        
		        String sql = "SELECT * FROM OrderInfo";      
		        connect_func();   
		        statement = (Statement) connect.createStatement();
		        ResultSet orderset = statement.executeQuery(sql);
		         
		        while (orderset.next()) {
		        	System.out.print("122");
		        	int orderID = orderset.getInt("orderID");
		        	int quoteID = orderset.getInt("quoteID");
		        	double price = orderset.getDouble("price");
		        	String schedulestart = orderset.getString("schedulestart");
		        	String scheduleend = orderset.getString("scheduleend");
		        	String status = orderset.getString("status");
			  
	
		            OrderInfo orders = new OrderInfo(orderID, quoteID, price, schedulestart, scheduleend, status);
		            orders.setOrderID(orderset.getInt("orderID"));
		            listOrders.add(orders);
		        }
		        
		    orderset.close();
		    disconnect();        
		    return listOrders;
		    }
		    
		    
		 
		    
		    protected void disconnect() throws SQLException {
		        if (connect != null && !connect.isClosed()) {
		        	connect.close();
		        }
		    }
		    
		   public void insert(OrderInfo orders) throws SQLException {
		    	connect_func();   
		    	System.out.println("Insert Order Info");
				String sql = "insert into OrderInfo(quoteID, price, schedulestart, scheduleend, status) values (?, ?, ?, ?, ?)";
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			    		preparedStatement.setInt(1, orders.getQuoteID());
			    		preparedStatement.setDouble(2, orders.getPrice());
			    		preparedStatement.setString(3, orders.getSchedulestart());
			    		preparedStatement.setString(4, orders.getScheduleend());
			    		preparedStatement.setString(5, orders.getStatus());
			    		preparedStatement.executeUpdate();
			    		
						preparedStatement.close();
		
		    }
		    
		    public boolean update(OrderInfo orders) throws SQLException {
		    	System.out.println("\n \n update in OrderInfoDAO.");
		        String sql = "update OrderInfo set Price=?, Schedulestart=?, Scheduleend=?, Status=? where OrderID = ?";
		        connect_func();

	    		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	    		preparedStatement.setDouble(1, orders.getPrice());
	    		preparedStatement.setString(2, orders.getSchedulestart());
	    		preparedStatement.setString(3, orders.getScheduleend());	
	      		preparedStatement.setString(4, orders.getStatus());
	      		preparedStatement.setInt(5,  orders.getOrderID());
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
//		        disconnect();
		        return rowUpdated;     
		    }
			
		    public OrderInfo getOrderInfo(int OrderID)  throws SQLException{
		        String sql = "SELECT * FROM OrderInfo where orderID = "+OrderID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        OrderInfo order=null;
		        if (rs.next()) {
		            order = new OrderInfo(rs.getInt("orderID"), rs.getInt("quoteID"), rs.getDouble("price"), rs.getString("schedulestart"),rs.getString("scheduleend"), rs.getString("status"));
		            order.setOrderID(rs.getInt("orderID"));
		        }
		        disconnect();        
		        return order;
		    	
		    }
		    public OrderInfo getOrderInfoByQuote(int quoteID)  throws SQLException{
		        String sql = "SELECT * FROM OrderInfo where quoteID = "+quoteID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        OrderInfo order=null;
		        if (rs.next()) {
		            order = new OrderInfo(rs.getInt("orderID"), rs.getInt("quoteID"), rs.getDouble("price"), rs.getString("schedulestart"),rs.getString("scheduleend"), rs.getString("status"));
		            order.setOrderID(rs.getInt("orderID"));
		        }
		        disconnect();        
		        return order;
		    	
		    }

		    public boolean delete(int orderID) throws SQLException {
		        String sql = "DELETE FROM OrderInfo WHERE orderID = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, orderID);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        disconnect();
		        return rowDeleted;     
		    }
		     
	
}
