import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import cs1c.TimeConverter;

public class Main
{

   public static void main(String[] args) throws FileNotFoundException
   {
      final String FILENAME = "resources/groceries.txt"; 

      ShoppingBag bag = new ShoppingBag(FILENAME);
      ArrayList<Integer> shoppingList = bag.getPriceOfGroceries();

      System.out.println("Groceries wanted:");
      System.out.println(shoppingList);

      System.out.println("Enter how much cash you have:");
      Scanner keyboard = new Scanner(System.in);
      int budget = Integer.parseInt(keyboard.next());

      long startTime, estimatedTime;

      // capture start time
      startTime = System.nanoTime();

      // output the result
      System.out.println("Purchased grocery prices are:");
      bag.findSubset(budget);
      
      // stop and calculate elapsed time
      estimatedTime = System.nanoTime() - startTime;

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
            + TimeConverter.convertTimeToString(estimatedTime) + ", "
            + " seconds.\n");
   }

}
