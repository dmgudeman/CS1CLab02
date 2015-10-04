package com.davidmgudeman;

/**
 * Class was designed to facilitate managing items such as total duration of 
 * subsets so as not to have to compute this every time. The objects carry a 
 * complete list of tunes from the store as well as duration of the subset and 
 * number of elements in the subset.
 */
import java.util.ArrayList;

import cs1c.SongEntry;

public class TunesSubSet
{
   static FoothillTunesStore originalStore;
   ArrayList<SongEntry> songEntryList;
   private int subSetDuration = 0;
   ArrayList<SongEntry> currentSubSet = null;
   int subSetSize = 0;

   /**
    * Constructs a Subset containing an ArrayList of SongEntry.
    * 
    * @param originalStore
    */
   public TunesSubSet(FoothillTunesStore originalStore)
   {
      super();
      this.originalStore = originalStore;
      songEntryList = originalStore.tunes;
      currentSubSet = new ArrayList<>();
      subSetDuration = 0;
      subSetSize = 0;
   }

   /**
    * I had a lot of duplicates so I put this work around in.
    * 
    * @param list
    * @param song
    * @return
    */
   public boolean avoidDuplicates(ArrayList<SongEntry> list, SongEntry song)
   {
      if (list.contains(song))
      {
         return true;
      }
      return false;
   }

   /**
    * Takes a TunesSubSet and a new SongEntry. It makes a copy of the
    * TunesSubSet and adds the SongEntry to it to make the augmented subset.
    * 
    * @param originalTunesSubSet
    * @param newSong
    * @return
    * @throws CloneNotSupportedException
    */
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

   /**
    * returns the subsetDuration
    */
   public double getSubSetDuration()
   {
      return this.subSetDuration;
   }

   /**
    * This is the workhorse class to find the subset with the closest duration
    * desired by the user. The desired duration is put in throught the menu and
    * console.
    * 
    * @param duration
    * @return
    */
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
                  if (subSet.subSetDuration > maxSubSet.subSetDuration
                        && subSet.subSetDuration < duration)
                     maxSubSet = subSet;
               } else if (Col.get(i).subSetDuration + e.getDuration() == duration)
               {

                  subSet.subSetDuration = Col.get(i).subSetDuration
                        + e.getDuration();

                  foundSubSet = true;
                  printTunesSubSet(subSet);
                  return subSet;
               }
            }
         }

      }
      return maxSubSet;
   }

   /**
    * helper class to print out a TunesSubSet object.
    * 
    * @param tunesSubSet
    */
   public void printTunesSubSet(TunesSubSet tunesSubSet)
   {
      System.out.println("subset Duration " + (tunesSubSet.subSetDuration / 60)
            + " minutes.");
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

   /**
    * getter for subset size.
    * 
    * @return
    */
   public int getSize()
   {
      return subSetSize;

   }

}
