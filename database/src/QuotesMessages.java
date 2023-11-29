
public class QuotesMessages {
		protected int quotemsgID;
		protected int userID;
		protected int quoteID;
		protected String msgtime;
		protected double price;
		protected String schedulestart;
		protected String scheduleend;
		protected String note;
		
		
	 
	    //constructors
	    public QuotesMessages() {
	    }    

	    public QuotesMessages(int quotemsgID, int userID, int quoteID, String msgtime, double price, String scheudlestart, String scheudleend, String note)
	    {
	    	System.out.println("QuotesMessages.java");
	    	this.quotemsgID = quotemsgID;
	    	this.userID = userID;
	    	this.quoteID = quoteID;
	    	this.msgtime = msgtime;
	    	this.price= price;
	    	this.schedulestart=scheudlestart;
	    	this.scheduleend=scheudleend;
	    	this.note=note;
	    }
	    
	   //getter and setter methods
	    public int getquotemsgID() {
	        return quotemsgID;
	    }
	    public void setquotemsgID(int quotemsgID) {
	        this.quotemsgID = quotemsgID;
	    }
	    
	    public int getuserID() {
	        return userID;
	    }
	    public void setuserID(int userID) {
	        this.userID = userID;
	    }
	    
	    public int getquoteID() {
	        return quoteID;
	    }
	    public void setquoteID(int quoteID) {
	        this.quoteID = quoteID;
	    }
	    
	    public String getmsgtime() {
	        return msgtime;
	    }
	    public void setmsgtime(String msgtime) {
	        this.msgtime = msgtime;
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
