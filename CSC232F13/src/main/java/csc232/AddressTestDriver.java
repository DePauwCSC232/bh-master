/**
   CSC232A - Fall 2013
   A simple program to test the <code>Address</code> class.
   
   @author Brian Howard <bhoward@depauw.edu>
   @author Scott Thede <sthede@depauw.edu>
   @version 2013-09-23
*/

package csc232;

/**
   Create two {@link Address} objects and print them out.
*/
public class AddressTestDriver
{
   public static void main(String[] args)
   {
      Address address1 = new Address("602 S. College Ave.", "Greencastle",
               "IN", "46135");
      Address address2 = new Address("36 Main St.", "Agawam", "MA", "01001");

      System.out.println(address1);
      System.out.println();
      System.out.println(address2);
   }
}
