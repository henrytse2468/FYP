import java.util.ArrayList;

public abstract class Building{
    private int id;
    private ArrayList<Room> Rooms;

    public Building(int id, int noOfRooms){
        this.id = id;
        this.Rooms = new ArrayList<>(noOfRooms);
    };
    public int getId(){
        return id;
    };

    /*public void setRooms(ArrayList Rooms){
        this.Rooms = Rooms;
    };*/
    public ArrayList<Room> getRooms(){
        return Rooms;
    };
    public Room addRoom(double length, double width){
        //int noOfRooms = Rooms.size() +1;
        //Rooms.ensureCapacity(noOfRooms);
        Room rm = new Room(length, width);
        Rooms.add(rm);
        return rm;
    }
    public void modifyRoom(int roomNo, double length, double width){
        Room rm = new Room(length, width);
        Rooms.set(roomNo, rm);
    };
    public void deleteRoom(int roomNo){
            Rooms.remove(roomNo);
        };
    public void printRoom(){
        for (int i = 0; i<Rooms.size(); i++){
            Room rm = Rooms.get(i);
            System.out.print(rm);
        }
        };
    public int getRoomQty(){
        return Rooms.size();
    };
    public abstract void modifyBuilding();
    public abstract void printBuilding();
}