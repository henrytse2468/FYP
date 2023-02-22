import java.util.*;

public class Factory_Print extends CommandFactory {
    //public static Scanner sc = new Scanner(System.in);
    
    Vector _building;
    int id;
    //String star = "*";

    public Factory_Print(){
        _building = null;
    }
    public void setBuilding(Vector building) {
        _building = building;
    }
    public Command FactoryMethod(){
        try{
            System.out.print("id:");
	        String line = Main.sc.nextLine();
            //System.out.println(line);

            if(line.matches("\\*")){
                Command c = new printCommand(_building);
                return c;
            }else{
	            id = Integer.parseInt(line);
                Command c = new printCommand(_building, id);
                return c;
            }
        }catch(Exception e){
			e.printStackTrace();
		}
        return null;
    }
}
