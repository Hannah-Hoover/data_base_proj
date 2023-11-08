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
@WebServlet("/clientDAO")
public class clientDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public clientDAO(){}
	
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
	
	    public List<client> listClients() throws SQLException {
	        List<client> listClient = new ArrayList<client>();        
	        String sql = "SELECT * FROM Client";      
	        connect_func();   
	        statement = (Statement) connect.createStatement();
	        ResultSet clientset = statement.executeQuery(sql);
	         
	        while (clientset.next()) {
	        	System.out.print("122");
	        	int clientID = clientset.getInt("clientID");
	            String email = clientset.getString("email");
	            String password = clientset.getString("password");
	            String firstName = clientset.getString("firstName");
	            String lastName = clientset.getString("lastName");
	            String address = clientset.getString("address"); 
	            String creditcard = clientset.getString("creditCard");  
	            String phone = clientset.getString("phone");

	             
	            client client = new client(clientID, email,password, firstName, lastName,address, creditcard, phone);
	            listClient.add(client);
	        }
	        
	    clientset.close();
	    disconnect();        
	    return listClient;
	    }
	    
	    protected void disconnect() throws SQLException {
	        if (connect != null && !connect.isClosed()) {
	        	connect.close();
	        }
	    }
	    
	    public void insertClient(client clients) throws SQLException {
	    	System.out.print("IN THE INSERT FUNCTION");
	    	connect_func("root","pass1234");         
			String sql = "insert into Client(email, firstName, lastName, password, address, phone, creditcard) values (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
				preparedStatement.setString(1, clients.getEmail());
				preparedStatement.setString(2, clients.getFirstName());
				preparedStatement.setString(3, clients.getLastName());
				preparedStatement.setString(4, clients.getPassword());		
				preparedStatement.setString(5, clients.getAddress());		
				preparedStatement.setString(6, clients.getPhone());		
				preparedStatement.setString(7, clients.getCreditcard());			

			preparedStatement.executeUpdate();
	        preparedStatement.close();
	    }
	    
	    public boolean checkClientEmail(String email) throws SQLException {
	    	boolean checks = false;
	    	String sql = "SELECT * FROM Client WHERE email = ?";
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
	    	String sql = "SELECT * FROM Client";
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
	    	
	 String[] INITIAL = {"drop table if exists Client; ",
						"CREATE TABLE if not exists Client( " +
								"clientID INTEGER, " + 
								"email VarChar(50) NOT NULL, " +
								"password VarChar(20) NOT NULL, " + 
								"firstName VarChar(20) NOT NULL, " +
								"lastname VarChar(20) NOT NULL, " +
								"address VarChar(50), " +
								"creditCard VarChar(50), " +
								"phone VarChar(15), " +
								"PRIMARY KEY (clientID) "
								+"); ",
						};
	    	  
	String[] TUPLES = {"INSERT INTO Client (clientID, email, password, firstName, lastName, address, creditcard, phone) " +
	 		    	   "VALUES " +
	 		    	    		"('000', 'root', 'pass1234', 'default', 'default', 'default', '0000 0000 0000 0000', '000-000-0000'), " +
	 		    	    		"('111', 'john@gmail.com', 'john1234', 'John', 'Smith', '1273 success road, Detroit, MI 49202', '1112 1113 1114 1115', '248-454-7892'), " +
	 		    	    		"('222', 'breanna@gmail.com', 'breanna1234', 'Breanna', 'Walts', '1923 briggs street, Warren, MI 49502', '2222 2223 2224 2225', '517-724-0192'), " +
	 		    	    		"('333', 'logan@gmail.com', 'logan1234', 'Logan', 'Baker', '6413 greene sqaure, Troy, MI 48915', '3332 3333 3334 3335', '248-970-1137'), " +
	 		    	    		"('444', 'calire@gmail.com', 'claire1234', 'Claire', 'Fields', '1342 prime road, Detroit, MI 49203', '4442 4443 4444 4445', '586-431-3801'), " +
	 		    	    		"('555', 'alexa@gmail.com', 'alexa1234', 'Alexa', 'Ferguson', '3951 kohler street, Rochester, MI 48323', '5552 5553 5554 5555', '248-115-4328'), " +
	 		    	    		"('666', 'nathan@gmail.com', 'nathan1234', 'Nathan', 'Long', '8439 rennings road, New Baltimore, MI 49222', '6662 6663 6664 6665', '248-554-4182'), " +
	 		    	    		"('777', 'craig@gmail.com', 'craig1234', 'Craig', 'mcdaniel', '1233 tribune road, Flint, MI 43202', '7772 7773 7774 7775', '818-904-6122'), " +
	 		    	    		"('888', 'anna@gmail.com', 'anna1234', 'Anna', 'Hector', '1593 liberty circle, Commerce, MI 48312', '8882 8883 8884 8885', '248-144-2830'), " +
	 		    	    		"('999', 'justin@gmail.com', 'justin1234', 'Justin', 'Novil', '1963 peace road, Woxom, MI 48320', '9992 9993 9994 9995', '248-108-3349'), " +
	 		    	    		"('112', 'marie@gmail.com', 'marie1234', 'Marie', 'palmer', '1123 croten road, Dearborn, MI 433202', '2222 1113 1114 1115', '808-998-1274');",
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
