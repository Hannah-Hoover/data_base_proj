
public class BillsMessages {
	protected int billmsgID;
	protected int userID;
	protected int billID;
	protected double price;
	protected String schedulestart;
	protected String scheduleend;
	protected String note;
	
	
 
    //constructors
    public BillsMessages() {
    }    

    public BillsMessages(int billmsgID, int userID, int billID, double price, String scheudlestart, String scheudleend, String note)
    {
    	System.out.println("BillsMessages.java");
    	this.billmsgID = billmsgID;
    	this.userID = userID;
    	this.billID = billID;
    	this.price= price;
    	this.schedulestart=scheudlestart;
    	this.scheduleend=scheudleend;
    	this.note=note;
    }
    
   //getter and setter methods
    public int getbillmsgID() {
        return billmsgID;
    }
    public void setbillmsgID(int billmsgID) {
        this.billmsgID = billmsgID;
    }
    
    public int getuserID() {
        return userID;
    }
    public void setuserID(int userID) {
        this.userID = userID;
    }
    
    public int getbillID() {
        return billID;
    }
    public void setbillID(int billID) {
        this.billID = billID;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
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
   
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    

}


