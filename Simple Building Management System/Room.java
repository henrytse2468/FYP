public class Room{
    private double length;
    private double width;
    public Room(double length, double width){
        this.length = length;
        this.width = width;
    }
    public void setLenght(double length){
        this.length = length;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public double getLenght(){
        return length;
    }
    public double getWidth(){
        return width;
    }
    public String toString(){
        return "Room length: " + length + ", Room width: " + width;
    }
}