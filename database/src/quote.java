public class quote{
	protected int quoteID;
	protected int contractorID;
	protected int clientID;
	protected double price;
	protected String startTime;
	protected String endTime;
	protected String status;	
 
    //constructors
    public quote() {
    }    

    public quote(int quoteID){
	    this.quoteID = quoteID;
    }
    public quote(int quoteID, int contractorID, int clientID, double price, String startTime, String endTime, String status){
	this(contractorID, clientID, price, startTime, endTime, status);
    	this.quoteID = quoteID; 
    }

    public quote(int contractorID, int clientID, double price, String startTime, String endTime, String status)
    {
		this.contractorID = contractorID;
    	this.clientID = clientID;
    	this.price = price;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.status= status;
    }
    
   //getter and setter methods
    public int getQuoteID() {
        return quoteID;
    }
    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }
    public int getContractorID() {
        return contractorID;
    }
    public void setContractorID(int contractorID) {
        this.contractorID = contractorID;
    }
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
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
