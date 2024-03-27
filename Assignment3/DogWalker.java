/*
 *  
 * Write the DogWalker program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac DogWalker.java
 * To execute:
 *        java DogWalker 5
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 *
 */
public class DogWalker {

    public static void main(String[] args) {

       int steps = Integer.parseInt(args[0]);
       int x = 0;
       int y = 0;
       System.out.println("(" + x + "," + y + ")");

       for (int a = 0; a<steps ; a++){
        
        double random = Math.random();

        if (random < 0.25){
            x++;
        }
        else if (random < 0.5){
            x--;
        }
        else if (random < 0.75){
            y++;
        }
        else{
            y--;
       }
       System.out.println("(" + x + "," + y + ")");
    }

    double distance = Math.pow(x,2) + Math.pow(y,2);
    System.out.println("Square Distance: " + distance);

    }
}
