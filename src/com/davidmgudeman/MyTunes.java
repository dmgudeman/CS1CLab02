package com.davidmgudeman;

/**
 * Creates an object of type FoothillTunesStore which parses a data file
 * in JSON format. Reads an input file that contains users selected actions.
 * Prints out a menu with available actions we can take.
 * Example selections: showing all the songs in our personal tunes library,
 *  adding new songs in our tunes library, making a playlist of a certain
 *  duration, etc. Outputs the estimated run time of "findSubset" method.
 *
 * @author Foothill College, David Gudeman
 *
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs1c.SongEntry;
import cs1c.TimeConverter;

/**
 * An object of a class stores and manages purchased tunes.
 */
public class MyTunes
{

   private ArrayList<SongEntry> purchasedTunes;
   private static String jsonFileName = "music_genre_subset.json";
   static Scanner keyboard = new Scanner(System.in);

   /**
    * zero constructor
    * 
    */
   public MyTunes()
   {
   }

   /**
    * standard I/O to capture the menu driven input from the user.
    * 
    * @param choice
    */
   public static void choosing(int choice)
   {
      long startTime, estimatedTime = 0;

      FoothillTunesStore tunes = new FoothillTunesStore(jsonFileName);

      switch (choice)
      {
      case 0:
         System.out.println("stopping");
         break;
      case 1:
         System.out.println("HELP MENU");
         break;
      case 2:
         System.out.println("Songs by Title");
         startTime = System.nanoTime();
         tunes.printAllSongs();
         estimatedTime = System.nanoTime() - startTime;
         break;
      case 3:
         System.out.println("Songs by genre");
         startTime = System.nanoTime();
         Genre genre = new Genre(tunes);
         genre.printByGenre();
         estimatedTime = System.nanoTime() - startTime;
         break;
      case 4:
         System.out.println("Buying song entitled:");
         Scanner s = new Scanner(System.in);
         String song = s.nextLine();
         System.out.println("This what you input " + song);
         startTime = System.nanoTime();
         tunes.findSongByTitle(song);
         estimatedTime = System.nanoTime() - startTime;
         s.close();
         break;
      case 5:
         System.out
               .println("How long in minutes would you like the playlist to be?");
         TunesSubSet subSet = new TunesSubSet(tunes);
         Scanner s2 = new Scanner(System.in);
         Integer playListLength = Integer.parseInt(s2.nextLine());
         System.out.println("This what you input " + playListLength
               + " minutes.");
         System.out.println("Creating playlist");
         int playMilli = playListLength * 60;
         startTime = System.nanoTime();
         subSet.findSubSet(playMilli);
         estimatedTime = System.nanoTime() - startTime;
         s2.close();

         break;

      }
      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
            + TimeConverter.convertTimeToString(estimatedTime) + ", "
            + " seconds.\n");

   }

   /**
    * Shows the titles of purchased tunes.
    */
   public void showLibrary()
   {
      System.out.println(purchasedTunes);
   }

   /**
    * Shows the menu of available actions.
    */
   public static void printMenu()
   {
      System.out.println("\nMenu:");
      System.out.println("0. Quit");
      System.out.println("1. Output this menu");
      System.out.println("2. Show all song titles");
      System.out.println("3. Show songs by genre ");
      System.out.println("4. Buy songs by title");
      System.out.println("5. Create a playlist");
      System.out.println("");
   }

   /**
    * Calls a JSONParse part of the "org.json.simple" package to parse the input
    * file. Stores each song entry in an array of SongEntry. Demonstrates
    * measuring elapsed time of an example algorithm.
    */
   public static void main(String[] args)
   {
      printMenu();

      FoothillTunesStore tunes = new FoothillTunesStore(jsonFileName);

      {
         Pattern intsOnly = Pattern.compile("([\\+-]?\\d+)([eE][\\+-]?\\d+)?");
         Matcher makeMatch = intsOnly.matcher(keyboard.next());
         makeMatch.find();
         String str = makeMatch.group();
         Integer choice = Integer.parseInt(str);
         System.out.println("THIS IS YOUR CHOICE " + choice);
         choosing(choice);
      }

   }
}
