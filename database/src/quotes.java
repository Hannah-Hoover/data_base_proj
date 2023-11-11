public class quotes{
	protected int clientID;
	protected double price;
	protected String timeFrame;
	protected String status;
	protected int requestID;
	
	
 
    //constructors
    public quotes() {
    }
 
    public quotes(int clientID) 
    {
        this.clientID = clientID;
    }
    

    public quotes(double price, String timeFrame, String status, int requestID, int clientID)
    {
    	this.price = price;
    	this.timeFrame = timeFrame;
    	this.status = status;
    	this.clientID= clientID;
    	this.requestID=requestID;
    }
    
   //getter and setter methods
    public int getClientID() {
        return clientID;
    }
    public void setClientID(int clientID) {
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
    public int getRequestID() {
        return requestID;
    }
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }
}
