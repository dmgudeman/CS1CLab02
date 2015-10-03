package com.davidmgudeman;

/**


 * Creates an object of type FoothillTunesStore which parses a data file
 * in JSON format. Reads an input file that contains users selected actions.
 * Prints out a menu with available actions we can take.
 * Example selections: showing all the songs in our personal tunes library,
 *  adding new songs in our tunes library, making a playlist of a certain
 *  duration, etc. Outputs the estimated run time of "findSubset" method.
 *
 * @author Foothill College, [YOUR NAME HERE]
 *
 * REMINDER: Include text cases in addition to those provided.
 *
 *
 * NOTE: Due to a few data points in the json file, such as:
 * ,
{
        "genre": "classic pop and rock",
        "artist_name": "Crosby_ Stills_ Nash and Young",
        "title": "Carry On",
        "duration": "0.80934"
},
 * Modify the constructor for class MillionSongDataSubset to save "duration" as follows:
        int duration = (int)Math.ceil(Double.parseDouble(currentJson.get("duration").toString()));
 *
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs1c.SongEntry;

/**
 * An object of a class stores and manages purchased tunes.
 */
public class MyTunes
{
   private static final int QUIT = 0;
   private static final int HELP_MENU = 1;
   private static final int LIST_SONG_TITLES = 2;
   private static final int LIST_SONGS_BY_GENRE = 3;
   private static final int BUY_SONG_TITLE = 4;
   private static final int CREATE_PLAYLIST = 5;

   private static final boolean ENABLE_RANDOM_PURCHASE = false;

   private ArrayList<SongEntry> purchasedTunes;
   private FoothillTunesStore theStore;
   private static final String jsonFilePath = "resources/music_genre_subset.json";
   private static final boolean ENABLE_DATA_OUTPUT = true;
   private static String jsonFileName = "music_genre_subset.json";
   static Scanner keyboard = new Scanner(System.in);

   public MyTunes(FoothillTunesStore store)
   {
 
   }
   
   public static void choosing (int choice)
   {
      FoothillTunesStore tunes = new FoothillTunesStore(jsonFileName);
      Genre genre = new Genre(tunes);
      switch(choice) 
      {
      case 0 :
         System.out.println("stopping");
         break;
      case 1:
         System.out.println("HELP MENU");
         break;
      case 2:
         System.out.println("Songs by Title");
         tunes.printAllSongs();
         break;
      case 3:
         System.out.println("Songs by genre");
         genre.printByGenre();
         break;
      case 4:
         System.out.println("Buying song entitled:");
         Scanner s = new Scanner(System.in);
         String song = s.nextLine();
         System.out.println("This what you input " + song);
         tunes.findSongByTitle(song);
         s.close();
         break;
      case 5:
         System.out.println("How long in minutes would you like the playlist to be?");
         Scanner s2 = new Scanner(System.in);
         Integer playListLength = Integer.parseInt(s2.nextLine());
         System.out.println("This what you input " + playListLength + " minutes.");     
         s2.close();
         System.out.println("Creating playlist");
         break;
        
      }
     
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
      System.out.println("3. Show all songs by genre ");
      System.out.println("4. Buy songs by title");
      System.out.println("5. Create a playlist");
      System.out.println("");
   }

   /**
    * Calls a JSONParse part of the "org.json.simple" package to parse the input file.
    * Stores each song entry in an array of SongEntry.
    * Demonstrates measuring elapsed time of an example algorithm.
    */
   public static void main(String[] args) {
      printMenu();
    
        FoothillTunesStore tunes = new FoothillTunesStore(jsonFileName);
      //   tunes.printArrayListTunes();
         Genre genre = new Genre(tunes);
     
         {
            Pattern intsOnly = Pattern
                  .compile("([\\+-]?\\d+)([eE][\\+-]?\\d+)?");
            Matcher makeMatch = intsOnly.matcher(keyboard.next());
            makeMatch.find();
            String str = makeMatch.group();
            Integer choice = Integer.parseInt(str);
            System.out.println("THIS IS YOUR CHOICE "+ choice);
            choosing(choice);
         }
/*
         // display unsorted array of songs
         System.out.println("Completed parsing JSON file.");
         if (ENABLE_DATA_OUTPUT)
            dataSet.printAllSongs();
            System.out.println("allSongs size: " + allSongs.size());

         // --------------------
         // use to measure the run time
   //      long startTime, estimatedTime;

  //       System.out.println("Sorting array of " + dataSet.getArrayOfSongs().length + " songs via BubbleSort...");

         // measuring run time of an example algorithm
 //        startTime = System.nanoTime();    

         // sort
 //        SongEntry.setSortType(SongEntry.SORT_BY_DURATION);
 //        BubbleSort.sortArray(dataSet.getArrayOfSongs());

  //       estimatedTime = System.nanoTime() - startTime;

         // display the sorted list
         if (!ENABLE_DATA_OUTPUT)
            dataSet.printAllSongs();

         // report algorithm time
  //       System.out.println("\nAlgorithm Elapsed Time: "
  //             + TimeConverter.convertTimeToString(estimatedTime) + ", "
  //             + " seconds.\n");

  */
   }
}
