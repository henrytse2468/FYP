import java.util.*;


public class Factory_addRoom extends CommandFactory{
    //public static Scanner sc = new Scanner(System.in);
    Vector _building;
    int id;

    public Factory_addRoom(){
        _building = null;
    }
    public void setBuilding(Vector building){
        _building = building;
    }
    public void setID(int id){
        this.id = id;
    }
    public Command FactoryMethod(){
        try{
	        System.out.print("Enter length:");
            double length =  Double.parseDouble(Main.sc.nextLine());
            
            System.out.print("Enter width:");
	        double width =  Double.parseDouble(Main.sc.nextLine());

            Command c = new addRoomCommand(_building, id, length, width);
            return c;
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;
    }
    
}
