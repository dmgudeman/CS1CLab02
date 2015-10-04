package com.davidmgudeman;

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

   ArrayList<SongEntry> tunes;
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
      ArrayList<SongEntry> songEntryList = new ArrayList<>();
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
         makeListOfSongs(arrayOfSongs, songEntryList);
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
   public void makeListOfSongs(SongEntry[] entries, ArrayList<SongEntry> songEntryList)
   {
      for (int i = 0; i < entries.length; i++ )
      {
         songEntryList.add(entries[i]);
      }
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
   public void printSubListTunes(ArrayList<SongEntry> song)
   {   
      System.out.println("Number of found songs: " + song.size());
      for (SongEntry s : song)
      {
        System.out.println(s.toString()); 
      }
   }

   public SongEntry findSongByTitle(String title)
   {   
      ArrayList<SongEntry> foundTitles = new ArrayList<>();
      SongEntry song = null;
      try
      {
         for (SongEntry s : tunes)
         {
            if (s.getTitle().equals(title))
            {
               song = s;
               foundTitles.add(s);
            }
         }
         printSubListTunes(foundTitles);
         return song;
      } catch (Exception e)
      {
         System.out.println("Title not found");
         return null;
      }

   }

   public void printSong(SongEntry song)
   {
      System.out.println(song.getTitle());
   }
   
 /*  public void findTimedPlayList(Integer duration)
   {
      Boolean foundSubSet = false;
      ArrayList<SongEntry> Col;
      int maxSize = 0;
      Col = new ArrayList<>();
      SongEntry emptySet = new SongEntry(null, 0, null, null);
      ArrayList<Integer> maxSubSet = null;
    
      Col.add(emptySet);
      for (SongEntry song : tunes)
      {
         if (foundSubSet == false)
         {
            int size = Col.size();
            for (int i = 0; i < size; i++)
            {
               ArrayList<SongEntry> newSubSet = (ArrayList<SongEntry>) Col.get(i)
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
   */
}
