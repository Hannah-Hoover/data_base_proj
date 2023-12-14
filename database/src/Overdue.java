public class Overdue {
    private int billID;
    
    
    public Overdue(int billID) {
        this.billID = billID;
       
    }
    public int getClientID() {
        return billID;
    }

    public void setClientID(int billID) {
        this.billID = billID;
    }
}
