import java.util.Calendar;
import java.text.DecimalFormat;

public class Clock {
    private DecimalFormat tflz, tf;
    private String time;

    public Clock() {
        tf = new DecimalFormat("#0");
        tflz = new DecimalFormat("00");
    }

    public String getCurrentTime(){
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

    public void run(String hmsTime) {
        try {
          while (true) {
            Calendar calendar = Calendar.getInstance();
            StringBuffer buf = new StringBuffer();
            buf.append(tf.format(calendar.get(Calendar.HOUR_OF_DAY)));
            buf.append(':');
            buf.append(tflz.format(calendar.get(Calendar.MINUTE)));
            buf.append(':');
            buf.append(tflz.format(calendar.get(Calendar.SECOND)));
            time =  buf.toString();

            System.out.println("second loop " + time); // check if the time is correct

            
            String minute = time.split(":")[1];
            int minuteInt = Integer.parseInt(minute);
            String second = time.split(":")[2];
            // int secondInt = Integer.parseInt(minute);

            // System.out.println("minute: " + minute);
            // System.out.println("minuteInt: " + minuteInt);
            // System.out.println("second: " + second);
            // System.out.println("secondInt: " + secondInt);
            // hmsTime format: "14:40:00"
            // if (time.compareTo(hmsTime) == 0 ) {
            //   System.out.println("hi");
            // }
            if ( (minute.matches("00") || minute.matches("15") || minute.matches("30") || minute.matches("45")) 
                &&  second.matches("00")) {
                System.out.println(time);
                System.out.println("hi");
            }

            // 1000ms = 1s
            Thread.sleep(5000);
            
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    
}