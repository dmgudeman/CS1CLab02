package com.davidmgudeman;

import java.util.ArrayList;

import cs1c.SongEntry;

public class TunesSubSet
{
   static FoothillTunesStore originalStore;
   ArrayList<SongEntry> songEntryList;
   private int subSetDuration = 0;
   ArrayList<SongEntry> currentSubSet = null;
   int subSetSize = 0;

   public TunesSubSet(FoothillTunesStore originalStore)
   {
      super();
      this.originalStore = originalStore;
      songEntryList = originalStore.tunes;
      currentSubSet = new ArrayList<>();
      subSetDuration = 0;
      subSetSize = 0;

   }

   public boolean avoidDuplicates(ArrayList<SongEntry> list, SongEntry song)
   {
      if (list.contains(song))
      {
         return true;
      }
      return false;
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

      return newTunesSubSet;

   }

   public double getSubSetDuration()
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

      TunesSubSet subSet = new TunesSubSet(originalStore);

   for (SongEntry e : originalStore.tunes)
      {
         for (int i = 0; i < Col.size(); i++)
            {
            if (foundSubSet == false)
            {
    
               Col.get(i);

               if (Col.get(i).subSetDuration + e.getDuration() <= duration
                     && !avoidDuplicates(Col.get(i).currentSubSet, e))
               {
                  try
                  {
                     subSet = (TunesSubSet) makeSubSet(Col.get(i), e);
                    
                  } catch (CloneNotSupportedException e1)
                  {
                     System.out
                           .println("Clone didn;t happen findSubSet method");
                     e1.printStackTrace();
                  }
                  Col.add(subSet);
                  if (subSet.subSetDuration> maxSubSet.subSetDuration && subSet.subSetDuration < duration)
                  maxSubSet = subSet;
               }
               else if (Col.get(i).subSetDuration + e.getDuration() == duration)
               {
                //  System.out.println("SUBSET FOUND");              
               ////   System.out.println("SUBSET DURATION =" + Col.get(i).subSetDuration + e.getDuration());
               //   System.out.println("NNNEWW SONNG" + e.getDuration());
              //    System.out.println("DDDDDDDUUUUURRRRRRAAAAATTTTION" + Col.get(i).subSetDuration);
                  subSet.subSetDuration = Col.get(i).subSetDuration
                        + e.getDuration();
                  
              //    System.out.println("VVVVVVVVVVVVVVVV = "+ subSet.subSetDuration);
                  foundSubSet = true;
                  printTunesSubSet(subSet);
                  return subSet;
               }            
            }
         }

      }
      return maxSubSet;
   }

   public void printTunesSubSet(TunesSubSet tunesSubSet)
   {  System.out.println("subset Duration " + (tunesSubSet.subSetDuration/60) + " minutes.");
      System.out.println("subset Size " + tunesSubSet.subSetSize);
    
      try
      {
         for (SongEntry s : tunesSubSet.currentSubSet)

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
