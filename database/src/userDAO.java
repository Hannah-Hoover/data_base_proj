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
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSet treeCount= null;
	
	public userDAO(){}
	
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
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");

             
            user users = new user(email,firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, cash_bal,PPS_bal);
            listUser.add(users);
            }
        resultSet.close();
        disconnect();        
        return listUser;
            
        }
 /*   public List<request> listAllRequests() throws SQLException {
        List<request> listRequest = new ArrayList<request>();        
        String sql = "SELECT * FROM Request";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet treeCount = statement.executeQuery(sql);
         
        while (treeCount.next()){
        	String location = treeCount.getString("location");
            String height = treeCount.getString("height");
            String proximity = treeCount.getString("proximity");
            String sizeDiameter = treeCount.getString("diameter");
        	String photodata1= treeCount.getString("photodata1");
            String photodata2 = treeCount.getString("photodata2");
            String photodata3 = treeCount.getString("photodata3");
           
            
            request request = new request(location,height,proximity,sizeDiameter,photodata1,photodata2,photodata3);
            listRequest.add(request);
            }
        treeCount.close();
        disconnect();        
        return listRequest;
            
        }
    */
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	if (statement != null) {
        	statement.close();
        	}
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,cash_bal,PPS_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());		
			preparedStatement.setInt(11, users.getCash_bal());		
			preparedStatement.setInt(12, users.getPPS_bal());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());		
		preparedStatement.setString(7, users.getAdress_street());		
		preparedStatement.setString(8, users.getAdress_city());		
		preparedStatement.setString(9, users.getAdress_state());		
		preparedStatement.setString(10, users.getAdress_zip_code());		
		preparedStatement.setInt(11, users.getCash_bal());		
		preparedStatement.setInt(12, users.getPPS_bal());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
        
        try {
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String role= resultSet.getString("role");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");
            user = new user(email, firstName, lastName, password, birthday, role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,cash_bal,PPS_bal);
        }
    
    }finally {
    	if (preparedStatement != null) {
    		preparedStatement.close();
    	}
    	disconnect();
    }
         
        //resultSet.close();
      //  statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
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
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
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
    	String sql = "SELECT * FROM User";
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
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement=this.connect.createStatement();
    	
    	  String[] INITIAL = {"drop database if exists testdb; ",
			        "create database testdb; ",
			        "use testdb;",
			        "drop table if exists User; ",
			        "CREATE TABLE if not exists User( " +
			        		"userID INT AUTO_INCREMENT PRIMARY KEY,"+
			        		"email	VARCHAR(50) NOT NULL,"+
			        		"password VARCHAR(20) NOT NULL, "+
			        		"firstName VARCHAR(20) NOT NULL, "+
			        		"lastname VARCHAR(20) NOT NULL, "+
			        	    "role VARCHAR(20) NOT NULL,"+
			        		"address VARCHAR(50), "+
			        		"creditCard VARCHAR(50)," +
			        		"phone VARCHAR(15)" +"); ",
			        		
			        "drop table if exists Quote; ",                           
					"CREATE TABLE if not exists Quote( " +
						    "quoteID INT AUTO_INCREMENT PRIMARY KEY, " + 
						    "contractorID INTEGER,"+
						    "clientID INTEGER, "+
						    "price DOUBLE, "+
						    "schedulestart DATETIME,"+
						    "scheduleend DATETIME,"+
						    "FOREIGN KEY (contractorID) REFERENCES User(userID),"+
						    "FOREIGN KEY (clientID) REFERENCES User(userID)"+"); ",
					
					
                   "drop table if exists OrderInfo; ",
                   "CREATE TABLE if not exists OrderInfo( " + 
                		   "orderID INT AUTO_INCREMENT PRIMARY KEY, "+
                		   "quoteID INTEGER, "+
                		   "price DOUBLE,"+
                		   "schedulestart DATETIME,"+
                		   "scheduleend DATETIME,"+
                		   "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)" + ");  ",
                		 
                		   
                		   
                	"drop table if exists Tree; ",
                	"CREATE TABLE if not exists Tree (" +
                			"treeID INT AUTO_INCREMENT PRIMARY KEY,"+
                		    "quoteID INTEGER,"+
							"location VARCHAR(70), " +
							"height VARCHAR(20), " +
							"proximity VARCHAR(20), " +
							"sizeDiameter VARCHAR(20), " +
							"photodata1 BLOB, " +
							"photodata2 BLOB, " +
							"photodata3 BLOB, " +
							"FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)"+ "); ",
							
							
					"drop table if exists Bill; ",
					"CREATE TABLE if not exists Bill( " +
							"billID INT AUTO_INCREMENT PRIMARY KEY,"+
						    "orderID INTEGER,"+
							"price DOUBLE, "+
						    "discount DOUBLE, "+
						    "balance DOUBLE, "+
							"status VARCHAR(20),"+
							"FOREIGN KEY (orderID) REFERENCES OrderInfo(orderID)"+"); ",
							
							
			        
				        	
				        	
				        	
				    "drop table if exists QuotesMessages; ",
    	  			"CREATE TABLE if not exists QuotesMessages("+
    	  					"quotemsgID INT AUTO_INCREMENT PRIMARY KEY, "+
    	  					"userID INTEGER, "+
    	  					"quoteID INTEGER,"+
    	  					"msgtime DATETIME,"+
    	  					"price DOUBLE,"+
    	  					"schedulestart DATETIME,"+
    	  					"scheduleend DATETIME,"+
    	  					"note VARCHAR(200),"+
    	  					"FOREIGN KEY(userID) REFERENCES User(userID),"+
    	  					"FOREIGN KEY(quoteID) REFERENCES Quote(quoteID)"+"); ",
    	  					
    	  		    "drop table if exists BillsMessages;",
    	  			"CREATE TABLE if not exists BillsMessages("+
    	  					"billmsgID INT AUTO_INCREMENT PRIMARY KEY,"+
    	  					"userID INTEGER,"+
    	  					"billID INTEGER, "+
    	  					"price DOUBLE,"+
    	  					"schedulestart DATETIME,"+
    	  					"scheduleend DATETIME,"+
    	  					"note VARCHAR(200),"+
    	  					"FOREIGN KEY(userID) REFERENCES User(userID), FOREIGN KEY(billID) REFERENCES Bill(billID)"+"); ",

					};
    	  
