import java.util.Calendar;

import java.text.DecimalFormat;

public class Clock {
    private DecimalFormat tflz, tf;
    private final String initialTime = "23:00:00";
    DatabaseAction databaseAction;

    public Clock() {
        tf = new DecimalFormat("#0");
        tflz = new DecimalFormat("00");
        databaseAction = new DatabaseAction();
    }

    public String getCurrentTime(){
      String time;
      Calendar calendar = Calendar.getInstance();
      StringBuffer buf = new StringBuffer();
      buf.append(tf.format(calendar.get(Calendar.HOUR_OF_DAY)));
      buf.append(':');
      buf.append(tflz.format(calendar.get(Calendar.MINUTE)));
      buf.append(':');
      buf.append(tflz.format(calendar.get(Calendar.SECOND)));
      time =  buf.toString();

      return time;
    }

    public void run() {
      String time;
        try {
          while (true) {
            time =  getCurrentTime();

            System.out.println("second loop " + time); // check if the time is correct

            
            String minute = time.split(":")[1];
            // int minuteInt = Integer.parseInt(minute);
            String second = time.split(":")[2];
            // int secondInt = Integer.parseInt(minute);

            // hmsTime format: "14:40:00"
            // if (time.compareTo(hmsTime) == 0 ) {
            //   System.out.println("hi");
            // }
            if ( (minute.matches("00") || minute.matches("15") || minute.matches("30") || minute.matches("45")) 
                &&  second.matches("00")) {
                // do something
                System.out.println(time);
                System.out.println("hi");
            }

            if(time.matches(initialTime)){
              databaseAction.initializeDB();
            }

            // 1000ms = 1s
            Thread.sleep(5000);
            
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    
}