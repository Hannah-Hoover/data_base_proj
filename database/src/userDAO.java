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
        	int userID = resultSet.getInt("userID");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            String address = resultSet.getString("address"); 
            String creditCard = resultSet.getString("creditCard"); 
            String phone = resultSet.getString("phone"); 
    
            user users = new user(userID, email, password, firstName, lastName, role, address, creditCard, phone);
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
		String sql = "insert into User(userID, email,  password, firstName, lastName, role, address, creditCard, phone) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, users.getUserID());
			preparedStatement.setString(2, users.getEmail());
			preparedStatement.setString(3, users.getPassword());
			preparedStatement.setString(4, users.getFirstName());
			preparedStatement.setString(5, users.getLastName());
			preparedStatement.setString(6, users.getRole());
			preparedStatement.setString(7, users.getAddress());		
			preparedStatement.setString(8, users.getCreditCard());		
			preparedStatement.setString(9, users.getPhone());			

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
        String sql = "update User set userID= ?, password = ?, firstName=?, lastName =?, role=?, address =?, creditCard=?, phone=? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, users.getUserID());
        preparedStatement.setString(2, users.getEmail());
        preparedStatement.setString(3, users.getPassword());
		preparedStatement.setString(4, users.getFirstName());
		preparedStatement.setString(5, users.getLastName());
		preparedStatement.setString(6, users.getRole());
		preparedStatement.setString(7, users.getAddress());		
		preparedStatement.setString(8, users.getCreditCard());		
		preparedStatement.setString(9, users.getPhone());		
	
         
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
        	int userID = resultSet.getInt("userID");
        	String password = resultSet.getString("password");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role= resultSet.getString("role");
            String address = resultSet.getString("address"); 
            String creditCard = resultSet.getString("creditCard"); 
            String phone = resultSet.getString("phone");
            user = new user(userID, email, password, firstName, lastName, role, address,  creditCard,  phone);
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
			        		"lastName VARCHAR(20) NOT NULL, "+
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
						    "startTime DATETIME,"+
						    "endTime DATETIME,"+
						    "status VARCHAR(20),"+
						    "FOREIGN KEY (contractorID) REFERENCES User(userID),"+
						    "FOREIGN KEY (clientID) REFERENCES User(userID)"+"); ",
					
					
                   "SET FOREIGN_KEY_CHECKS=0;",
						    "drop table if exists OrderInfo; ",
			                   "CREATE TABLE if not exists OrderInfo( " + 
			                		   "orderID INT AUTO_INCREMENT PRIMARY KEY, "+
			                		   "quoteID INTEGER, "+
			                		   "price DOUBLE,"+
			                		   "schedulestart DATETIME,"+
			                		   "scheduleend DATETIME,"+
			                		   "status VARCHAR(20),"+
			                		   "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)" + ");  ",
			       //"SET FOREIGN_KEY_CHECKS=1;",
                		   
                		   
                	"drop table if exists Tree; ",
                	"CREATE TABLE if not exists Tree (" +
                			"treeID INT AUTO_INCREMENT PRIMARY KEY,"+
                		    "quoteID INTEGER,"+
							"location VARCHAR(70), " +
							"height VARCHAR(20), " +
							"proximity VARCHAR(20), " +
							"sizeDiameter VARCHAR(20), " +
							"photo1 BLOB, " +
							"photo2 BLOB, " +
							"photo3 BLOB, " +
							"FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)"+ "); ",
							
					"SET FOREIGN_KEY_CHECKS=0;",
					"drop table if exists Bill; ",
					"CREATE TABLE if not exists Bill( " +
							"billID INT AUTO_INCREMENT PRIMARY KEY,"+
						    "orderID INTEGER,"+
							"price DOUBLE, "+
						    "discount DOUBLE, "+
						    "balance DOUBLE, "+
							"status VARCHAR(20),"+
						    "current TIMESTAMP DEFAULT NULL,"+
						    "accepted TIMESTAMP DEFAULT NULL,"+
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
    	  
