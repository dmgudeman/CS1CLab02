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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cs1c.MillionSongDataSubset;
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

   public MyTunes(FoothillTunesStore store)
   {
      // TODO Auto-generated constructor stub
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

      JSONParser jsonParser = new JSONParser();

      try {

         // --------------------
         // parse the JSON file
         FileReader fileReader = new FileReader(jsonFilePath);

         JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

         JSONArray allSongs = (JSONArray) jsonObject.get("songs");

         System.out.println("Parsing JSON file...");
         MillionSongDataSubset dataSet = new MillionSongDataSubset(allSongs);

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

      } 
      catch (FileNotFoundException e) 
      {  e.printStackTrace(); } 
      catch (IOException e) 
      {  e.printStackTrace(); } 
      catch (ParseException e) 
      {  e.printStackTrace(); }
   }
}
