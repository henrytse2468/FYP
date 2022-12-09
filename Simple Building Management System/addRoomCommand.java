import java.util.*;

public class addRoomCommand implements Command{
    Room room;
    Vector _building;
    int building_id;
    double length;
    double width;
    int Buildingindex;
    Building building;
    int roomIndex;

    public addRoomCommand(Vector building, int id, double length, double width){
        this._building = building;
        this.building_id = id;
        this.length = length;
        this.width = width;

    }

    public void execute(){
        room = new Room(length, width);
        for (int i = 0; i < _building.size(); i++){
            Building B = (Building)_building.get(i);
            if (B.getId()== building_id){
                building = B;
                building.addRoom(length, width);
                this.roomIndex = building.getRoomQty()-1;
                //_building.set(i, B);
                this.Buildingindex = i;
                System.out.println("Room added");
                building.printBuilding();
            }else{
                //System.out.println("Not this ID" + building_id);
            }
        }
        
    }
    public void undo(){
        building.deleteRoom(roomIndex);
        System.out.println("Room added undo");
        building.printBuilding();
        /*if(room != null){
            for (int i = 0; i < _building.size(); i++){
                Building B = (Building)_building.get(i);
                if (B.getId()== building_id){
                    B.deleteRoom(B.getRoomQty()-1);
                    _building.set(i, B);
                    System.out.println("Room added undo");
                    B.printBuilding();
                }
            }
        }*/

    }
    public String toString(){
        return "Add Room: Building No.:"+ building_id +", Room No." + (roomIndex+1) +", "+ room.toString();
    }
}
