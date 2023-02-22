import java.util.*;

public class addBuildingCommand implements Command{
    Building b;
    Building ob;
    Vector _building;

    public addBuildingCommand(Vector building, Building b){
        this._building = building;
        this.b = b;
    }

    public void execute(){
            if (b.getClass().getSimpleName().matches("Apartment")){
                ob = new Apartment(((Building) b).getId(), ((Building) b).getRoomQty(), ((Apartment) b).getMonthlyRental(), ((Apartment)b).getSupportStaff());
            } else if (b.getClass().getSimpleName().matches("House")){
                ob = new House(((Building) b).getId(), ((Building) b).getRoomQty(), ((House) b).getFloors());
            }else{
                System.out.println("Wrong Type");
            }
        _building.add(b);
        System.out.println("New Building Added");
        b.printBuilding();
    }
    public void undo(){
        _building.remove(b);
        System.out.println("New Building Removed");
    }
    public String toString(){
        return "Add Building: " + ob.toString();
    }
}
