public class client {
	protected String password;
 	protected String email;
    protected String firstName;
    protected String lastName;
    protected String phone;
	protected String address;
	protected String creditcard;
	protected int clientID;
	
 
    //constructors
    public client() {
    }
 
    public client(int clientID) 
    {
        this.clientID = clientID;
    }
    
    public client(int clientID, String email,String firstName, String lastName, String password, String address, String phone, String creditcard) 
    {
    	this(email, firstName, lastName, password, address, phone, creditcard);
    	this.clientID = clientID; 
    }
 

    public client(String email, String firstName, String lastName, String password, String address, String phone, String creditcard) 
    {
    	this.email = email;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.password = password;
        this.address = address;
        this.phone = phone;
        this.creditcard = creditcard;
    }
    
   //getter and setter methods
    public int getID() {
        return clientID;
    }
    public void setID(int clientID) {
        this.clientID = clientID;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
    	return address;
    }
    public void setAddress(String address) {
    	this.address = address;
    }
    public String getPhone() {
    	return phone;
    }
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    public String getCreditcard() {
    	return creditcard;
    }
    public void setCreditcard(String creditcard) {
    	this.creditcard = creditcard;
    }

}
