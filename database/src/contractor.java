public class contractor
{
		protected String password;
	 	protected String email;
	    protected String firstName;
	    protected String lastName;
	    protected int clientID;
	    
	   /* public enum UserRole{
	    	Client, Contractor, Admin
	    }
	    
	    private UserRole role;
	    */
	    //constructors
	    public contractor() {
	    }
	 
	    public contractor(int clientID) 
	    {
	        this.clientID = clientID;
	    }
	    
	    public contractor(int clientID, String email,String firstName, String lastName, String password) 
	    {
	    	this(email, firstName,lastName,password);
	    	this.clientID = clientID;
	    }
	 
	
	    public contractor(String email, String firstName, String lastName, String password) 
	    {
	    	this.email = email;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
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

	}