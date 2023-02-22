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

    public void run() {
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

            System.out.println(time);

            //initialize
            if (time.compareTo("14:40:00") == 0 ) {
              System.out.println("hi");
            }

            Thread.sleep(1000);
            
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    
}
