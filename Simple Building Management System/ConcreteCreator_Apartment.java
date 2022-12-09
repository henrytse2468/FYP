import java.util.*;

public class ConcreteCreator_Apartment extends BuildingFactory{
    //public static Scanner sc = new Scanner(System.in);

    //String building_type;

    public ConcreteCreator_Apartment(){
    }

    public Building FactoryMethod(){
        try{
            System.out.print("id:");
	        int id = Integer.parseInt(Main.sc.nextLine());
          
            System.out.print("Enter monthlyRental:");
	        double monthlyRental = Double.parseDouble(Main.sc.nextLine());
            System.out.print("Enter SupportStaff:");
	        String supportStaff = Main.sc.nextLine();

            System.out.print("Enter noOfRooms:");
	        int noOfRooms = Integer.parseInt(Main.sc.nextLine());

            //addRoom
            Building b = new Apartment(id, noOfRooms, monthlyRental, supportStaff);
            for(int i = 0; i < noOfRooms; i++){
                System.out.print("Enter Length: ");
	            Double length = Double.parseDouble(Main.sc.nextLine());

                System.out.print("Enter Width: ");
	            Double width = Double.parseDouble(Main.sc.nextLine());
                b.addRoom(length, width);
            }
            
            return b;
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;
    }
}