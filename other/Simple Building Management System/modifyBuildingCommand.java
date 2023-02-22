import java.util.*;
import MementoPattern.Originator;
import MementoPattern.Memento;

public class modifyBuildingCommand implements Command{
    Vector _building;
    Building building;
    int building_id;
    int index;
    Building oldBuilding;
    Originator originator;
    Memento m0;
    

    public modifyBuildingCommand(Vector building, Building b, int index){
        this._building = building;
        this.building = b;
        building_id = b.getId();
        this.index = index;
    }
    public void execute(){
            

        originator = new Originator(oldBuilding);
        m0= new Memento(originator); 
        
        building.modifyBuilding();

        if (building.getClass().getSimpleName().matches("Apartment")){
            this.oldBuilding = new Apartment(((Building) _building.get(index)).getId(), ((Building) _building.get(index)).getRoomQty(), ((Apartment) _building.get(index)).getMonthlyRental(), ((Apartment) _building.get(index)).getSupportStaff());
            //System.out.println(oldBuilding.toString());
        } else if (building.getClass().getSimpleName().matches("House")){
            this.oldBuilding = new House(((Building) _building.get(index)).getId(), ((Building) _building.get(index)).getRoomQty(), ((House) _building.get(index)).getFloors());
            //System.out.println(oldBuilding.toString());
        }else{
            System.out.println("Wrong Type");
        }

        _building.set(index, building);
        System.out.println("Building is modified:");
        System.out.println(_building.get(index).toString());
    }
    public void undo(){
        Building oldBuilding2 = (Building)m0.restore();
        _building.set(index, oldBuilding2);
        System.out.println("Building is un-modified:");
        //System.out.println(_building.get(index).toString());
    }
    public String toString(){
        return "Modify Building: " + oldBuilding.toString();
    }
    
    
}
