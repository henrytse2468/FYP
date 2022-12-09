import java.util.*;
public class delRoomCommand implements Command{
    Room room;
    Vector _building;
    int building_id;
    int roomIndex;
    Building building;
    //ArrayList Rooms= new ArrayList<>();

    public delRoomCommand(Vector building, int building_id, int roomIndex){
        this._building = building;
        this.building_id = building_id;
        this.roomIndex = roomIndex;
        for (int i = 0; i < _building.size(); i++){
            Building B = (Building)_building.get(i);
            if (B.getId()== building_id){
                this.building = B;
            }
        }
    }

    public void execute(){
            if(room == null){
                room = building.getRooms().get(roomIndex);
                building.deleteRoom(roomIndex);
            }else{
                building.getRooms().remove(room);
            }
            //_building.set(i, B);
            System.out.println("Room deleted");
            building.printBuilding();


    }
    public void undo(){
        building.getRooms().add(roomIndex, room);
        System.out.println("Room delete undo");
        building.printBuilding();
        /*if(room != null){
            for (int i = 0; i < _building.size(); i++){
                Building B = (Building)_building.get(i);
                if (B.getId()== building_id){
                    System.out.println("Room delete undo");
                    B.printBuilding();
                }
            }
        }*/
    }
    public String toString(){
        return "Delete Room: " + building_id +", Room No." + (roomIndex+1) +", "+ room.toString();
    }
}
