/*
 * Write your program inside the main method to compute the 
 * Gravitational Force according to the assignment description.
 * 
 * To compile:
 *        javac GravitationalForce.java
 * To execute:
 *        java GravitationalForce 36000.0 1080.0 50.0
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class GravitationalForce {

    public static void main(String[] args) {
       
        double mass_obj1 = Double.parseDouble(args[0]);
        double mass_obj2 = Double.parseDouble(args[1]);
        double distance = Double.parseDouble(args[2]);
        double G = 6.6743e-11;

        double Force = G*(mass_obj1*mass_obj2)/(distance*distance);
        System.out.println(Force);
    }
}
