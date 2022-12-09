import java.util.*;

public class Factory_delRoom extends CommandFactory{
    //public static Scanner sc = new Scanner(System.in);
    Vector _building;
    int id;

    public Factory_delRoom(){
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
            int roomIndex=  Integer.parseInt(Main.sc.nextLine())-1;

            Command c = new delRoomCommand(_building, id, roomIndex);
            return c;
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;
    }
}
