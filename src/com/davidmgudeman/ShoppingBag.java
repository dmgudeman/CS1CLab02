package com.davidmgudeman;

/**
 * This contains the Main for Part 1 of the assignment. This class takes in a 
 * text file that provides the grocery list which is the superset from 
 * which the subsets are to be taken.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs1c.TimeConverter;

public class ShoppingBag
{
   Boolean foundSubSet = false;
   String FILENAME;
   int cashOnHand;
   ArrayList<Integer> groceryList;
   ArrayList<ArrayList<Integer>> Col;

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

   /**
    * contstructor the takes in the text filename as a String.
    * 
    * @param fILENAME
    */
   public ShoppingBag(String fILENAME)
   {
      super();
      FILENAME = fILENAME;
   }

   /**
    * Creates an ArrayList of Integers that represents the superset, grocery 
    * list for the assignment.
    * @return
    * @throws FileNotFoundException
    */
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

   /**
    * main method to find the closest subset to the alloted amount of money 
    * (budget) which is obtained from the user by way of the console.
    * @param budget
    */
   public void findSubset(int budget)
   {
      int maxSize = 0;
      Col = new ArrayList<>();
      ArrayList<Integer> emptySet = new ArrayList<>();
      ArrayList<Integer> maxSubSet = null;

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
                  maxSubSet = newSubSet;
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
      if (foundSubSet == false)
         printSubSet(maxSubSet);

   }

   /**
    * helper class to sum an Integer ArrayList.
    * @param listToSum
    * @return
    */
   public Integer listSummation(ArrayList<Integer> listToSum)
   {
      Integer sum = 0;
      for (Integer i : listToSum)
      {
         sum = sum + i;
      }
      return sum;
   }

   /**
    * helper class to print out ArrayList of Integers.
    * @param subSet
    */
   public void printSubSet(ArrayList<Integer> subSet)
   {
      System.out.print("[ ");
      for (Integer a : subSet)
      {
         System.out.print(+a + ", ");
      }
      System.out.println("] sum = " + listSummation(subSet));
   }

   /**
    * Prints out the entire set of subsets. The superset is an ArrayList of 
    * ArrayLists of Integers.
    * @param Col
    */
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
