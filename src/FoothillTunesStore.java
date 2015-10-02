import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cs1c.SongEntry;

public class FoothillTunesStore
{
   private static final boolean ENABLE_DATA_OUTPUT = false;
   private String jsonFileName = "";
   private ArrayList<SongEntry> tunes;
   private ArrayList<Genre> genres;
   private SongEntry[] arrayOfSongs;
   private JSONArray allSongs;

   /**
    * One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.
    * @author CS1C, Foothill College, Bita Mazloom
    * @version 1.0
    */
 


      

      public FoothillTunesStore(JSONArray allSongs)
      {
         // --------------------
         // create an array of all the JSON objects
         arrayOfSongs = new SongEntry[allSongs.size()];

         Iterator<?> iterator = allSongs.iterator();
         int counter = 0;
         while (iterator.hasNext() && counter < arrayOfSongs.length) 
         {
            JSONObject currentJson = (JSONObject)iterator.next();
            String title = currentJson.get("title").toString();
            int duration = (int)Double.parseDouble(currentJson.get("duration").toString());
            String artist = currentJson.get("artist_name").toString();
            String genre = currentJson.get("genre").toString();

            SongEntry currentSong = new SongEntry(title, duration, artist, genre);
            arrayOfSongs[counter++] = currentSong;
         }        

      }

      /**
       * returns the array of song entries
       */
      public SongEntry[] getArrayOfSongs()
      {  return arrayOfSongs; }

      /**
       * displays the array of song entries
       */
      public void printAllSongs()
      {
         for (SongEntry song : arrayOfSongs)
            System.out.println(song);
      }
}
