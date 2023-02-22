import java.util.*;

public class Factory_modifyBuilding extends CommandFactory {
    //public static Scanner sc = new Scanner(System.in);
    Building building;
    Vector _building;
    //int building_id;
    //BuildingFactory bf;
    //String building_type;
    int index;

    public Factory_modifyBuilding(){
        _building = null;
    }
    public void setBuilding(Vector building){
        _building = building;
    }
    public Command FactoryMethod(){
        try{
	        System.out.print("Enter Building Id:");
            int id = Integer.parseInt(Main.sc.nextLine());
            for (int i = 0; i < _building.size(); i++){
                Building B = (Building)_building.get(i);
                if (B.getId()== id){
                    System.out.println(B.toString());
                    //this.building_id = id;
                    this.building = B;
                    this.index = i;
                }
            }
            Command c = new modifyBuildingCommand(_building, building, index);
            return c;
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;
    }
    
}
