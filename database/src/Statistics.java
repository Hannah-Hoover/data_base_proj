import java.sql.Timestamp;

 class Statistics {
	 private int clientID;
	    private String firstName;
	    private String lastName;
	    private int totalTrees;
	    private double totalDueAmount;
	    private double totalPaidAmount;
	    private Timestamp workCompletionDate;
	    
	    public Statistics(){
	    }
	
	 public int getClientID() {
	        return clientID;
	    }

	    public void setClientID(int clientID) {
	        this.clientID = clientID;
	    }


}
