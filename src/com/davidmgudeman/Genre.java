package com.davidmgudeman;
import java.util.ArrayList;

import cs1c.SongEntry;

public class Genre<T extends ArrayList>
{
   //public enum genre {soul_and_reggae, classic_pop_and_rock, 
  //    dance_and_electronica, folk, punk, metal, jazz_and_blues}
  
   public enum genre {SOUL, ROCK, DANCE, FOLK, PUNK, METAL, JAZZ}
   public int index = 0;
   
    
   public Genre()
   {
      
      ArrayList<genre> songs = new ArrayList<>();   
   }
   
   public void populateArrayList(String genreType, 
         FoothillTunesStore store)
   { 
      ArrayList<SOUL> soul = new ArrayList<>();
      for (SongEntry s : store.getListOfSongs())
      {
      
         if (s.getGenre() == "soul and reggae")
         {
            
         }
         
      }
   }  
}
