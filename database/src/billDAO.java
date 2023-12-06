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
	@WebServlet("/billDAO")
public class billDAO {
		private static final long serialVersionUID = 1L;
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		public billDAO(){}
		
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
		
		    public List<bill> listAllBills() throws SQLException {
		    	System.out.print("In the quotes list function");
		        List<bill> listBills = new ArrayList<bill>();        
		        String sql = "SELECT * FROM Bill";      
		        connect_func();   
		        statement = (Statement) connect.createStatement();
		        ResultSet billset = statement.executeQuery(sql);
		         
		        while (billset.next()) {
		        	  System.out.print("122");
                int billID = billset.getInt("billID");
			    int orderID = billset.getInt("quoteID");
                double price = billset.getDouble("price");
                double discount = billset.getDouble("discount");
                double balance = billset.getDouble("balance");
                String status = billset.getString("status");
	
		             
		            bill bill = new bill(orderID, price, discount, balance, status);
		            bill.setBillID(billset.getInt("bill"));
		            listBills.add(bill);
		        }
		        
		    billset.close();
		    disconnect();        
		    return listBills;
		    }
		   
		    
		    protected void disconnect() throws SQLException {
		        if (connect != null && !connect.isClosed()) {
		        	connect.close();
		        }
		    }
		    
		    public void insertBill(bill bill) throws SQLException {
		    	connect_func();         
				String sql = "insert into Bill(orderID, price, discount, balance, status) values (?, ?, ?, ?, ?)";
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

			    		preparedStatement.setInt(1, bill.getOrderID());
			    		preparedStatement.setDouble(2, bill.getPrice());
			    		preparedStatement.setDouble(3, bill.getDiscount());
			    		preparedStatement.setDouble(4, bill.getBalance());
			    		preparedStatement.setString(5, bill.getStatus());
			    		preparedStatement.executeUpdate();
			    		preparedStatement.close();
		    }
		    
		    public boolean update(bill bill) throws SQLException {
		    	System.out.println("\n \n update in billDAO.");
		        String sql = "update Bill set BillID=?, OrderID=?, Price=?, Discount=?, Balance=?, Status=?";
		        connect_func();
			    		preparedStatement.setInt(1, bill.getOrderID());
			    		preparedStatement.setDouble(2, bill.getPrice());
			    		preparedStatement.setDouble(3, bill.getDiscount());
			    		preparedStatement.setDouble(4, bill.getBalance());
			    		preparedStatement.setString(5, bill.getStatus());
			    		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
//		        disconnect();
		        return rowUpdated;     
		    }
			
		    public bill getBill(int billID)  throws SQLException{
		        String sql = "SELECT * FROM Bill where billID = "+billID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        bill bill=null;
		        if (rs.next()) {
		            bill = new bill(rs.getInt("orderID"), rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("balance"),rs.getString("status"));
		            bill.setBillID(rs.getInt("billID"));
		        }
		        disconnect();        
		        return bill;
		    	
		    }
		    
		    public boolean delete(int billID) throws SQLException {
		        String sql = "DELETE FROM Bill WHERE billID = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, billID);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        disconnect();
		        return rowDeleted;     
		    }
		     
	
		   }
		    


