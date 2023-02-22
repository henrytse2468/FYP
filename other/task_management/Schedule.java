import java.util.ArrayList;

public class Schedule {
    //private ArrayList<String[]> task;
    private String[][] task = 
    {
    {"08:00:00", "08:30:00", "status", "location"},
    {"08:30:00", "09:00:00", "status", "location"},
    {"09:00:00", "09:30:00", "status", "location"},
    {"09:30:00", "10:00:00", "status", "location"},
    {"10:00:00", "10:30:00", "status", "location"},
    {"10:30:00", "11:00:00", "status", "location"},
    {"11:00:00", "11:30:00", "status", "location"},
    {"11:30:00", "12:00:00", "status", "location"},
    {"12:00:00", "12:30:00", "status", "location"},
    {"12:30:00", "13:00:00", "status", "location"},
    {"13:00:00", "13:30:00", "status", "location"},
    {"13:30:00", "14:00:00", "status", "location"},
    {"14:00:00", "14:30:00", "status", "location"},
    {"14:30:00", "15:00:00", "status", "location"},
    {"15:00:00", "15:30:00", "status", "location"},
    {"15:30:00", "16:00:00", "status", "location"},
    {"16:00:00", "16:30:00", "status", "location"},
    {"16:30:00", "17:00:00", "status", "location"},
    {"17:00:00", "17:30:00", "status", "location"},
    {"17:30:00", "18:00:00", "status", "location"},
    {"18:00:00", "18:30:00", "status", "location"},
    {"18:30:00", "19:00:00", "status", "location"}
    };

    public Schedule(){
        initialize();
    }

    

    public boolean isAvailableNow(String time){
        for(int i = 0; i < task.length; i++){
            if (time.compareTo(task[i][0]) >= 0 &&
                time.compareTo(task[i][1]) < 0 &&
                (task[i][2] == "available" || task[i][2] == "over time") ) {
                    return true;
                }
            else if(time.compareTo(task[i][0]) >= 0 &&
                    time.compareTo(task[i][1]) < 0 &&
                    task[i][2] == "reserved"){
                return false;
            }
        }
        return false;
    }

    public void updateSchedule(String time){
        for(int i = 0; i < task.length; i++){
            if (time.compareTo(task[i][0]) > 0){
                if(task[i][2] == "reserved"){
                    continue;
                }
                task[i][2] = "over time";
            }
        }
    }

    public boolean hasReserved(String time){
        for(int i = 0; i < task.length; i++){
            if (time.compareTo(task[i][0]) >= 0 &&
                time.compareTo(task[i][1]) < 0 &&
                task[i][2] == "available") {
                    return false;
                }
            else if(time.compareTo(task[i][0]) >= 0 &&
                    time.compareTo(task[i][1]) < 0 &&
                    task[i][2] == "reserved"){
                return true;
            }
        }
        return false;
    }

    public void reserve(String time, String location){
        for(int i = 0; i < task.length; i++){
            if (time.compareTo(task[i][0]) >= 0 &&
                time.compareTo(task[i][1]) < 0 ) {

                task[i][2] = "reserved";
                task[i][3] = location;
            }
        }
    }

    public void initialize() {
        for(int i = 0; i < task.length; i++){
            task[i][2] = "available";
            task[i][3] = "null";
        }
    }

    public void getTask(){
        for(int i = 0; i < task.length; i++){
            for(int x = 0; x < task[i].length; x++){
                System.out.println(task[i][x]);
            }
            System.out.println();
        }
        
    }
}
