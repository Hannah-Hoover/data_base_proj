public class quote{
	protected int clientID;
	protected double price;
	protected String timeFrame;
	protected String status;
	
 
    //constructors
    public quote() {
    }
 
    public quote(int clientID) 
    {
        this.clientID = clientID;
    }
    
    public quote(int clientID, double price, String timeFrame, String status){
    	this(price, timeFrame, status);
    	this.clientID = clientID; 
    }
 

    public quote(double price, String timeFrame, String status)
    {
    	this.price = price;
    	this.timeFrame = timeFrame;
    	this.status = status;
    }
    
   //getter and setter methods
    public int getID() {
        return clientID;
    }
    public void setID(int clientID) {
        this.clientID = clientID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getTimeFrame() {
        return timeFrame;
    }
    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}