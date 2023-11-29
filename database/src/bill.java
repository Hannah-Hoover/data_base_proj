public class bill{
  protected int billID;
  protected int orderID;
  protected double price;
  protected double discount;
  protected double balance;
  protected String status;

    //constructors
    public bill() {
    }    

    public bill(int billID){
	    this.billID = billID;
    }
    public bill(int billID, int orderID, double price, double discount, double balance, String status){
	    this(orderID, price, discount, balance, status);
    	this.billID = billID; 
    }

    public bill(int orderID, double price, double discount, double balance, String status)
    {
	    this.orderID = orderID;
    	this.price = price;
      this.discount = discount;
      this.balance = balance;
    	this.status= status;
    }
    
   //getter and setter methods
    public int getBillID() {
        return billID;
    }
    public void setBillID(int billID) {
        this.billID = billID;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
  
}
