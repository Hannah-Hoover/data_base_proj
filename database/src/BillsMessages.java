
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
    public BillsMessages(int quotemsgID){
		  this.billmsgID = billmsgID;
    }
    public BillsMessages(int billmsgID, int userID,int billID, double price, String schedulestart, String scheduleend, String note){
    this(userID, billID, price, schedulestart, scheduleend, note);
	   this.billmsgID = billmsgID; 
    }
    public BillsMessages(int userID, int billID, double price, String scheudlestart, String scheudleend, String note)
	{
    	this.userID = userID;
    	this.billID = billID;
    	this.price= price;
    	this.schedulestart=scheudlestart;
    	this.scheduleend=scheudleend;
    	this.note=note;
    }
    
   //getter and setter methods
    public int getBillmsgID() {
        return billmsgID;
    }
    public void setBillmsgID(int billmsgID) {
        this.billmsgID = billmsgID;
    }
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public int getBillID() {
        return billID;
    }
    public void setBillID(int billID) {
        this.billID = billID;
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
   
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    

}


