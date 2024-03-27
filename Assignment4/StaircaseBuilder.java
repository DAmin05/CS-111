/*
 * Write your program inside the main method to build
 * a staicase in a 2D array of characters according
 * to the assignment description
 *
 * To compile:
 *        javac StaircaseBuilder.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class StaircaseBuilder {
    
    public static void main(String[] args) {

        int dimension = Integer.parseInt(args[0]);
        int bricks_total = Integer.parseInt(args[1]);
        char [][] dimension_array = new char[dimension][dimension];
        
        for(int i=0 ; i<dimension_array.length ; i++)
        {
            for(int j=(dimension_array.length - 1) ; j>=0 ;j--)
            {
                if(j >= (dimension_array.length - 1 - i) && bricks_total > 0)
                {
                    dimension_array[j][i] = 'X';
                    bricks_total--;
                }
                else
                {
                    dimension_array[j][i] = ' ';
                }
            }
        }

        for (int i=0 ; i<dimension_array.length ; i++)
        {
            for(int j=0 ; j<dimension_array.length ; j++)
            {
                System.out.print(dimension_array[i][j]);
            }
            System.out.println();
        }
        System.out.println("Bricks remaining: " + bricks_total);
    }
}
