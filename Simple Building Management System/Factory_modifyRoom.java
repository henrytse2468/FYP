import java.util.*;
public class Factory_modifyRoom extends CommandFactory{
    //public static Scanner sc = new Scanner(System.in);
    Vector _building;
    int id;

    public Factory_modifyRoom(){
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
	        System.out.print("Enter Room No.:");
            int roomIndex= Integer.parseInt(Main.sc.nextLine())-1;
            System.out.print("Enter Room Length:");
            Double length =  Double.parseDouble(Main.sc.nextLine());
            System.out.print("Enter Room Width:");
            Double width=  Double.parseDouble(Main.sc.nextLine());
            

            Command c = new modifyRoomCommand(_building, id, roomIndex, length, width);
            return c;
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;
    }
}
