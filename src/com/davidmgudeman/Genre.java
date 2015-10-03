package com.davidmgudeman;
import java.util.ArrayList;

import cs1c.SongEntry;

public class Genre
{
   //public enum genre {soul_and_reggae, classic_pop_and_rock, 
  //    dance_and_electronica, folk, punk, metal, jazz_and_blues}
  
   
   public int index = 0;
   
    
   public Genre(FoothillTunesStore store)
   {
      
      ArrayList<SongEntry> tunes = store.getListOfSongs();
      ArrayList<SongEntry> classical = new ArrayList<>();
      ArrayList<SongEntry> soul = new ArrayList<>();
      ArrayList<SongEntry> rock = new ArrayList<>();
      ArrayList<SongEntry> dance = new ArrayList<>();
      ArrayList<SongEntry> folk = new ArrayList<>();
      ArrayList<SongEntry> punk = new ArrayList<>();
      ArrayList<SongEntry> metal = new ArrayList<>();
      ArrayList<SongEntry> jazz = new ArrayList<>();
      
      for (SongEntry s : tunes)
      { 
         
        if (s.getGenre().equals("classical"))
         {
           
            classical.add(s);  
         }
         else if (s.getGenre() == "pop and rock")
        {
           rock.add(s);
        }
         else if (s.getGenre() == "dance and electronica")
         {
            dance.add(s);
         }
         else if (s.getGenre() == "folk")
         {
            folk.add(s);
         }
         else if (s.getGenre() == "punk")
         {
            punk.add(s);
         }
         else if (s.getGenre() == "metal")
         {
            metal.add(s);
         } else if (s.getGenre() == "jazz and blues")
         {
            jazz.add(s);
         }
      }
 /*     System.out.println("I GOT HERE");
         System.out.println("CLASSICAL SIZE " + classical.size());
         System.out.println("tunes " + tunes.size());
         printArrayListTunes(classical);
         printArrayListTunes(rock);
         printArrayListTunes(dance);
         printArrayListTunes(folk);
         printArrayListTunes(punk);
         printArrayListTunes(metal);
         printArrayListTunes(jazz);
      */
   }
   public void printArrayListTunes(ArrayList<SongEntry> list)
   {
      int counter = 1;
      for (SongEntry s : list)
      {
      
         System.out.println(s.getTitle() + ", " + s.getArtistName() + ", " +  s.getGenre() + ", " + s.getDuration());
         System.out.println();
         counter++;
      }
      System.out.println("The list has " + counter + " elements.");
   }
   
  
}
