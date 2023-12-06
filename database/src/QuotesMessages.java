
public class QuotesMessages {
		protected int quotemsgID;
		protected int userID;
		protected int quoteID;
		protected String msgtime;
		protected double price;
		protected String schedulestart;
		protected String scheduleend;
		protected String status;
		protected String note;
		
		
	 
	    //constructors
	    public QuotesMessages() {
	    }    
	    public QuotesMessages(int quotemsgID){
		  this.quotemsgID = quotemsgID;
	    }
	    public QuotesMessages(int quotemsgID, int userID, int quoteID, String msgtime, double price, String schedulestart, String scheduleend, String status, String note){
	    this(userID, quoteID, msgtime, price, schedulestart, scheduleend, status, note);
    	     this.quotemsgID = quotemsgID; 
    	}
	    public QuotesMessages(int userID, int quoteID, String msgtime, double price, String scheudlestart, String scheudleend, String status, String note)
	    {
	    	this.userID = userID;
	    	this.quoteID = quoteID;
	    	this.msgtime = msgtime;
	    	this.price = price;
	    	this.schedulestart= scheudlestart;
	    	this.scheduleend= scheudleend;
	    	this.status= status;
	    	this.note= note;
	    }
	    
	   //getter and setter methods
	    public int getQuotemsgID() {
	        return quotemsgID;
	    }
	    public void setQuotemsgID(int quotemsgID) {
	        this.quotemsgID = quotemsgID;
	    }
	    
	    public int getUserID() {
	        return userID;
	    }
	    public void setUserID(int userID) {
	        this.userID = userID;
	    }
	    
	    public int getQuoteID() {
	        return quoteID;
	    }
	    public void setQuoteID(int quoteID) {
	        this.quoteID = quoteID;
	    }
	    
	    public String getMsgtime() {
	        return msgtime;
	    }
	    public void setMsgtime(String msgtime) {
	        this.msgtime = msgtime;
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
	   
	    public String getNote() {
	        return note;
	    }
	    public void setNote(String note) {
	        this.note = note;
	    }
	    


}
