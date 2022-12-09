import java.util.*;
import MementoPattern.Originator;
import MementoPattern.Memento;

public class modifyRoomCommand implements Command{
    Room oldRoom;
    Room newRoom;
    Vector _building;
    int building_id;
    int roomIndex;
    int buildingIndex;
    double length;
    double width;
    Originator originator;
    Memento m0;

    public modifyRoomCommand(Vector building, int building_id, int roomIndex, double length, double width){
        this._building = building;
        this.building_id = building_id;
        this.roomIndex = roomIndex;
        this.length = length;
        this.width = width;
    }

    public void execute(){
        for (int i = 0; i < _building.size(); i++){
            Building B = (Building)_building.get(i);
            if (B.getId()== building_id){
                buildingIndex = i;
                oldRoom = new Room(B.getRooms().get(roomIndex).getLenght(),B.getRooms().get(roomIndex).getWidth());
                newRoom = new Room(length, width);
 

                originator = new Originator(oldRoom);
                m0= new Memento(originator); 

                B.getRooms().set(roomIndex, newRoom);
                _building.set(i, B);
                System.out.println("Room Updated");
                B.printBuilding();
            }

        }

    }
    public void undo(){

        Room oldRoom2 =(Room)m0.restore();
        Building B = (Building)_building.get(buildingIndex);
        B.getRooms().set(roomIndex, oldRoom2);
        _building.set(buildingIndex, B);
        System.out.println("Room update undo");
        B.printBuilding();

    }
    public String toString(){
        return "Modify Room: Building No.:"+ building_id +", Room No." + (roomIndex+1) +", "+ newRoom.toString();
    }
}