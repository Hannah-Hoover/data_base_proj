public class user 
{
		protected int userID;
	 	protected String email;
	 	protected String password;
	    protected String firstName;
	    protected String lastName;
	    protected String role;
	    protected String address;
	    protected String creditCard;
	    protected String phone;
	    
	   /* public enum UserRole{
	    	Client, Contractor, Admin
	    }
	    
	    private UserRole role;
	    */
	    //constructors
	    public user() {
	    }
	 
	    public user(int userID) 
	    {
	        this.userID = userID;
	    }
	    
	    public user(int userID, String email, String password, String firstName, String lastName, String role, String address, String creditCard, String phone) 
	    {
	    	this(email, password, firstName, lastName, role, address, creditCard, phone);
	    	this.userID = userID;
	    }
	 
	
	    public user(String email, String password, String firstName, String lastName, String role, String address, String creditCard, String phone) 
	    {
	    	this.email = email;
	    	this.password = password;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	        this.role= role;
	        this.address = address;
	        this.creditCard = creditCard;
	        this.phone= phone;
	
	    }
	    
	   //getter and setter methods
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public int getuserID() {
	        return userID;
	    }
	    public void setuserID(int userID) {
	        this.userID = userID;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	  
	    public String getRole() {
	    	return role;
	    }
	    public void setRole(String role) {
	    	this.role = role;
	    }
	    
	    public String getaddress() {
	        return address;
	    }
	    public void setaddress(String address) {
	        this.address = address;
	    }
	    
	    public String getcreditCard() {
	    	return creditCard;
	    }
	    public void setcreditCard(String creditCard) {
	    	this.creditCard = creditCard;
	    }
	    
	    public String getphone() {
	    	return phone;
	    }
	    public void phone(String phone) {
	    	this.phone = phone;
	    }
	}