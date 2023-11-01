
public class contractor 
{
	protected String password;
 	protected String email;
    protected String firstName;
    protected String lastName;
   
 
    //constructors
    public contractor() {
    }
 
    public contractor(String email) 
    {
        this.email = email;
    }
    
    public contractor(String email,String firstName, String lastName, String password) 
    {
    	this(firstName,lastName,password);
    	this.email = email;
    }
 
    public contractor(String firstName, String lastName, String password) 
    {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.password = password;
    }
    
   //getter and setter methods
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