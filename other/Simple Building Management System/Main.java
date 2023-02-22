import java.util.*;


public class Main{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){

        
        Vector _building = new Vector();
		Vector _history = new Vector();
        Vector _undoHistory = new Vector();
        boolean cont = true;
        CommandFactory[] _facs;
        BuildingFactory[] _bFacs;
        Command com;
        
        String[] _factory = {"Factory_addBuilding", "Factory_Print" ,"Factory_addRoom", "Factory_delRoom", "Factory_modifyRoom", "Factory_modifyBuilding"};
        _facs = new CommandFactory[_factory.length];
        try{
            for (int i = 0; i < _facs.length; i++) {
				_facs[i] = (CommandFactory) Class.forName(_factory[i]).newInstance();
				_facs[i].setBuilding(_building);
			}
            while (cont) {
                int index = 0;
                System.out.println("Building Management System (BMS)");
                System.out.println("Please enter command: [a|d|m|e|u|r|l|x]");
                System.out.println("a = add building, d = display buildings, m = modify building, e = edit rooms"+
                                    "\nu = undo, r = redo, l = list undo/redo, x = exit system");

                //System.out.println("Enter command: 0 = exit, 1 = undo, 2 = redo, 3 = print building,  4 = build Apartment, 5 = build House, 6 = addRoom");
                
                String command = sc.nextLine();
                if (command.matches("x")||command.matches("X")){
                        cont = false;
                        break;
                }
                else if (command.matches("u")) {
                    if (_history.size() > 0) {
                        com = (Command) _history.remove(_history.size()-1);
                        _undoHistory.add(com);
                        com.undo();
                        continue;
                    }
                }
                else if (command.matches("r")){
                    if (_undoHistory.size()>0){
                        com = (Command) _undoHistory.remove(_undoHistory.size()-1);
                        _history.add(com);
                        com.execute();
                        continue;
                    }
                }
                else if (command.matches("a")){
                    //new Factory_Building(_building, command);
                    index = 0;
                    
                }else if (command.matches("d")){
                    index = 1;
                }else if (command.matches("e")){
                    System.out.print("Building id:");
                    command = sc.nextLine();
                    int id = Integer.parseInt(command);
                    for (int i = 0; i < _building.size(); i++){
                        Building edit_B = (Building)_building.get(i);
                        if (edit_B.getId()== id){
                            edit_B.printBuilding();
                        }
                    }
                            
                    System.out.println("Please enter command: [a|d|m]");
                    System.out.println("a = add room, d = delete room, m = modify room");

                    command = sc.nextLine();
                    switch (command){
                        case "a":
                            index = 2;
                            ((Factory_addRoom) _facs[index]).setID(id);
                            
                            break;
                        case "d":
                            index = 3;
                            ((Factory_delRoom) _facs[index]).setID(id);
                            break;
                        case "m":
                            index = 4;
                            ((Factory_modifyRoom) _facs[index]).setID(id);
                            break;
                        default:
                            System.out.println(command);
                            System.out.print("wrong input");
                            continue;
                    }
                }else if(command.matches("m")){
                    index = 5;
                }else if(command.matches("l")){
                    if (_history.size() == 0){
                        System.out.println("Undo List:");
                        System.out.println("Nothing to Undo");
                    }else{
                        System.out.println("Undo List:");
                        for(int i = _history.size()-1 ; i >= 0; i--){
                            System.out.println(_history.get(i).toString());
                        }
                    }
                    if (_undoHistory.size() == 0){
                        System.out.println("Redo List:");
                        System.out.println("Nothing to Redo");
                    }else{
                        System.out.println("Redo List:");
                        for(int i = _undoHistory.size()-1 ; i >= 0; i--){
                            System.out.println(_undoHistory.get(i).toString());
                        }
                    }
                    continue;
                }
                else{
                    System.out.println("wrong input");
                    continue; 
                }
                com = _facs[index].FactoryMethod();
                if (!(com instanceof printCommand))
                    _history.add(com);
                com.execute();
                }

            }
            
        catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}