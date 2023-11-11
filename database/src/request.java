public class request 
{		protected int requestID;
		protected int clientID;
		protected String location;
	 	protected String height;
	    protected String proximity;
	    protected String sizeDiameter;
	    protected String photodata1;
	    protected String photodata2;
	    protected String photodata3;
	    protected String note;
	
	 
	    //constructors
	    public request() {
	    }
	 
	    public request(int requestID){
	  
	        this.requestID = requestID;
	    }
	    
	    public request(int requestID,int clientID, String location, String height, String proximity, String sizeDiameter,String photodata1, String photodata2, String photodata3, String note) 
	    {
	    	this(clientID, location, height, proximity, sizeDiameter, photodata1, photodata2, photodata3, note); 
	    	this.requestID = requestID;
	    }
	 
	
	    public request(int clientID, String location, String height, String proximity,String sizeDiameter, String photodata1, String photodata2, String photodata3, String note) 
	    {
	    	this.clientID = clientID;
	    	this.location = location;
	    	this.height = height;
	    	this.proximity = proximity;
	        this.sizeDiameter = sizeDiameter;
	        this.photodata1 = photodata1;
	        this.photodata2 = photodata2;
	        this.photodata3= photodata3;
	        this.note = note;
	    }
	    
	   //getter and setter methods
	    public int getRequestID() {
	        return requestID;
	    }
	    public void setRequestID(int requestID) {
	        this.requestID = requestID;
	    }
	    public int getClientID() {
	        return clientID;
	    }
	    public void setClientID(int clientID) {
	        this.clientID = clientID;
	    }
	    
	    
	    public String getLocation() {
	        return location;
	    }
	    public void setLocation(String location) {
	        this.location = location;
	    }
	    
	    public String getHeight() {
	        return height;
	    }
	    public void setHeight(String height) {
	        this.height = height;
	    }
	    
	    public String getProximity() {
	        return proximity;
	    }
	    public void setProximity(String proximity) {
	        this.proximity = proximity;
	    }
	  
	    public String getSizeDiameter() {
	    	return sizeDiameter;
	    }
	    public void setSizeDiameter(String sizeDiameter) {
	    	this.sizeDiameter = sizeDiameter;
	    }
	    
	    public String getPhotodata1() {
	        return photodata1;
	    }
	    public void setPhotodata1(String photodata1) {
	        this.photodata1 = photodata1;
	    }
	    public String getPhotodata2() {
	        return photodata2;
	    }
	    public void setPhotodata2(String photodata2) {
	        this.photodata2 = photodata2;
	    }
	    public String getPhotodata3() {
	        return photodata3;
	    }
	    public void setPhotodata3(String photodata3) {
	        this.photodata3 = photodata3;
	    }
	    public String getNote() {
	        return note;
	    }
	    public void setNote(String note) {
	        this.note = note;
	    }
	    
	    @Override
	    public String toString() {
	    	return String.format("Request(clientID=%s)", this.clientID);
	    }
	}
