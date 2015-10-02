import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ShoppingBag
{
   Boolean foundSubSet = false;
   String FILENAME;
   int cashOnHand;
   ArrayList<Integer> groceryList;
   ArrayList<ArrayList<Integer>> Col;

   public ShoppingBag(String fILENAME)
   {
      super();
      FILENAME = fILENAME;
   }

   public ArrayList<Integer> getPriceOfGroceries() throws FileNotFoundException
   {

      String filename = this.FILENAME;
      BufferedReader inFile = new BufferedReader(new FileReader(filename));

      // Define and initialize the ArrayList
      groceryList = new ArrayList<>(); // The ArrayList stores strings

      String inline; // Buffer to store the current line
      try
      {
         while ((inline = inFile.readLine()) != null) // Read line-by-line,
                                                      // until end of file
         {
            Pattern intsOnly = Pattern
                  .compile("([\\+-]?\\d+)([eE][\\+-]?\\d+)?");
            Matcher makeMatch = intsOnly.matcher(inline);
            makeMatch.find();
            String str = makeMatch.group();
            Integer i = Integer.parseInt(str);
            groceryList.add(i);
         }
         inFile.close(); // We've finished reading the file
      } catch (IOException e)
      {
         e.printStackTrace();
      }
      return groceryList;
   }

   public void findSubset(int budget)
   {
      int maxSize = 0;
      Col = new ArrayList<>();
      ArrayList<Integer> emptySet = new ArrayList<>();
      Col.add(emptySet);
      for (Integer item : groceryList)
      {
         if (foundSubSet == false)
         {
            int size = Col.size();
            for (int i = 0; i < size; i++)
            {
               ArrayList<Integer> newSubSet = (ArrayList<Integer>) Col.get(i)
                     .clone();
               newSubSet.add(item);
               int newSubSetSize = listSummation(newSubSet);
               if (newSubSetSize < budget)
               {
                  if (newSubSetSize > maxSize)
                     maxSize = newSubSetSize;
                  Col.add(newSubSet);
               }
               if (newSubSetSize == budget)
               {
                  foundSubSet = true;
                  printSubSet(newSubSet);
                  break;
               }
            }
         }
      }
   }

   public Integer listSummation(ArrayList<Integer> listToSum)
   {
      Integer sum = 0;
      for (Integer i : listToSum)
      {
         sum = sum + i;
      }
      return sum;
   }

   public void printSubSet(ArrayList<Integer> subSet)
   {
      System.out.print("[ ");
      for (Integer a : subSet)
      {
         System.out.print(+a + ", ");
      }
      System.out.println("] sum = " + listSummation(subSet));
   }

   public void printCol(ArrayList<ArrayList<Integer>> Col)
   {
      for (ArrayList<Integer> I : Col)
      {
         System.out.print("[ ");
         printSubSet(I);
         System.out.print(" ]");
         System.out.println();
      }
   }

}
