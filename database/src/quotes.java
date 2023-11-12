public class quotes{
	protected int quoteID;
	protected int clientID;
	protected double price;
	protected String timeFrame;
	protected String status;
	protected int requestID;
	protected String note;
	
	
 
    //constructors
    public quotes() {
    }    

    public quotes(double price, String timeFrame, String status, int requestID, int clientID, String note)
    {
    	System.out.println("quote.java");
    	this.price = price;
    	this.timeFrame = timeFrame;
    	this.status = status;
    	this.clientID= clientID;
    	this.requestID=requestID;
    	this.note=note;
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
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getQuoteID() {
    	return this.quoteID;
    }
    public void setQuoteID(int quoteID) {
    	this.quoteID=quoteID;
    }
    public boolean isEditable() {
    	return !("quit".equals(this.status) || "agree".equals(this.status));
    }
}
