
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
    public bill(int billID, int quoteID, double price, String schedulestart,String scheduleend){
	    this(quoteID, price, discount, balance, status);
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
    public int getorderID() {
        return orderID;
    }
    public void setorderID(int orderID) {
        this.orderID = orderID;
    }
    public int getquoteID() {
        return quoteID;
    }
    public void setquoteID(int quoteID) {
        this.quoteID = quoteID;
    }
    public double getprice() {
        return price;
    }
    public void setprice(double price) {
        this.price = price;
    }
    public String getschedulestart() {
        return schedulestart;
    }
    public void setschedulestart(String schedulestart) {
        this.schedulestart = schedulestart;
    }
    public String getscheduleend() {
        return scheduleend;
    }
    public void setscheduleend(String scheduleend) {
        this.scheduleend = scheduleend;
    }
    


}
