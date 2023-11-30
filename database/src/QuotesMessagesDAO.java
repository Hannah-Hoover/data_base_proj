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
		            String note = resultSet.getString("note");
	
		             
		            QuotesMessages quotesmessages = new QuotesMessages(userID, quoteID, msgtime, price, schedulestart, scheduleend, note);
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
		    
		

}
