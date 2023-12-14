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
import java.sql.ResultSetMetaData;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/requestDAO")
public class requestDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public requestDAO(){}
	
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
	    public List<request> listAllRequests() throws SQLException {
	    	System.out.println("\n \n requestDAO.listAllRequests() is called.");
	        List<request> listRequest = new ArrayList<request>();        
	        String sql = "SELECT * FROM Request";      
	        connect_func();      
	        statement = (Statement) connect.createStatement();
	        ResultSet treeCount = statement.executeQuery(sql);
	        
	        ResultSetMetaData meta = treeCount.getMetaData();
	        for(int i=1;i<=meta.getColumnCount();i++) {
	        	System.out.println(meta.getColumnName(i));
	        }
	        
	        while (treeCount.next()){
	        	int requestID = treeCount.getInt("requestID");
	        	int clientID = treeCount.getInt("clientID");
	        	String location = treeCount.getString("location");
	            String height = treeCount.getString("height");
	            String proximity = treeCount.getString("proximity");
	            String sizeDiameter = treeCount.getString("sizeDiameter");
	        	String photo1= treeCount.getString("photo1");
	            String photo2 = treeCount.getString("photo2");
	            String photo3 = treeCount.getString("photo3");
	            String note= treeCount.getString("note");
	           
	            
	            request request = new request(requestID,clientID, location,height,proximity,sizeDiameter,photo1,photo2,photo3,note);
	            listRequest.add(request);
	            }
	        treeCount.close();
	        disconnect();        
	        return listRequest;
	            
	        }
	    
	    protected void disconnect() throws SQLException {
	        if (connect != null && !connect.isClosed()) {
	        	connect.close();
	        }
	    }
	    
	    public void insert(request requests) throws SQLException {
	    	connect_func();         
			String sql = "insert into Request(clientID,location,height,proximity,sizeDiameter,photo1,photo2,photo3,note) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
				preparedStatement.setInt(1, requests.getClientID());
				preparedStatement.setString(2, requests.getLocation());
				preparedStatement.setString(3, requests.getHeight());
				preparedStatement.setString(4, requests.getProximity());
				preparedStatement.setString(5, requests.getSizeDiameter());
				preparedStatement.setString(6, requests.getPhotodata1());		
				preparedStatement.setString(7, requests.getPhotodata2());		
				preparedStatement.setString(8, requests.getPhotodata3());		
				preparedStatement.setString(9, requests.getNote());			

			preparedStatement.executeUpdate();
	        preparedStatement.close();
	    }
/*
		    public request getRequest(String email) throws SQLException {
	    	request request = null;
	        String sql = "SELECT * FROM Client WHERE email = ?";
	         
	        connect_func();
	        
	        try {
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, email);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	            String location = resultSet.getString("location");
	            String height = resultSet.getString("height");
	            String proximity = resultSet.getString("proximity");
	            String sizeDiameter = resultSet.getString("diameter"); 
	            String photodata1 = resultSet.getString("photodata1"); 
	            String photodata2 = resultSet.getString("photodata2"); 
	            String photodata3 = resultSet.getString("photodata3"); 
	            String note = resultSet.getString("note"); 
	            request = new request(location, height, proximity, sizeDiameter, photodata1, photodata2, photodata3, note);
	        }
	    
	    }finally {
	    	if (preparedStatement != null) {
	    		preparedStatement.close();
	    	}
	    	disconnect();
	    }
	         
	        //resultSet.close();
	      //  statement.close();
	         
	        return request;
	    }
	   
	    /*
	    public void init() throws SQLException, FileNotFoundException, IOException{
	    	connect_func();
	        statement=this.connect.createStatement();
	    	
	 String[] INITIAL = 
	String[] TUPLES =
	    		
	       					
	        //for loop to put these in database
	    	for (int i = 0; i < INITIAL.length; i++) {
	 
	        	statement.execute(INITIAL[i]);
	    	}
	        for (int i = 0; i < TUPLES.length; i++) {
	        	statement.execute(TUPLES[i]);
	        }

	       
	        disconnect();
		}
	    */

		public request getRequest(int requestID) throws SQLException {
	    	System.out.println("\n \n requestDAO.getRequest() is called.");
	        List<request> listRequest = new ArrayList<request>();        
	        String sql = "SELECT * FROM Request where requestID = "+requestID;      
	        connect_func();      
	        PreparedStatement statement = connect.prepareStatement(sql);
	        ResultSet rs = statement.executeQuery(sql);
	        request request=null;
	        if (rs.next()) {
	            request = new request(rs.getInt("requestID"), rs.getInt("clientID"),rs.getString("location"), rs.getString("height"), rs.getString("proximity"),rs.getString("sizeDiameter"),rs.getString("photo1"),rs.getString("photo2"),rs.getString("photo3"),rs.getString("note"));
	        }
	        disconnect();        
	        return request;
		}
	   
}