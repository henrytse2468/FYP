import java.util.*;
public class Apartment extends Building{
    private double monthlyRental;
    private String supportStaff;
    //public static Scanner sc = new Scanner(System.in);

    public Apartment(int id, int noOfRooms, double monthlyRental, String supportStaff){
        super(id, noOfRooms);
        this.monthlyRental = monthlyRental;
        this.supportStaff = supportStaff;
    }
    public void setMonthlyRental(double monthlyRental){
        this.monthlyRental = monthlyRental;
    }
    public double getMonthlyRental(){
        return monthlyRental;
    }
    public void setSupportStaff(String supportStaff){
        this.supportStaff = supportStaff;
    }
    public String getSupportStaff(){
        return supportStaff;
    }
    public void modifyBuilding(){
        System.out.print("Modify Monthly Rental: ");
        this.setMonthlyRental(Double.parseDouble(Main.sc.nextLine()));
        System.out.print("Modify Support Staff: ");
        this.setSupportStaff(Main.sc.nextLine());
    }
    public void printBuilding(){
        System.out.print("Building id: " + getId() + "\nNo of Rooms: "+
                            getRoomQty() + "\nMonthlyRental: "+
                            getMonthlyRental() + "\nSupport Staff: "+
                            getSupportStaff()+ "\n");
                            //Room No.: 1, Length: 7.0, Width: 10.0
        for(int i = 0; i < getRoomQty(); i++){
            System.out.println("Room No.:" + (i+1) + ", Length: "+ getRooms().get(i).getLenght() + ", Width: "+ getRooms().get(i).getWidth());
        }
        
    }
    public String toString(){
        return "Building id: " + getId()+ ", MonthlyRental: "+
        getMonthlyRental() + ", Support Staff: "+
        getSupportStaff();
    }
    
}