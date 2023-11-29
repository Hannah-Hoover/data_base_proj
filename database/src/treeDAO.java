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
	@WebServlet("/treeDAO")
public class treeDAO {
		private static final long serialVersionUID = 1L;
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		public treeDAO(){}
		
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
		
		    public List<tree> listAllTrees() throws SQLException {
		    	System.out.print("In the quotes list function");
		        List<tree> listTrees = new ArrayList<tree>();        
		        String sql = "SELECT * FROM Tree";      
		        connect_func();   
		        statement = (Statement) connect.createStatement();
		        ResultSet treeset = statement.executeQuery(sql);
		         
		        while (quoteset.next()) {
		        	  System.out.print("122");
                int treeID = treeset.getInt("treeID");
			          int quoteID = treeset.getInt("quoteID");
			          String location = treeset.getInt("location");
		            String height = treeset.getInt("height");
		            String proximity = treeset.getDouble("proximity");
		            String diameter = treeset.getString("diameter");
			          String photo1 = treeset.getString("photo1");
		            String photo2 = treeset.getString("photo2");
                String photo3 = treeset.getString("photo3");
	
		             
		            tree tree = new tree(quoteID, location, height, proxitimity, diameter, photo1, photo2, photo3);
		            tree.setTreeID(treeset.getInt("tree"));
		            listTree.add(tree);
		        }
		        
		    treeset.close();
		    disconnect();        
		    return listTree;
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
		    
		    public void insertTree(tree tree) throws SQLException {
		    	connect_func();         
				String sql = "insert into Tree(quoteID, location, height, proximity, diameter, photo1, photo2, photo3) values (?, ?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

			    		preparedStatement.setInt(1, tree.getQuoteID());
			    		preparedStatement.setString(2, tree.getLocation());
			    		preparedStatement.setString(3, tree.getHeight());
			    		preparedStatement.setString(4, tree.getProximity());
			    		preparedStatement.setString(5, tree.getDiameter());
			    		preparedStatement.setString(6, tree.getPhoto1());		
              preparedStatement.setString(7, tree.getPhoto2());	
              preparedStatement.setString(8, tree.getPhoto3());	
			    		preparedStatement.executeUpdate();
			    		preparedStatement.close();
		    }
		    
		    public boolean update(tree tree) throws SQLException {
		    	System.out.println("\n \n update in treeDAO.");
		        String sql = "update Tree set TreeID=?, QuoteID=?, Location=?, Height=?, Proximity=?, Diameter=?, Photo1=? Photo2 = ?, Photo3=?";
		        connect_func();
		     			preparedStatement.setInt(1, tree.getQuoteID());
			    		preparedStatement.setString(2, tree.getLocation());
			    		preparedStatement.setString(3, tree.getHeight());
			    		preparedStatement.setString(4, tree.getProximity());
			    		preparedStatement.setString(5, tree.getDiameter());
			    		preparedStatement.setString(6, tree.getPhoto1());		
              preparedStatement.setString(7, tree.getPhoto2());	
              preparedStatement.setString(8, tree.getPhoto3());
			    		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
//		        disconnect();
		        return rowUpdated;     
		    }
			
		    public quote getTree(int treeID)  throws SQLException{
		        String sql = "SELECT * FROM Tree where treeID = "+treeID;      
		        connect_func();      
		        PreparedStatement statement = connect.prepareStatement(sql);
		        ResultSet rs = statement.executeQuery(sql);
		        tree tree=null;
		        if (rs.next()) {
		            tree = new tree(rs.getInt("quoteID"), rs.getString("location"), rs.getString("height"), rs.getString("proximity"),rs.getString("diameter"),rs.getString("photo1"),rs.getString("photo2"),rs.getString("photo3"));
		            quote.setTreeID(rs.getInt("treeID"));
		        }
		        disconnect();        
		        return quote;
		    	
		    }

	/*
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
	*/
		    public boolean delete(int treeID) throws SQLException {
		        String sql = "DELETE FROM Tree WHERE treeID = ?";        
		        connect_func();
		         
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setInt(1, treeID);
		         
		        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		        preparedStatement.close();
		        disconnect();
		        return rowDeleted;     
		    }
		     
	
		   }
		    

