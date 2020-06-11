
public class CustomerDetailsNew {
	private String courseName;
	private String purchaseDate;
	private int Amount;
	private String Location;
	private String studentName;

	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	public String getCourseName(){
		return courseName;
	}
	public void setPurchaseDate(String purchaseDate){
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseDate(){
		return purchaseDate;
	}
	public void setAmount(int Amount){
		this.Amount = Amount;
	}
	public int getAmount(){
		return Amount;
	}
	public void setLocation(String Location){
		this.Location = Location;
	}	
	public String getLocation(){
		return Location;
	}
	
}
