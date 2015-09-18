import static org.junit.Assert.*;

import org.junit.Test;

public class HW1Test
{
   /**
    * Test some short palindromes.
    */
   @Test
   public void testIsPalindrome()
   {
      assertTrue(HW1.isPalindrome("dad"));
      assertTrue(HW1.isPalindrome("mom"));
      assertTrue(HW1.isPalindrome("aa"));
      assertTrue(HW1.isPalindrome("noon"));
      assertTrue(HW1.isPalindrome("radar"));
   }

   /**
    * Test some longer palindromes.
    */
   @Test
   public void testIsPalindrome2()
   {
      assertTrue(HW1.isPalindrome("racecar"));
      assertTrue(HW1.isPalindrome("able was I ere I saw elba"));
   }

   /**
    * Test some non palindromes.
    */
   @Test
   public void testIsPalindrome3()
   {
      assertFalse(HW1.isPalindrome("moon"));
      assertFalse(HW1.isPalindrome("modem"));
      assertFalse(HW1.isPalindrome("Able was I ere I saw Elba"));
   }

   /**
    * Test some edge cases.
    */
   @Test
   public void testIsPalindrome4()
   {
      assertTrue(HW1.isPalindrome(""));
      assertTrue(HW1.isPalindrome("a"));
      assertTrue(HW1.isPalindrome(
               "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
      assertTrue(HW1.isPalindrome(
               "abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba"));
      assertTrue(HW1.isPalindrome(
               "abcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcba"));
   }
}
