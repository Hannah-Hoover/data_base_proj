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
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
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
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
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
            user = new user(email, firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,cash_bal,PPS_bal);
        }
         
        resultSet.close();
        statement.close();
         
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
        statement =  (Statement) connect.createStatement();
        
       String[] userINITIAL = {
    		   	"drop database if exists testdb; ",
    		   			"create database testdb; ",
    		   			"use testdb; ",
    		   			"drop table if exists User; ",
    		   			("CREATE TABLE IF NOT EXISTS User ( " +
    		   					"email VARCHAR(50) NOT NULL, " + 
    		   					"firstName VARCHAR(10) NOT NULL, " +
    		   					"lastName VARCHAR(10) NOT NULL, " +
    		   					"password VARCHAR(20) NOT NULL, " +
    		   					"birthday DATE NOT NULL, " +
    		   					"adress_street_num VARCHAR(4), " + 
    		   					"adress_street VARCHAR(30), " + 
    		   					"adress_city VARCHAR(20), " +
    		   					"adress_state VARCHAR(2), " + 
    		   					"adress_zip_code VARCHAR(5), " + 
    		   					"cash_bal DECIMAL(13,2) DEFAULT 1000, " + 
    		   					"PPS_bal DECIMAL(13,2) DEFAULT 0, " +
    		   					"PRIMARY KEY (email) "+"); ")
       						};
    
       String[] userTUPLES = {
    		    "INSERT INTO User (email, firstName, lastName, password, birthday, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal) " +
    		    	    "VALUES " +
    		    	    		"('susie@gmail.com', 'Susie', 'Guzman', 'susie1234', '2000-06-27', '1234', 'whatever street', 'detroit', 'MI', '48202', 1000, 0), " +
    		    	    		"('don@gmail.com', 'Don', 'Cummings', 'don123', '1969-03-20', '1000', 'hi street', 'mama', 'MO', '12345', 1000, 0), " +
    		    	    		"('margarita@gmail.com', 'Margarita', 'Lawson', 'margarita1234', '1980-02-02', '1234', 'ivan street', 'tata', 'CO', '12561', 1000, 0), " +
    		    	    		"('jo@gmail.com', 'Jo', 'Brady', 'jo1234', '2002-02-02', '3214', 'marko street', 'brat', 'DU', '54321', 1000, 0), " +
    		    	    		"('wallace@gmail.com', 'Wallace', 'Moore', 'wallace1234', '1971-06-15', '4500', 'frey street', 'sestra', 'MI', '48202', 1000, 0), " +
    		    	    		"('amelia@gmail.com', 'Amelia', 'Phillips', 'amelia1234', '2000-03-14', '1245', 'm8s street', 'baka', 'IL', '48000', 1000, 0), " +
    		    	    		"('sophie@gmail.com', 'Sophie', 'Pierce', 'sophie1234', '1999-06-15', '2468', 'yolos street', 'ides', 'CM', '24680', 1000, 0), " +
    		    	    		"('angelo@gmail.com', 'Angelo', 'Francis', 'angelo1234', '2021-06-14', '4680', 'egypt street', 'lolas', 'DT', '13579', 1000, 0), " +
    		    	    		"('rudy@gmail.com', 'Rudy', 'Smith', 'rudy1234', '1706-06-05', '1234', 'sign street', 'samo ne tu', 'MH', '09876', 1000, 0), " +
    		    	    		"('jeannette@gmail.com', 'Jeannette', 'Stone', 'jeannette1234', '2001-04-24', '0981', 'snoop street', 'kojik', 'HW', '87654', 1000, 0), " +
    		    	    		"('root', 'default', 'default', 'pass1234', '0000-00-00', '0000', 'Default', 'Default', '0', '00000', 1000, 1000000000);"
    		    			};
    		    
    		   
    		    //client 
    	String[] clientINITIAL = {
    			"drop database if exists testdb; ",
						"create database testdb; ",
						"use testdb; ",
						"drop table if exists Client; ",
						("CREATE TABLE Client if not exists Client( " +
								"clientID INTEGER NOT NULL, " + 
								"clientEmail VarChar(50) NOT NULL, " +
								"clientPassword VarChar(20) NOT NULL, " + 
								"clientFirstName VarChar(20) NOT NULL, " +
								"clientLastname VarChar(20) NOT NULL, " +
								"clientAddress VarChar(50) NOT NULL, " +
								"clientPayment VarChar(50), NOT NULL, " +
								"clientPhone VarChar(10), NOT NULL, " +
								"PRIMARY KEY (clientID), "+"); ")
							};

    	String[] clientTUPLES = {
    		     "INSERT INTO Client (clientID, clientEmail, clientPassword, clientFirstName, clientLastname, clientAddress, clientPayment, clientPhone) " +
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
    		    	    		"('112', 'marie@gmail.com', 'marie1234', 'Marie', 'palmer', '1123 croten road, Dearborn, MI 433202', '2222 1113 1114 1115', '808-998-1274');"
	    		    		};
    		
                           
                            //contractor
        String[] contractorINITIAL = {
        		"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists Contractor; ",
                            ("CREATE TABLE Contractor if not exists Contractor( " +
                                "clientID INTEGER NOT NULL, " +
                                "FOREIGN KEY clientID REFERENCES Client(clientID), "+");")
        					};
        
        String[] contractorTUPLES = {
                "INSERT INTO Contractor (clientID) " +
                		  "VALUES " +
                		  				"('000'),"+
                		  				"('111'),"+
                		  				"('222'),"+
                		  				"('333'),"+
                		  				"('444'),"+
                		  				"('555'),"+
                		  				"('666'),"+
                		  				"('777'),"+
                		  				"('888'),"+
                		  				"('999'),"+
                		  				"('112');"
                		    			};

                			//order
        String[] orderinfoINITIAL = {
        		"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists OrderInfo; ",
                            ("CREATE TABLE OrderInfo if not exists Order( " + 
	                            "contractID VARCHAR(20) NOT NULL, " +
                                "clientID INTEGER NOT NULL, " +
                                "FOREIGN KEY clientID REFERENCES Client(clientID), "+");")
        					};
                            
        String[] orderinfoTUPLES = {
                    "INSERT INTO Contractor (clientID) " +
                               "VALUES " +
                                    	    "('000', '0001-0001'),"+
                        		    	    "('111', '1111-1112'),"+
                        		    	    "('222', '2221-2222'),"+
                        		    	    "('333', '3331-3332'),"+
                        		    	    "('444', '4441-4442'),"+
                        		    	    "('555', '5551-5552'),"+
                        		    	    "('666', '6661-6662'),"+
                        		    	    "('777', '7771-7772'),"+
                        		    	    "('888', '8881-8882'),"+
                        		    	    "('999', '9991-9992'),"+
                        		    	    "('112', '1112-1113');"
                        		    	};
        String[] treeINITIAL= {
    			"drop database if exists testdb; ",
    						"create database testdb; ",
    						"use testdb; ",
    						"drop table if exists Tree; ",
    						("CREATE TABLE Tree (" +
    								"clientID INTEGER, " +
    								"location VARCHAR(70), " +
    								"height VARCHAR(20), " +
    								"proximity VARCHAR(20), " +
    								"sizeDiameter VARCHAR(20), " +
    								"photoID INTEGER, " +
    								"photodata1 BLOB, " +
    								"photodata2 BLOB, " +
    								"photodata3 BLOB, " +
    								"PRIMARY KEY (photoID), " +
    								"FOREIGN KEY (clientID) REFERENCES Client(clientID), "+ ");")
        							};
        String[] treeTUPLES = {
                "INSERT INTO Tree (clientID) " +
                		  "VALUES " +
                		  				"('000', 'default', 'default', 'default','default', '11100', '0x000000', '0x000000', '0x000000'),"+
                		  				"('111', 'backyard-east', '2.0 meters', '1 meter','60 millimeters', '11101', '0xFFD856382', '0xFFD830386', '0xFFD998450'),"+
                		  				"('222', 'backyard-south', '1.75 meters', '2 meters','50 millimeters', '11102', '0xFFD546721', '0xFFD223421', '0xFFD450897'),"+
                		  				"('333', 'backyard-northeast', '3.0 meters', '.5 meters','90 millimeters', '11103', '0xFFD675867', '0xFFD890798', '0xFFD707908'),"+
                		  				"('444', 'backyard-northwest', '2.5 meters', '2.2 meters','70 millimeters', '11104', '0xFFD124514', '0xFFD123511', '0xFFD109281'),"+
                		  				"('555', 'backyard-north', '2.75 meters', '1.5 meters','80 millimeters', '11105', '0xFFD105690', '0xFFD119938', '0xFFD343567'),"+
                		  				"('666', 'backyard-west', '4.5 meters', '3.2 meters','130 millimeters', '11106', '0xFFD097749', '0xFFD085591', '0xFFD134239'),"+
                		  				"('777', 'frontyard-southeast', '4.0 meters', '6 meters','120 millimeters', '11107', '0xFFD195872', '0xFFD294851', '0xFFD219191'),"+
                		  				"('888', 'frontyard-northeast', '3.5 meters', '2.1 meters','1100 millimeters', '11108', '0xFFD103885', '0xFFD390568', '0xFFD457683'),"+
                		  				"('999', 'frontyard-northwest', '3.75 meters', '9 meters','115 millimeters', '11109', '0xFFD301938', '0xFFD285920', '0xFFD193852'),"+
                		  				"('112', 'frontyard-southwest', '3.25 meters', '4.5 meters','100 millimeters', '22200', '0xFFD333876', '0xFFD784713', '0xFFD029344');"
                		    			};

                            //Bill table
        String[] billINITIAL = {
        		"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists Bill; ",
                            ("CREATE TABLE Bill( " +
                                "clientID INTEGER NOT NULL, " + 
	                            "amount INTEGER NOT NULL, " + 
                                "status BIT DEFAULT False, " + 
                                "FORGEIGN KEY clientID REFERENCES Client(clientID), "+");")
        					};
        
        String[] billTUPLES = {
        		"INSERT INTO Bill (clientID) " +
        				"VALUES " +
                    	    "('000', '000.00', '0'),"+
        		    	    "('111', '1000.00', '1'),"+
        		    	    "('222', '1200.00', '1'),"+
        		    	    "('333', '800.00', '0'),"+
        		    	    "('444', '300.00', '1'),"+
        		    	    "('555', '900.00', '1'),"+
        		    	    "('666', '400.00', '0'),"+
        		    	    "('777', '850.00', '1'),"+
        		    	    "('888', '700.00', '0'),"+
        		    	    "('999', '300.00', '0'),"+
        		    	    "('112', '1100.00', '0');"
        		    	};
                           
        
        			//Quote
        String[] quoteINITIAL = {
        		"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists Quote; ",                           
                            ("CREATE TABLE Quote( " +
                                "clientID INTEGER NOT NULL, " + 
                                "intprice DOUBLE NOT NULL, " + 
                                "Timeframe VARCHAR() NOT NULL, " + 
                                "FOREIGN KEY clientID REFERENCES Client(clientID), "+");")
        					};
        
       String[] quoteTUPLES = {
                 "INSERT INTO Quote (clientID) " +
                            "VALUES " +
                                  "('000', '000.00', 'default'),"+
                                  "('111', '1000.00', '1 week'),"+
                                  "('222', '1200.00', '3 days'),"+
                                  "('333', '800.00', '2 weeks'),"+
                                  "('444', '300.00', '1 week'),"+
                                  "('555', '900.00', '3 weeks'),"+
                                  "('666', '400.00', '1 month'),"+
                                  "('777', '850.00', '5 days'),"+
                                  "('888', '700.00', '4 days'),"+
                                  "('999', '300.00', '2 weeks'),"+
                                  "('112', '1100.00', '1 week');"
                            	};


                        
       					
        
        //for loop to put these in database
        for (int i = 0; i < userINITIAL.length; i++)
        	statement.execute(userINITIAL[i]);
        for (int i = 0; i < userTUPLES.length; i++)	
        	statement.execute(userTUPLES[i]);
        
        for (int i = 0; i < clientINITIAL.length; i++)
        	statement.execute(clientINITIAL[i]);
        for (int i = 0; i < clientTUPLES.length; i++)	
        	statement.execute(clientTUPLES[i]);
        
        for (int i = 0; i < contractorINITIAL.length; i++)
        	statement.execute(contractorINITIAL[i]);
        for (int i = 0; i < contractorTUPLES.length; i++)	
        	statement.execute(contractorTUPLES[i]);
        
        for (int i = 0; i < orderinfoINITIAL.length; i++)
        	statement.execute(orderinfoINITIAL[i]);
        for (int i = 0; i < orderinfoTUPLES.length; i++)	
        	statement.execute(orderinfoTUPLES[i]);
        
        for (int i = 0; i < billINITIAL.length; i++)
        	statement.execute(billINITIAL[i]);
        for (int i = 0; i < billTUPLES.length; i++)	
        	statement.execute(billTUPLES[i]);
        
        for (int i = 0; i < quoteINITIAL.length; i++)
        	statement.execute(quoteINITIAL[i]);
        for (int i = 0; i < quoteTUPLES.length; i++)	
        	statement.execute(quoteTUPLES[i]);
        
        disconnect();
}
}
