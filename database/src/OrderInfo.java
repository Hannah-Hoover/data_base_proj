
public class OrderInfo {
	protected int orderID;
	protected int quoteID;
	protected double price;
	protected String schedulestart;
	protected String scheduleend;
	protected String status;

    //constructors
    public OrderInfo() {
    }  
	
    public OrderInfo(int orderID) {
	    this.orderID = orderID;
    } 
    public OrderInfo(int orderID, int quoteID, double price, String schedulestart,String scheduleend, String status){
	this(quoteID, price, schedulestart, scheduleend, status);
            this.orderID = orderID; 
    }
    public OrderInfo(int quoteID, double price, String schedulestart, String scheduleend, String status)
    {
    	System.out.println("OrderInfo.java");
    	this.orderID = orderID;
    	this.quoteID = quoteID;
    	this.price = price;
    	this.schedulestart= schedulestart;
    	this.scheduleend= scheduleend;
    	this.status= status;
    }
    
   //getter and setter methods
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getQuoteID() {
        return quoteID;
    }
    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getSchedulestart() {
        return schedulestart;
    }
    public void setSchedulestart(String schedulestart) {
        this.schedulestart = schedulestart;
    }
    public String getScheduleend() {
        return scheduleend;
    }
    public void setScheduleend(String scheduleend) {
        this.scheduleend = scheduleend;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
