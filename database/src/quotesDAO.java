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
	@WebServlet("/quoteDAO")
public class quotesDAO {
		private static final long serialVersionUID = 1L;
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		public quotesDAO(){}
		
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
		    		String sql = "select * from Client where email = ?";
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
		
		    public List<quotes> listAllQuotes() throws SQLException {
		    	System.out.print("In the quotes list function");
		        List<quotes> listQuotes = new ArrayList<quotes>();        
		        String sql = "SELECT * FROM Quotes";      
		        connect_func();   
		        statement = (Statement) connect.createStatement();
		        ResultSet quoteset = statement.executeQuery(sql);
		         
		        while (quoteset.next()) {
		        	System.out.print("122");
		        	int clientID = quoteset.getInt("clientID");
		            double price = quoteset.getDouble("price");
		            String timeFrame = quoteset.getString("timeFrame");
		            String status = quoteset.getString("status");
		            int requestID = quoteset.getInt("requestID");
		            String note = quoteset.getString("note");
	
		             
		            quotes quote = new quotes(price, timeFrame, status, requestID, clientID, note);
		            quote.setQuoteID(quoteset.getInt("quoteID"));
		            listQuotes.add(quote);
		        }
		        
		    quoteset.close();
		    disconnect();        
		    return listQuotes;
		    }
		    
		    
		  /*
		    public List<quotes> listUserQuotes() throws SQLException {
		    	System.out.print("In the userlist function");
		        List<quotes> listUserQuotes = new ArrayList<quotes>();        
		        String sql = "SELECT * FROM Quotes= ";      
		        connect_func();   
		        statement = (Statement) connect.createStatement();
		        ResultSet quoteset = statement.executeQuery(sql);
		         
		        while (quoteset.next()) {
		        	System.out.print("122");
		        	int clientID = quoteset.getInt("clientID");
		            double price = quoteset.getDouble("price");
		            String timeFrame = quoteset.getString("timeFrame");
		            String status = quoteset.getString("status");
		            int requestID = quoteset.getInt("requestID");
	
		             
		            quotes quote = new quotes(price, timeFrame, status, requestID, clientID);
		            listUserQuotes.add(quote);
		        }
		        
		    quoteset.close();
		    disconnect();        
		    return listUserQuotes;
		    }
		 */
		    
		    protected void disconnect() throws SQLException {
		        if (connect != null && !connect.isClosed()) {
		        	connect.close();
		        }
		    }
		    
		    public void insertQuote(quotes quotes) throws SQLException {
		    	connect_func();         
				String sql = "insert into Quotes(price, timeFrame, status, requestID,clientID, note) values (?, ?, ?, ?, ?, ?)";
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
					
					preparedStatement.setDouble(1, quotes.getPrice());
					preparedStatement.setString(2, quotes.getTimeFrame());
					preparedStatement.setString(3, quotes.getStatus());	
					preparedStatement.setInt(4, quotes.getRequestID());	
					preparedStatement.setInt(5, quotes.getClientID());
					preparedStatement.setString(6, quotes.getNote());	
				preparedStatement.executeUpdate();
		        preparedStatement.close();
		    }
		    
		    public boolean update(quotes quote) throws SQLException {
		    	System.out.println("\n \n update in quoteDAO.");
		        String sql = "update Quotes set Price=?, TimeFrame =?, Status = ?,  RequestID = ?, Note = ?, ClientID = ? where quoteID= ?";
		        connect_func();
		        
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
				preparedStatement.setDouble(1, quote.getPrice());
				preparedStatement.setString(2, quote.getTimeFrame());
				preparedStatement.setString(3, quote.getStatus());	
				preparedStatement.setInt(4, quote.getRequestID());
				preparedStatement.setString(5, quote.getNote());
				preparedStatement.setInt(6, quote.getClientID());
				preparedStatement.setInt(7, quote.getQuoteID());
				
		         
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
//		        disconnect();
		        return rowUpdated;     
		    }
			
		    public quotes getQuote(int quoteID)  throws SQLException{
		        String sql = "SELECT * FROM Quotes where quoteID = "+quoteID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        quotes quote=null;
		        if (rs.next()) {
		            quote = new quotes(rs.getDouble("price"),rs.getString("timeFrame"),rs.getString("status"), rs.getInt("requestID"), rs.getInt("clientID"), rs.getString("note"));
		            quote.setQuoteID(rs.getInt("quoteID"));
		        }
		        disconnect();        
		        return quote;
		    	
		    }
		    
		    public List<quotes> getQuotesByClientID(int clientID) throws SQLException {
		    	System.out.println("\n \n quoteDAO.getQuote() is called.");
		        List<quotes> listQuotes = new ArrayList<quotes>();        
		        String sql = "SELECT * FROM Quotes where clientID = "+clientID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        quotes quote=null;
		        while (rs.next()) {
		            quote = new quotes(rs.getDouble("price"),rs.getString("timeFrame"),rs.getString("status"), rs.getInt("requestID"), rs.getInt("clientID"), rs.getString("note"));
		            quote.setQuoteID(rs.getInt("quoteID"));
		            listQuotes.add(quote);
		        }
		        disconnect();        
		        return listQuotes;
			}
		    public boolean delete(int quoteID) throws SQLException {
		        String sql = "DELETE FROM Quotes WHERE quoteID = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, quoteID);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        disconnect();
		        return rowDeleted;     
		    }
		     
	
		   }
		    

