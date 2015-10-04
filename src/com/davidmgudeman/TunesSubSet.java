package com.davidmgudeman;

import java.util.ArrayList;

import cs1c.SongEntry;

public class TunesSubSet
{
   static FoothillTunesStore originalStore;
   ArrayList<SongEntry> tunesList;
   private Integer subSetDuration = 0;
   ArrayList<SongEntry> currentSubSet = null;
   int subSetSize = 0;

   public TunesSubSet(FoothillTunesStore originalStore)
   {
      super();
      this.originalStore = originalStore;
      tunesList = originalStore.getListOfSongs();
      currentSubSet = new ArrayList<>();
      subSetDuration = 0;
      subSetSize = 0;

   }

   public Object makeSubSet(TunesSubSet originalSubSet, SongEntry newSong)
         throws CloneNotSupportedException
   {
      TunesSubSet newSubSet = new TunesSubSet(originalStore);
      ArrayList<SongEntry> oldSongEntryList = new ArrayList<>();
      oldSongEntryList = originalSubSet.currentSubSet;
      newSubSet.subSetDuration = originalSubSet.subSetDuration
            + newSong.getDuration();
      oldSongEntryList.add(newSong);
      int subSetSize = oldSongEntryList.size();
      newSubSet.currentSubSet = originalSubSet.currentSubSet;
      newSubSet.currentSubSet.add(newSong);
      newSubSet.subSetSize = originalSubSet.subSetSize + 1;

      return newSubSet;
   }

   public int getSubSetDuration()
   {
      return this.subSetDuration;
   }

   public TunesSubSet findSubSet(int duration)
   {
      ArrayList<TunesSubSet> Col = new ArrayList<>();
      TunesSubSet maxSubSet = new TunesSubSet(originalStore);
      TunesSubSet emptySubSet = new TunesSubSet(originalStore);
      Col.add(emptySubSet);

      for (SongEntry e : originalStore.getListOfSongs())
      {
         TunesSubSet subSet = new TunesSubSet(originalStore);
         int size = Col.size();
         for (int i= 0; i < size; i++)
         {
            if (Col.get(i).subSetDuration + e.getDuration() <= duration)
            {
               try
               {
                  subSet = (TunesSubSet) makeSubSet(Col.get(i), e);
                  System.out.println("subset.subSetDuration = " + subSet.subSetDuration);
               } catch (CloneNotSupportedException e1)
               {
                  System.out.println("Clone didn;t happen findSubSet method");
                  e1.printStackTrace();
               }
               Col.add(subSet);
               maxSubSet = subSet;
            }
            if (Col.get(i).subSetDuration + e.getDuration() == duration)
            {
               System.out.println("subset found");
               return subSet;
            }
         }
      }
      return maxSubSet;
   }

   public void printTunesSubSet()
   {
      try
      {
         for (SongEntry s : currentSubSet)

            System.out.println(s.toString());
      } catch (NullPointerException e)
      {
         System.out.println("No subset found");
      }
   }

   public int getSize()
   {
      return subSetSize;
   }

}
