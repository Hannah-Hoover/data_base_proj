import java.sql.Timestamp;
public class bill{
  protected int billID;
  protected int orderID;
  protected double price;
  protected double discount;
  protected double balance;
  protected String status;
  protected Timestamp current;
  protected Timestamp accepted;

    //constructors
    public bill() {
    }    

    public bill(int billID){
	    this.billID = billID;
    }
    public bill(int billID, int orderID, Timestamp current,Timestamp accepted, double price, double discount, double balance, String status){
	    this(orderID, current,accepted, price, discount, balance, status);
    	this.billID = billID; 
    }

    public bill(int orderID, Timestamp current, Timestamp accepted, double price, double discount, double balance, String status)
    {
    	this.accepted = accepted;
    	this.current = current;
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
    public Timestamp getCurrent() {
        return current;
    }
    public void setCurrent(Timestamp current) {
        this.current = current;
    }
    public Timestamp getAccepted() {
        return accepted;
    }
    public void setAccepted(Timestamp accepted) {
        this.accepted = accepted;
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
