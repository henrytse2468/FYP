import java.util.*;


public class printCommand implements Command{
    public Vector _building;
    public int id;

    public printCommand(Vector building){
        _building = building;
        this.id = -10;
    }

    public printCommand(Vector building, int id){
        _building = building;
        this.id = id;
    }
    public void execute(){
        if (id == -10){
            for (int i = 0; i < _building.size(); i++)
			    ( (Building) _building.elementAt(i)).printBuilding();
        }else{
            for (int i = 0; i < _building.size(); i++){
                Building B = (Building)_building.get(i);
                if (B.getId() == id){
                    B.printBuilding();
                }
            }
        }

    }
    public void undo(){

    }

    
}