String[] TUPLES = {"insert into User(email, password, firstName, lastname, role, address, creditCard, phone)"+
			"values ('root', 'pass1234', 'default', 'default', 'admin', 'default', '0000 0000 0000 0000', '000-000-0000')," +
							"('breanna@gmail.com', 'breanna1234', 'Breanna', 'Walts', 'client', '1923 briggs street, Warren, MI 49502', '2222 2223 2224 2225', '517-724-0192'),"+
							"('logan@gmail.com', 'logan1234', 'Logan', 'Baker', 'client', '6413 greene sqaure, Troy, MI 48915', '3332 3333 3334 3335', '248-970-1137'), "+
							"('calire@gmail.com', 'claire1234', 'Claire', 'Fields', 'client', '1342 prime road, Detroit, MI 49203', '4442 4443 4444 4445', '586-431-3801'),"+
							"('alexa@gmail.com', 'alexa1234', 'Alexa', 'Ferguson', 'client', '3951 kohler street, Rochester, MI 48323', '5552 5553 5554 5555', '248-115-4328'), "+
							"('nathan@gmail.com', 'nathan1234', 'Nathan', 'Long', 'client', '8439 rennings road, New Baltimore, MI 49222', '6662 6663 6664 6665', '248-554-4182'),"+
							"('craig@gmail.com', 'craig1234', 'Craig', 'mcdaniel', 'client', '1233 tribune road, Flint, MI 43202', '7772 7773 7774 7775', '818-904-6122'),"+
							"('anna@gmail.com', 'anna1234', 'Anna', 'Hector', 'client', '1593 liberty circle, Commerce, MI 48312', '8882 8883 8884 8885', '248-144-2830'), "+
							"('justin@gmail.com', 'justin1234', 'Justin', 'Novil', 'client', '1963 peace road, Woxom, MI 48320', '9992 9993 9994 9995', '248-108-3349'),"+
							"('marie@gmail.com', 'marie1234', 'Marie', 'palmer', 'client', '1123 croten road, Dearborn, MI 433202', '2222 1113 1114 1115', '808-998-1274'),"+
							"('david@gmail.com', 'pass1234', 'David', 'Smith', 'contractor', '1983 dumfore street, Pontiac, MI 43292', '4444 4444 44444 4444', '818-800-8000');",
	    			
	    			
			"INSERT INTO Quote (contractorID, clientID, price, schedulestart, scheduleend) " +
			"VALUES " +
							"('1','1','00.00', '0001-01-01', '0001-01-01');",
 		  
			"INSERT INTO OrderInfo (quoteID, price, schedulestart, scheduleend) " +
			"VALUES " +
        		    	    "('1', '100.00' , '2023-11-02', '2023-12-02');",
        		    	    
        		    	    
        		    	    
    	    "INSERT INTO Tree (quoteID, location, height, proximity, sizeDiameter, photodata1,  photodata2, photodata3)"+
  		  	"VALUES " +
  		  				"('1', 'backyard-east', '2.0 meters','.5 meters', '60 millimeters', '0x000000', '0x000000', '0x000000');",
  		  				
  		  				
			"INSERT INTO Bill (orderID, price, discount, balance, status) " +
			"VALUES " +
    		    	    "('1', '100.00', '10.00', '90.00', 'pending');",
    		    	    
    		    	    
    		    	    
    		    	    
    	    
            
	        "INSERT INTO QuotesMessages(userID, quoteID, msgtime, price, schedulestart, scheduleend)"+
	        "VALUES" + 
	        			"('1', '1', '2023-11-27 02:28:11', '100.00','2024-10-12', '2024-10-22');",
	        			
	        			
	        			
	       "INSERT INTO BillsMessages(userID, billID, price, schedulestart, scheduleend)"+
	       "VALUES" + 
	    		   	"('1','1','100.00', '2024-10-12', '2024-10-22');"
	
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
}
