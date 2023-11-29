public class tree{
	protected int treeID;
	protected int quoteID;
	protected String location;
	protected String height;
	protected String proximity;
	protected String diameter;
	protected String photo1;
	protected String photo2;
	protected String photo3;
	
	
    //constructors
    public tree() {
    }
    public tree(int treeID) 
    {
        this.treeID = treeID;
    }
    
    public tree(int treeID, int quoteID, String location, String height, String proximity, String diameter, String photo1, String photo2, String photo3){
    	this(quoteID, location, height, proximity, diameter, photo1, photo2, photo3);
    	this.treeID = treeID; 
    }

    public quote(quoteID, location, height, proximity, diameter, photo1, photo2, photo3)
    {
    	this.quoteID = quoteID;
    	this.location = location;
     	this.height = height;
      	this.proximity = proximity;
        this.diameter = diameter;
	this.photo1 = photo1;
 	this.photo2 = photo2;
  	this.photo3 = photo3;
    }
    
   //getter and setter methods
    public int getTreeID() {
        return treeID;
    }
    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }

    public int getQuoteID() {
        return quoteID;
    }
    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
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
    public String getDiameter() {
        return diameter;
    }
    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }
    public String getPhoto1() {
        return photo1;
    }
    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
    public String getPhoto2() {
        return photo2;
    }
    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }
    public String getPhoto3() {
        return photo3;
    }
    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }
}
    
