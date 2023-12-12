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
	@WebServlet("/QuotesMessagesDAO")
public class QuotesMessagesDAO {
		
		private static final long serialVersionUID = 1L;
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		public QuotesMessagesDAO(){}
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
		    public List<QuotesMessages> listAllQuotesMessages() throws SQLException {
		    	System.out.print("In the QuotesMessages list function");
		        List<QuotesMessages> listQuotesMessages = new ArrayList<QuotesMessages>();        
		        String sql = "SELECT * FROM QuotesMessages";      
		        connect_func();   
		        statement = (Statement) connect.createStatement();
		        ResultSet resultSet = statement.executeQuery(sql);
		         
		        while (resultSet.next()) {
		        	System.out.print("122");
		        	int quotemsgID = resultSet.getInt("quotemsgID");
		        	int userID = resultSet.getInt("userID");
		            int quoteID = resultSet.getInt("quoteID");
		            String msgtime = resultSet.getString("msgtime");
		            double price = resultSet.getDouble("price");
		            String schedulestart = resultSet.getString("schedulestart");
		            String scheduleend = resultSet.getString("scheduleend");
		            String status = resultSet.getString("status");
		            String note = resultSet.getString("note");
	
		             
		            QuotesMessages quotesmessages = new QuotesMessages(userID, quoteID, msgtime, price, schedulestart, scheduleend, status, note);
		            quotesmessages.setQuotemsgID(resultSet.getInt("quotemsgID"));
		            listQuotesMessages.add(quotesmessages);
		        }
		        
		        resultSet.close();
		        disconnect();        
		        return listQuotesMessages;
		    }
		    protected void disconnect() throws SQLException {
		        if (connect != null && !connect.isClosed()) {
		        	if (statement != null) {
		        	statement.close();
		        	}
		        	connect.close();
		        }
		    }
		    
		    public QuotesMessages insert(QuotesMessages quotesmessages) throws SQLException {
		    	connect_func("root","pass1234");         
				String sql = "insert into QuotesMessages(userID, quoteID, msgtime, price, schedulestart, scheduleend, status, note) values (?, ?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
					preparedStatement.setInt(2, quotesmessages.getUserID());
					preparedStatement.setInt(3, quotesmessages.getQuoteID());
					preparedStatement.setString(4, quotesmessages.getMsgtime());
					preparedStatement.setDouble(5, quotesmessages.getPrice());
					preparedStatement.setString(6, quotesmessages.getSchedulestart());
					preparedStatement.setString(7, quotesmessages.getScheduleend());
					preparedStatement.setString(8, quotesmessages.getStatus());
					preparedStatement.setString(9, quotesmessages.getNote());				
					String sql1= "SELECT max(quotemsgID) as quotemsgID FROM QuotesMessages";  
		    		ResultSet rs = preparedStatement.executeQuery(sql1);
		    		if (rs.next()) {
		    		quotesmessages.setQuoteID(rs.getInt("quotemsgID"));
		    		}
		    		preparedStatement.close();
		    		return quotesmessages;
		    }
		    public boolean delete(String email) throws SQLException {
		        String sql = "DELETE FROM QuotesMessages WHERE email = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setString(1, email);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        return rowDeleted;     
		    }
		    public boolean update(QuotesMessages quotesmessages) throws SQLException {
		        String sql = "update QuotesMessages set quotemsgID= ?, userID= ?, quoteID= ?, msgtime=?, price=?, schedulesttart=?, scheduleend=?, status=?, note=?, where email = ?";
		        connect_func();
		        
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, quotesmessages.getQuotemsgID());
				preparedStatement.setInt(2, quotesmessages.getUserID());
				preparedStatement.setInt(3, quotesmessages.getQuoteID());
				preparedStatement.setString(4, quotesmessages.getMsgtime());
				preparedStatement.setDouble(5, quotesmessages.getPrice());
				preparedStatement.setString(6, quotesmessages.getSchedulestart());
				preparedStatement.setString(7, quotesmessages.getScheduleend());	
				preparedStatement.setString(8, quotesmessages.getStatus());
				preparedStatement.setString(9, quotesmessages.getNote());			
			
		         
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        return rowUpdated;     
		    }
		    
		    public QuotesMessages getQuotesMessages(int QuotemsgID)  throws SQLException{
		        String sql = "SELECT * FROM QuotesMessages where QuotemsgID = "+QuotemsgID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        QuotesMessages quotesMessage=null;
		        if (rs.next()) {
		        	quotesMessage = new QuotesMessages(rs.getInt("userID"), rs.getInt("quoteID"), rs.getString("msgtime"),rs.getDouble("price"),rs.getString("schedulestart"), rs.getString("scheduleend"), rs.getString("status"), rs.getString("note"));
		        	quotesMessage.setQuotemsgID(rs.getInt("QuotemsgID"));
		        }
		        disconnect();        
		        return quotesMessage;
		    	
		    }
		    
		    public boolean delete(int quotemsgID) throws SQLException {
		        String sql = "DELETE FROM QuoteMessages WHERE quotemsgID = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, quotemsgID);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        disconnect();
		        return rowDeleted;     
		    }
		     
	
		   }
		    
		    
		    
		    
		    
		


