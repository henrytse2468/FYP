public class Main {
    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();
        while (true) {
            if(clock.getCurrentTime().split(":")[2].equals("00")){
                clock.run("10");
                break;
            }
            System.out.println(clock.getCurrentTime());
            Thread.sleep(500);
        }
        
    }
}
