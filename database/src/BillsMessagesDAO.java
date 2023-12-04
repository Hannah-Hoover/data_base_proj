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
	@WebServlet("/BillsMessagesDAO")
public class BillsMessagesDAO {

		private static final long serialVersionUID = 1L;
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		public BillsMessagesDAO(){}
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
		    
		    public List<BillsMessages> listAllBillsMessages() throws SQLException {
		    	System.out.print("In the BillsMessages list function");
		        List<BillsMessages> listBillsMessages = new ArrayList<BillsMessages>();        
		        String sql = "SELECT * FROM BillsMessages";      
		        connect_func();   
		        statement = (Statement) connect.createStatement();
		        ResultSet resultSet = statement.executeQuery(sql);
		         
		        while (resultSet.next()) {
		        	System.out.print("122");
		        	int billmsgID = resultSet.getInt("billmsgID");
		        	int userID = resultSet.getInt("userID");
		            int billID = resultSet.getInt("billID");
		            double price = resultSet.getDouble("price");
		            String schedulestart = resultSet.getString("schedulestart");
		            String scheduleend = resultSet.getString("scheduleend");
		            String note = resultSet.getString("note");
	
		             
		            BillsMessages billsmessages = new BillsMessages(billmsgID, userID, billID, price, schedulestart, scheduleend, note);
		            billsmessages.setBillmsgID(resultSet.getInt("billmsgID"));
		            listBillsMessages.add(billsmessages);
		        }
		        
		        resultSet.close();
		        disconnect();        
		        return listBillsMessages;
		    }
		    protected void disconnect() throws SQLException {
		        if (connect != null && !connect.isClosed()) {
		        	if (statement != null) {
		        	statement.close();
		        	}
		        	connect.close();
		        }
		    }
		    
		    public void insert(BillsMessages billsmessages) throws SQLException {
		    	connect_func("root","pass1234");         
				String sql = "insert into BillsMessages(billmsgID, userID, billID, price, schedulestart, scheduleend, note) values (?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
					preparedStatement.setInt(1, billsmessages.getBillmsgID());
					preparedStatement.setInt(2, billsmessages.getUserID());
					preparedStatement.setInt(3, billsmessages.getBillID());
					preparedStatement.setDouble(5, billsmessages.getPrice());
					preparedStatement.setString(6, billsmessages.getSchedulestart());
					preparedStatement.setString(7, billsmessages.getScheduleend());		
					preparedStatement.setString(8, billsmessages.getNote());				

				preparedStatement.executeUpdate();
		        preparedStatement.close();
		    }
		    public boolean delete(String email) throws SQLException {
		        String sql = "DELETE FROM BillsMessages WHERE email = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setString(1, email);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        return rowDeleted;     
		    }
		    public boolean update(BillsMessages billsmessages) throws SQLException {
		        String sql = "update BillsMessages set billmsgID= ?, userID= ?, billID= ?, price=?, schedulesttart=?, scheduleend=?, note=?, where email = ?";
		        connect_func();
		        
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, billsmessages.getBillmsgID());
				preparedStatement.setInt(2, billsmessages.getUserID());
				preparedStatement.setInt(3, billsmessages.getBillID());
				preparedStatement.setDouble(5, billsmessages.getPrice());
				preparedStatement.setString(6, billsmessages.getSchedulestart());
				preparedStatement.setString(7, billsmessages.getScheduleend());		
				preparedStatement.setString(8, billsmessages.getNote());			
			
		         
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        return rowUpdated;     
		    }
		    
		    public QuotesMessages getBillsMessages(int BillmsgID)  throws SQLException{
		        String sql = "SELECT * FROM BillsMessages where BillmsgID = "+BillmsgID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        BillsMessages billsMessage=null;
		        if (rs.next()) {
		        	billsMessage = new BillsMessages(rs.getInt("billmsgID"), rs.getInt("userID"), rs.getInt("billID"),rs.getDouble("price"),rs.getString("schedulestart"), rs.getString("scheduleend"), rs.getString("note"));
		        	billsMessage.setBillmsgID(rs.getInt("billmsgID"));
		        }
		        disconnect();        
		        return billsmessage;
		    	
		    }
		    
		    public boolean delete(int billmsgID) throws SQLException {
		        String sql = "DELETE FROM BillsMessages WHERE billmsgID = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, billmsgID);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        disconnect();
		        return rowDeleted;     
		    }
		     
	
		   }
		    
		    
		    
		    
		    
		


