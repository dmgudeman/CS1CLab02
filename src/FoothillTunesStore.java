import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cs1c.BubbleSort;
import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;

public class FoothillTunesStore
{
   private static final String jsonFilePath = "resources/music_genre_subset.json";
   private static final boolean ENABLE_DATA_OUTPUT = false;
   String jsonFileName = "";
   ArrayList<SongEntry> tunes;
   ArrayList<Genre> genres;
   private SongEntry[] arrayOfSongs; 
   

   public FoothillTunesStore(String jsonFilePath2)
   {
      super();
      this.jsonFileName = jsonFilePath;
     //create an array of all the JSON objects
     tunes = makeSongEntryList(jsonFileName);
   //   arrayOfSongs = new SongEntry[allSongs.size()];
     // Iterator<?> iterator = allSongs.iterator();
   //   int counter = 0;
      for (SongEntry s : tunes)
      {
     //    JSONObject currentJson = (JSONObject)iterator.next();
         String title = s.getTitle();
         int duration = s.getDuration();
         String artist = s.getArtistName();
         String genre = s.getArtistName();

         SongEntry currentSong = new SongEntry(title, duration, artist, genre);
       //  arrayOfSongs[counter++] = currentSong;
      }  
   }

   public Object getFirstNTitles(int numSongsToBuy, boolean enableRandomPurchase)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<SongEntry> buySongByTitle(String title)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void groupSongsByGenre()
   {
      // TODO Auto-generated method stub

   }

   public void printNumOfSongsInEachGenre()
   {
      // TODO Auto-generated method stub

   }

   public ArrayList<String> getTitles()
   {    
      ArrayList<SongEntry> list = this.tunes;
      ArrayList<String> currentTitles = new ArrayList<String>();

      for (SongEntry s : list)
      {

         currentTitles.add(s.getTitle());
      }
      return currentTitles;
   }

   public  makeSongEntryList(String jsonFileName)
   {
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

         // --------------------
         // use to measure the run time
         long startTime, estimatedTime;

         System.out.println("Sorting array of " + dataSet.getArrayOfSongs().length + " songs via BubbleSort...");

         // measuring run time of an example algorithm
         startTime = System.nanoTime();    

         // sort
         SongEntry.setSortType(SongEntry.SORT_BY_DURATION);
         BubbleSort.sortArray(dataSet.getArrayOfSongs());

         estimatedTime = System.nanoTime() - startTime;

         // display the sorted list
         if (ENABLE_DATA_OUTPUT)
            dataSet.printAllSongs();

         // report algorithm time
         System.out.println("\nAlgorithm Elapsed Time: "
               + TimeConverter.convertTimeToString(estimatedTime) + ", "
               + " seconds.\n");

      } 
      catch (FileNotFoundException e) 
      {  e.printStackTrace(); } 
      catch (IOException e) 
      {  e.printStackTrace(); } 
      catch (ParseException e) 
      {  e.printStackTrace(); }
   }
}
