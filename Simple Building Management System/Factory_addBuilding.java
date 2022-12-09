import java.util.*;

public class Factory_addBuilding extends CommandFactory{
    //public static Scanner sc = new Scanner(System.in);
    Building building;
    Vector _building;
    String building_type;
    BuildingFactory bf;
    

    public Factory_addBuilding(){
        this._building = null;
    }

    public void setBuilding(Vector building){
        _building = building;
    }
    public Command FactoryMethod(){
        System.out.print("Building Type  (a=Apartment/h=House):");
	    String building_type = Main.sc.nextLine();
        try{
            if(building_type.matches("a")){
                bf = new ConcreteCreator_Apartment();

            }else if(building_type.matches("h")){
                bf = new ConcreteCreator_House();
            }
            this.building = bf.FactoryMethod();
            Command c = new addBuildingCommand(_building, building);
            return c;
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;
    }
}