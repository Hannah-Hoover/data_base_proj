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
@WebServlet("/contractorDAO")
public class contractorDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public contractorDAO(){}
	
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
	
	    public List<contractor> listContractors() throws SQLException {
	        List<contractor> listContractor = new ArrayList<contractor>();        
	        String sql = "SELECT * FROM Contractor";      
	        connect_func();   
	        statement = (Statement) connect.createStatement();
	        ResultSet contractorset = statement.executeQuery(sql);
	         
	        while (contractorset.next()) {
	        	System.out.print("122");
	        	int clientID = contractorset.getInt("clientID");
	            String email = contractorset.getString("email");
	            String password = contractorset.getString("password");
	            String firstName = contractorset.getString("firstName");
	            String lastName = contractorset.getString("lastName");
	
	            contractor contractors = new contractor(clientID, email,password, firstName, lastName);
	            listContractor.add(contractors);
	        }
	        
	    contractorset.close();
	    disconnect();        
	    return listContractor;
	    }
	    
	    protected void disconnect() throws SQLException {
	        if (connect != null && !connect.isClosed()) {
	        	connect.close();
	        }
	    }
	    
	    public void insertContractor(contractor contractors) throws SQLException {
	    	System.out.println("IN THE INSERT FUNCTION");
	    	System.out.println(contractors.getEmail());
	    	
	    	connect_func("root","pass1234");         
			String sql = "insert into Constructor(email, firstName, lastName, password, address, phone, creditcard) values (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
				preparedStatement.setString(1, contractors.getEmail());
				preparedStatement.setString(2, contractors.getFirstName());
				preparedStatement.setString(3, contractors.getLastName());
				preparedStatement.setString(4, contractors.getPassword());		
			
			preparedStatement.executeUpdate();
	        preparedStatement.close();
	    }
	    
	    public contractor getContractor(String email) throws SQLException {
	    	contractor contractor = null;
	        String sql = "SELECT * FROM Contractor WHERE email = ?";
	         
	        connect_func();
	        
	        try {
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, email);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	            String firstName = resultSet.getString("firstName");
	            String lastName = resultSet.getString("lastName");
	            String password = resultSet.getString("password");
	            contractor = new contractor(email, firstName, lastName, password);
	        }
	    
	    }finally {
	    	if (preparedStatement != null) {
	    		preparedStatement.close();
	    	}
	    	disconnect();
	    }
	         
	        //resultSet.close();
	      //  statement.close();
	         
	        return contractor;
	    }
	    
	    public boolean checkContractorEmail(String email) throws SQLException {
	    	boolean checks = false;
	    	String sql = "SELECT * FROM Contractor WHERE email = ?";
	    	connect_func();
	    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        System.out.println(checks);	
	        
	        if (resultSet.next()) {
	        	checks = true;
	        }
	        
	        System.out.println(checks);
	    	return checks;
	    }
	    public boolean isValid(String email, String password) throws SQLException
	    {
	    	String sql = "SELECT * FROM Contractor";
	    	connect_func();
	    	statement = (Statement) connect.createStatement();
	    	ResultSet resultSet = statement.executeQuery(sql);
	    	
	    	resultSet.last();
	    	
	    	int setSize = resultSet.getRow();
	    	resultSet.beforeFirst();
	    	
	    	for(int i = 0; i < setSize; i++)
	    	{
	    		resultSet.next();
	    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
	    			return true;
	    		}		
	    	}
	    	return false;
	    }
	    /*
	    public void init() throws SQLException, FileNotFoundException, IOException{
	    	connect_func();
	        statement=this.connect.createStatement();
	    	
	 String[] INITIAL = {"drop table if exists Contractor; ",
                    		"CREATE TABLE if not exists Contractor( " +
                    		"clientID INTEGER NOT NULL " +"); ",
                    		"email VARCHAR(50) NOT NULL, " + 
			            	"firstName VARCHAR(10) NOT NULL, " +
			            	"lastName VARCHAR(10) NOT NULL, " +
			            	"password VARCHAR(20) NOT NULL, " +
						};
	    	  
	String[] TUPLES = {"INSERT INTO Contractor (clientID, email, password, firstName, lastName) " +
 		    	   "VALUES " +
 		    	    		"('000', 'david@gmail.com', 'pass1234', 'david', 'smith');",
		};

	    		
	       					
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
	   
}