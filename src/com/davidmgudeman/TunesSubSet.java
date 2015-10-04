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

   public TunesSubSet makeSubSet(TunesSubSet originalTunesSubSet,
         SongEntry newSong) throws CloneNotSupportedException
   {
      TunesSubSet newTunesSubSet = new TunesSubSet(originalStore);
      for (SongEntry s : originalTunesSubSet.currentSubSet)
      {
         SongEntry as = s;
         newTunesSubSet.currentSubSet.add(as);
      }
      newTunesSubSet.subSetDuration = originalTunesSubSet.subSetDuration
            + newSong.getDuration();

      newTunesSubSet.subSetSize = originalTunesSubSet.subSetSize + 1;
      newTunesSubSet.currentSubSet.add(newSong);
      // newSubSet.currentSubSet.add(newSong);
      System.out.println("INNNNNNN   TUNNESSUBSEETTT   "
            + newTunesSubSet.subSetSize);

      return newTunesSubSet;
   }

   public int getSubSetDuration()
   {
      return this.subSetDuration;
   }

   public TunesSubSet findSubSet(int duration)
   {
      Boolean foundSubSet = false;
      ArrayList<TunesSubSet> Col = new ArrayList<>();
      TunesSubSet maxSubSet = new TunesSubSet(originalStore);
      TunesSubSet emptySubSet = new TunesSubSet(originalStore);
      Col.add(emptySubSet);
      int size = 0;
      TunesSubSet subSet = new TunesSubSet(originalStore);
      
      for (SongEntry e : maxSubSet.tunesList)
      {         
         if (foundSubSet == false)
         {        
            for (int i = 0; i < Col.size();i ++)
            {
               System.out.println(" i "  + i + "; Col.size(): " + Col.size());
               Col.get(i);
                            
               if (Col.get(i).subSetDuration + e.getDuration() <= duration)
               {
                  try
                  {
                     subSet = (TunesSubSet) makeSubSet(Col.get(i), e);
                     subSet.printTunesSubSet();
                  } catch (CloneNotSupportedException e1)
                  {
                     System.out
                           .println("Clone didn;t happen findSubSet method");
                     e1.printStackTrace();
                  }
                  Col.add(subSet);
                  System.out.println("Col.size()" + Col.size());
                  maxSubSet = subSet;
               }
               if (Col.get(i).subSetDuration + e.getDuration() == duration)
               {
                  System.out.println("subset found");
                  foundSubSet = true;
                  return subSet;
               }
               size = Col.size();
            }
         }

      }
      return maxSubSet;
   }

   public void printTunesSubSet()
   {
      System.out.println("subset Size" + this.subSetSize);
      System.out.println("subset Duration" + this.subSetDuration);
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
