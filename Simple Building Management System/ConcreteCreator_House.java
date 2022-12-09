import java.util.*;

public class ConcreteCreator_House extends BuildingFactory {
    //public static Scanner sc = new Scanner(System.in);

    public ConcreteCreator_House(){
    }

    public Building FactoryMethod(){
        try{
            System.out.print("id:");
	        int id = Integer.parseInt(Main.sc.nextLine());

	        
            
            System.out.print("Enter noOfFloors:");
	        int noOfFloors = Integer.parseInt(Main.sc.nextLine());

            System.out.print("Enter noOfRooms:");
	        int noOfRooms = Integer.parseInt(Main.sc.nextLine());

            Building b = new House(id, noOfRooms, noOfFloors);

            for(int i = 0; i < noOfRooms; i++){
                System.out.print("Enter Length:");
	            Double length =  Double.parseDouble(Main.sc.nextLine());

                System.out.print("Enter Width:");
	            Double width =  Double.parseDouble(Main.sc.nextLine());
                b.addRoom(length, width);
            }
            
            return b;
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;


    }
}
