/*
 * Write your program inside the main method to find the order
 * which the bus the student needs to take will arrive
 * according to the assignemnt description. 
 *
 * To compile:
 *        javac BusStop.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class BusStop {

    public static void main(String[] args) {

        int[] bus_stops = new int[args.length];
        int bus_number = Integer.parseInt(args[args.length-1]);
        int order = 1000;

        for(int i=0 ; i<args.length ; i++)
        {
            bus_stops[i] = Integer.parseInt(args[i]);
        }

        for (int i=1 ; i<bus_stops.length ; i++)
        {
            if(bus_number == bus_stops[i-1])
            {
                order = i;
                break;
            }
        }

        System.out.println(order);
       
    }
}
