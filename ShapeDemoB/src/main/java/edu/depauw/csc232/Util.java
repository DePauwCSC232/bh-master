package edu.depauw.csc232;

public class Util {

   /**
    * Return a string consisting of n copies of the given string.
    * 
    * @param s
    * @param n
    * @return
    */
   public static String repeat(String s, int n) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < n; i++) {
         builder.append(s);
      }
      return builder.toString();
   }

}
