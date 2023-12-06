
public class OrderInfo {
	protected int orderID;
	protected int quoteID;
	protected double price;
	protected String schedulestart;
	protected String scheduleend;

    //constructors
    public OrderInfo() {
    }  
	
    public OrderInfo(int orderID) {
	    this.orderID = orderID;
    } 
    public OrderInfo(int orderID, int quoteID, double price, String schedulestart,String scheduleend){
	this(quoteID, price, schedulestart, scheduleend);
            this.orderID = orderID; 
    }
    public OrderInfo(int quoteID, double price, String schedulestart, String scheduleend)
    {
    	System.out.println("OrderInfo.java");
    	this.orderID = orderID;
    	this.quoteID = quoteID;
    	this.price = price;
    	this.schedulestart= schedulestart;
    	this.scheduleend= scheduleend;
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
    


}
