package com.davidmgudeman;

import java.util.ArrayList;

import cs1c.SongEntry;

public class Genre
{
   // public enum genre {soul_and_reggae, classic_pop_and_rock,
   // dance_and_electronica, folk, punk, metal, jazz_and_blues}

   public int index = 0;

   ArrayList<SongEntry> classical = new ArrayList<>();
   ArrayList<SongEntry> soul = new ArrayList<>();
   ArrayList<SongEntry> rock = new ArrayList<>();
   ArrayList<SongEntry> dance = new ArrayList<>();
   ArrayList<SongEntry> folk = new ArrayList<>();
   ArrayList<SongEntry> punk = new ArrayList<>();
   ArrayList<SongEntry> metal = new ArrayList<>();
   ArrayList<SongEntry> jazz = new ArrayList<>();

   public Genre(FoothillTunesStore store)
   {

      ArrayList<SongEntry> tunes = store.tunes;

      for (SongEntry s : tunes)
      {

         if (s.getGenre().equals("classical"))
         {

            classical.add(s);
         } else if (s.getGenre().equals("classic pop and rock"))
         {
            rock.add(s);
         } else if (s.getGenre().equals("dance and electronica"))
         {
            dance.add(s);
         } else if (s.getGenre().equals("folk"))
         {
            folk.add(s);
         } else if (s.getGenre().equals("punk"))
         {
            punk.add(s);
         } else if (s.getGenre().equals("metal"))
         {
            metal.add(s);
         } else if (s.getGenre().equals("jazz and blues"))
         {
            jazz.add(s);
         }
      }
   }

   public void printArrayListTunes(ArrayList<SongEntry> list)
   {
      int counter = 1;
      for (SongEntry s : list)
      {
         System.out.println(s.getTitle() + ", " + s.getArtistName() + ", "
               + s.getGenre() + ", " + s.getDuration());
         counter++;
      }
      System.out.println("The list has " + counter + " elements.");
   }

   public void printByGenre()
   {
      System.out.println("CLASSICAL:_________________________________");
      printArrayListTunes(classical);
      System.out.println();
      System.out.println("ROCK:_________________________________");
      printArrayListTunes(rock);
      System.out.println();
      System.out.println("DANCE AND ELECTRONIC___________________");
      printArrayListTunes(dance);
      System.out.println();
      System.out.println("FOLK:_________________________________");
      printArrayListTunes(folk);
      System.out.println();
      System.out.println("PUNK:_________________________________");
      printArrayListTunes(punk);
      System.out.println();
      System.out.println("METAL:_________________________________");
      printArrayListTunes(metal);
      System.out.println();
      System.out.println("JAZZ:_________________________________");
      printArrayListTunes(jazz);
      System.out.println();
   }

}
