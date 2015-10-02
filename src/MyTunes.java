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

import cs1c.SongEntry;
import cs1c.TimeConverter;

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

   public static void main(String[] args)
   {
      final String jsonFilePath = "resources/music_genre_subset.json";
      final String tunesTestFilePath = "resources/test_tunes.txt";

      FoothillTunesStore store = new FoothillTunesStore(jsonFilePath);
      ArrayList<String> storeTitles = store.getTitles();

      if (storeTitles.isEmpty())
      {
         System.out.println("Welcome! Currently there are no songs in the "
                     + "FoothillTunes store!");
      } else
      {

         System.out.println("Welcome! We have over " + storeTitles.size()
               + " in FoothillTunes store!");
      }

      ArrayList<String> tunesTestFile = MyTunes.readTestFile(tunesTestFilePath);

      MyTunes.printMenu();

      MyTunes personalTunes = new MyTunes(store);

      ArrayList<String> linesInFile = MyTunes.readTestFile(tunesTestFilePath);
      int selection = -1;
      long startTime, estimatedTime;

      for (int i = 0; i < linesInFile.size() && selection != QUIT; /*
                                                                    * no need to
                                                                    * increment
                                                                    * here
                                                                    */)
      {
         String line = linesInFile.get(i++);
         String[] tokens = line.split(" ");
         if (line.contains("selection"))
            selection = Integer.parseInt(tokens[1]);
         else
         {
            // invalid selection format
            System.out.println("WARNING: Invalid selection");
            continue;
         }

         System.out.println("\nselected option:" + selection);
         switch (selection)
         {
         case QUIT:
            break;
         case HELP_MENU:
            MyTunes.printMenu();
            break;
         case LIST_SONG_TITLES:
            System.out.println("Number of titles in store = "
                  + personalTunes.getPurchasedTunes().size());
            personalTunes.showLibrary();
            break;
         case LIST_SONGS_BY_GENRE:
            // capture start time
            startTime = System.nanoTime();

            // implement grouping songs by genre
            store.groupSongsByGenre();

            // stop and calculate elapsed time
            estimatedTime = System.nanoTime() - startTime;

            // output the result
            store.printNumOfSongsInEachGenre();

            // report algorithm time
            System.out.println("\nAlgorithm Elapsed Time: "
                  + TimeConverter.convertTimeToString(estimatedTime) + "\n");
            break;
         case BUY_SONG_TITLE:
            String title = linesInFile.get(i++);
            System.out.println("selected song title: " + title);

            // capture start time
            startTime = System.nanoTime();

            // implement searching for songs by title
            ArrayList<SongEntry> searchResult = store.buySongByTitle(title);

            // stop and calculate elapsed time
            estimatedTime = System.nanoTime() - startTime;

            System.out.println("Found " + searchResult.size() + " two songs:");
            System.out.println(searchResult);

            personalTunes.addSongs(searchResult);

            // report algorithm time
            System.out.println("\nAlgorithm Elapsed Time: "
                  + TimeConverter.convertTimeToString(estimatedTime) + "\n");
            break;

         case CREATE_PLAYLIST:
            int numSongsToBuy = Integer.parseInt(linesInFile.get(i++));
            System.out.println("selected number of songs to buy: "
                  + numSongsToBuy);

            personalTunes.addSongs(store.getFirstNTitles(numSongsToBuy,
                  ENABLE_RANDOM_PURCHASE));

            int lengthInMinutes = Integer.parseInt(linesInFile.get(i++));
            int seconds = lengthInMinutes * 60;
            System.out.println("selected playlist length (in seconds): "
                  + seconds);

            // capture start time
            startTime = System.nanoTime();

            // implement finding subset of songs that is closest to the
            // requested length of play list
            // HINT: Use same approach as buying a subset of groceries within
            // budget
            ArrayList<SongEntry> myPlayList = personalTunes
                  .makePlayList(seconds);

            // stop and calculate elapsed time
            estimatedTime = System.nanoTime() - startTime;

            // output the result
            int totalTime = 0;
            for (SongEntry song : myPlayList)
            {
               totalTime += song.getDuration();
            }
            System.out
                  .println("length of play list (in seconds): " + totalTime);
            System.out.println("songs in play list: " + myPlayList);

            // report algorithm time
            System.out.println("\nAlgorithm Elapsed Time: "
                  + TimeConverter.convertTimeToString(estimatedTime) + "\n");
            break;
         default:
            System.out.println("ERROR : invalid selection.");
            MyTunes.printMenu();
            break;
         } // switch
      }
   }

   private ArrayList<SongEntry> getPurchasedTunes()
   {
      // TODO Auto-generated method stub
      return null;
   }

   private ArrayList<SongEntry> makePlayList(int seconds)
   {
      // TODO Auto-generated method stub
      return null;
   }

   private void addSongs(Object firstNTitles)
   {
      // TODO Auto-generated method stub

   }

   private static ArrayList<String> readTestFile(String tunesTestFilePath)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
