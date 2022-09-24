import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class main {
    public static Scanner input = new Scanner(System.in);
    public static Clock clock;
    public static Schedule schedule;

    public static void main(String arg[]){
        clock = new Clock();
        schedule = new Schedule();

        Callable<Void> callable1 = new Callable<Void>(){
            @Override
            public Void call() throws Exception
            {
                homePage();
                return null;
            }
        };

        Callable<Void> callable2 = new Callable<Void>(){
            @Override
            public Void call() throws Exception
            {
                autoInitialize();
                return null;
            }
        };

        List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
        taskList.add(callable1);
        taskList.add(callable2);

        //create a pool executor with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try
        {
            //start the threads and wait for them to finish
            executor.invokeAll(taskList);
        }
        catch (InterruptedException ie)
        {
            //do something if you care about interruption;
            System.out.println("hi");
        }

        /* autoInitialize();
        homePage(); */
        
    }

    private static void homePage(){
        try{
            int selection;
			System.out.println("------------------------------");
			System.out.println("Home Page");
			
			do {
				System.out.println("What do you want to do? ");
			    System.out.println("Reserved - 0; Get All Record - 1; " + "Initialize - 2; Check if now is available - 3; " +
                "Close - 4");
				System.out.print("Selection: ");
				selection = input.nextInt();
				input.nextLine();

				if(selection == 0) {
					System.out.print("Reserve Location: ");
                    String location = input.nextLine();

                    System.out.print("Reserve time: ");
                    String time = input.nextLine();
                    if( !schedule.hasReserved(time) ){
                        schedule.reserve(time, location);
                        System.out.println("Reserved Successfully");
                    }
                    else{
                        System.out.println("This section is reserved.");
                    }

                    homePage();
				}
				
				else if(selection == 1) {
					schedule.getTask();
					homePage();
                }
				
				else if(selection == 2) {
					schedule.initialize();
                    homePage();
				}

				else if(selection == 3) {
					if (schedule.isAvailableNow(clock.getCurrentTime())) {
                        System.out.println("You can use me now");
                    }
                    else{
                        System.out.println("I am reserved this section");
                    }
                    homePage();
				}

				else if(selection == 4) {
					System.out.print("bye");
					System.exit(0);
				}
				
				else {
					System.out.println("Invalid number");
				}
				
			}while(selection != 0 && selection != 1 && selection != 2 && selection != 3 && selection != 4);
			
		}catch (InputMismatchException e) {
			System.out.println("Wrong input! Enter again please");
			input.next();
			homePage();
		}
    }

    private static void autoInitialize(){
        try {
            while (true) {
              
            String time = clock.getCurrentTime();

            //initialize
            if (time.compareTo("17:36:00") == 0 ) {
                System.out.println("Today work is over, initialize now");
                schedule.initialize();
                System.out.println("Initialized");
            }
  
            Thread.sleep(1000);
              
            }
          } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}