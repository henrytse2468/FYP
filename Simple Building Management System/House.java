import java.util.*;
public class House extends Building{
    //public static Scanner sc = new Scanner(System.in);
    private int noOfFloors;
    public House(int id, int noOfRooms, int noOfFloors){
        super(id, noOfRooms);
        this.noOfFloors = noOfFloors;

    }
    public void setFloors(int noOfFloors){
        this.noOfFloors = noOfFloors;
    }
    public int getFloors(){
        return noOfFloors;
    }
    public void modifyBuilding(){
        System.out.print("Modify no of Floors");
        this.setFloors(Integer.parseInt(Main.sc.nextLine()));
    }
    public void printBuilding(){
        System.out.println("id: " + getId() + "\nNo of Rooms: "+
        getRoomQty() + "\nNo of Floor: "+
        getFloors()+ "\n");
        for(int i = 0; i < getRoomQty(); i++){
            System.out.println("Room No.:" + (i+1) + ", Length: "+ getRooms().get(i).getLenght() + ", Width: "+ getRooms().get(i).getWidth());
        }
    }
    public String toString(){
        return "Building id: " + getId() + ", No of Floor: "+ getFloors();
    }
}