String[] TUPLES = {"insert into User(email, password, firstName, lastName, role, address, creditCard, phone)"+
			"values ('root', 'pass1234', 'default', 'default', 'admin', 'default', '0000 0000 0000 0000', '000-000-0000')," +
							"('breanna@gmail.com', 'breanna1234', 'Breanna', 'Walts', 'client', '1923 briggs street, Warren, MI 49502', '2222 2223 2224 2225', '517-724-0192'),"+
							"('david@gmail.com', 'pass1234', 'David', 'Smith', 'contractor', '1983 dumfore street, Pontiac, MI 43292', '4444 4444 44444 4444', '818-800-8000'),"+
							"('logan@gmail.com', 'logan1234', 'Logan', 'Baker', 'client', '6413 greene sqaure, Troy, MI 48915', '3332 3333 3334 3335', '248-970-1137'), "+
							"('calire@gmail.com', 'claire1234', 'Claire', 'Fields', 'client', '1342 prime road, Detroit, MI 49203', '4442 4443 4444 4445', '586-431-3801'),"+
							"('alexa@gmail.com', 'alexa1234', 'Alexa', 'Ferguson', 'client', '3951 kohler street, Rochester, MI 48323', '5552 5553 5554 5555', '248-115-4328'), "+
							"('nathan@gmail.com', 'nathan1234', 'Nathan', 'Long', 'client', '8439 rennings road, New Baltimore, MI 49222', '6662 6663 6664 6665', '248-554-4182'),"+
							"('craig@gmail.com', 'craig1234', 'Craig', 'mcdaniel', 'client', '1233 tribune road, Flint, MI 43202', '7772 7773 7774 7775', '818-904-6122'),"+
							"('anna@gmail.com', 'anna1234', 'Anna', 'Hector', 'client', '1593 liberty circle, Commerce, MI 48312', '8882 8883 8884 8885', '248-144-2830'), "+
							"('justin@gmail.com', 'justin1234', 'Justin', 'Novil', 'client', '1963 peace road, Woxom, MI 48320', '9992 9993 9994 9995', '248-108-3349'),"+
							"('marie@gmail.com', 'marie1234', 'Marie', 'palmer', 'client', '1123 croten road, Dearborn, MI 433202', '2222 1113 1114 1115', '808-998-1274');",
	    			
	    			
							"INSERT INTO Quote (contractorID, clientID, price, startTime, endTime, status) " +
									"VALUES " +
									"('2','2','120.00', '2023-01-15', '2023-01-20','pending')," +
									"('2','3','150.00', '2023-02-10', '2023-02-15','accepted')," +
									"('2','4','200.00', '2023-03-05', '2023-03-10','pending')," +
									"('2','5','180.00', '2023-04-20', '2023-04-25','accepted')," +
									"('2','6','250.00', '2023-05-15', '2023-05-20','pending')," +
									"('2','7','180.00', '2023-06-10', '2023-06-15','accepted')," +
									"('2','8','300.00', '2023-07-05', '2023-07-10','pending');",
 		  
		
			"INSERT INTO OrderInfo (quoteID, price, schedulestart, scheduleend, status) " +
			"VALUES " +
			"('3', '130.00' , '2023-12-05', '2023-12-15', 'complete')," +
			"('4', '90.00' , '2024-01-10', '2024-01-20', 'incomplete')," +
			"('5', '200.00' , '2024-02-15', '2024-02-25', 'complete')," +
			"('6', '150.00' , '2024-03-20', '2024-03-30', 'incomplete')," +
			"('7', '180.00' , '2024-04-25', '2024-05-05', 'complete')," +
			"('8', '220.00' , '2024-05-30', '2024-06-10', 'incomplete')," +
			"('9', '250.00' , '2024-06-15', '2024-06-25', 'complete');",
		    	    
   
			"INSERT INTO Tree (quoteID, location, height, proximity, sizeDiameter, photo1, photo2, photo3) " +
			"VALUES " +
			"('1', 'backyard-east', '2.0 meters', '0.5 meters', '60 millimeters', '0x000000', '0x000000', '0x000000')," +
			"('2', 'front-yard-west', '1.5 meters', '0.3 meters', '40 millimeters', '0x111111', '0x111111', '0x111111')," +
			"('3', 'side-yard-north', '1.8 meters', '0.4 meters', '50 millimeters', '0x222222', '0x222222', '0x222222')," +
			"('4', 'backyard-south', '1.2 meters', '0.2 meters', '30 millimeters', '0x333333', '0x333333', '0x333333')," +
			"('5', 'front-yard-east', '2.2 meters', '0.5 meters', '70 millimeters', '0x444444', '0x444444', '0x444444')," +
			"('6', 'side-yard-west', '1.6 meters', '0.3 meters', '45 millimeters', '0x555555', '0x555555', '0x555555')," +
			"('7', 'backyard-north', '1.9 meters', '0.4 meters', '55 millimeters', '0x666666', '0x666666', '0x666666');",
	
			
			"INSERT INTO Bill (orderID, current, accepted, price, discount, balance, status) " +
			"VALUES " +
			"('1', '2023-11-27 02:28:11', '2023-11-29 02:28:11', '100.00', '10.00', '90.00', 'pending')," +
			"('2', '2023-12-02 03:15:45', '2023-12-05 04:20:30', '120.00', '15.00', '105.00', 'complete')," +
			"('3', '2024-01-10 01:30:20', '2024-01-12 02:45:10', '80.00', '8.00', '72.00', 'pending')," +
			"('4', '2024-02-18 05:12:30', '2024-02-20 06:30:45', '150.00', '20.00', '130.00', 'complete')," +
			"('5', '2024-03-25 02:45:15', '2024-03-27 03:55:30', '200.00', '25.00', '175.00', 'pending');",
	    
    		    	    
    		    	    
    	 
            
	        "INSERT INTO QuotesMessages(userID, quoteID, msgtime, price, schedulestart, scheduleend, note)"+
	        "VALUES" + 
	        			"('1', '1', '2023-11-27 02:28:11', '100.00','2024-10-12', '2024-10-22', 'note');",
	        			
	        			
	        			
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
