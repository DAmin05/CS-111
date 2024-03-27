/*
 *
 * Write the Buses program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac Buses.java
 * To execute:
 *        java Buses 7302
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class Buses {
    public static void main(String[] args) {
        int bus_number = Integer.parseInt(args[0]);
        int sum = 0;

        if(bus_number > 0){
            while (bus_number > 0){
                int x = bus_number % 10;
                sum += x;
                bus_number /= 10;
            }

            if (sum % 2 == 0){
                System.out.println("LX");
            }
            else{
                System.out.println("H");
            }
        }
        else{
            System.out.println("Error");
        }
    }
}
