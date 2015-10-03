import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cs1c.SongEntry;

public class FoothillTunesStore
{
   private static final boolean ENABLE_DATA_OUTPUT = false;
 
   private ArrayList<SongEntry> tunes;
   private ArrayList<Genre> genres;
   private SongEntry[] arrayOfSongs;
   private JSONArray allSongs;

   /**
    * One object of class MillionSongDataSubset parses a JSON data set and
    * stores each entry in an array.
    * 
    * @author CS1C, Foothill College, Bita Mazloom
    * @version 1.0
    */

   public FoothillTunesStore(String jsonFileName)
   {
      String jsonFilePath = "resources/" + jsonFileName;
      JSONParser jsonParser = new JSONParser();

      try
      {
         // --------------------
         // parse the JSON file
         FileReader fileReader = new FileReader(jsonFilePath);
         JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
         JSONArray allSongs = (JSONArray) jsonObject.get("songs");
         System.out.println("Parsing JSON file...");
         // MillionSongDataSubset dataSet = new MillionSongDataSubset(allSongs);

         // --------------------
         // create an array of all the JSON objects
         arrayOfSongs = new SongEntry[allSongs.size()];

         tunes = new ArrayList<>();
         Iterator<?> iterator = allSongs.iterator();
         int counter = 0;
         while (iterator.hasNext() && counter < allSongs.size())
         {
            JSONObject currentJson = (JSONObject) iterator.next();
            String title = currentJson.get("title").toString();
            int duration = (int) Double.parseDouble(currentJson.get("duration")
                  .toString());
            String artist = currentJson.get("artist_name").toString();
            String genre = currentJson.get("genre").toString();

            SongEntry currentSong = new SongEntry(title, duration, artist,
                  genre);
            tunes.add(currentSong);
            arrayOfSongs[counter++] = currentSong;
         }
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      } catch (IOException e)
      {
         e.printStackTrace();
      } catch (ParseException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * returns the array of song entries
    */
   public SongEntry[] getArrayOfSongs()
   {
      return arrayOfSongs;
   }

   /**
    * displays the array of song entries
    */
   public void printAllSongs()
   {
      for (SongEntry song : arrayOfSongs)
         System.out.println(song);
   }
   
   public void printArrayListTunes()
   {
      int counter = 1;
      for (SongEntry s : tunes)
      {
         System.out.println();
         System.out.println("Song number " + counter);
         System.out.println(s.getArtistName());
         System.out.println(s.getDuration());
         System.out.println(s.getGenre());
         System.out.println(s.getTitle());
         counter++;
      }
   }
}
