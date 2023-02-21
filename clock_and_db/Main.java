public class Main {
    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();
        
        // set to second 00
        while (true) {
            if(clock.getCurrentTime().split(":")[2].equals("00")){
                clock.run();
                break;
            }
            System.out.println(clock.getCurrentTime());
            Thread.sleep(500);
        }
        
    }
}
