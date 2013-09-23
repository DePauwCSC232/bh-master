package csc232;

/**
   CSC232A - Fall 2013
   The <code>Address</code> class represents a US mailing address (just the
   street address and city/state/ZIP, not the recipient).
   An address is immutable -- once constructed, the fields may not be changed.

   @author Brian Howard <bhoward@depauw.edu>
   @author Scott Thede <sthede@depauw.edu>
   @version 2013-09-23
*/
public class Address
{
   /**
      Construct an <code>Address</code> object given a street address, city,
      state, and ZIP code.

      @param street The street address (number and street name)
      @param city The name of the city
      @param state The two-letter state abbreviation
      @param zip The ZIP code as a five-digit string
   */
   public Address(String street, String city, String state, String zip)
   {
      this.street = street;
      this.city = city;
      this.state = state;
      this.zip = zip;
   }

   /**
      Override the default <code>toString</code> method to return the address
      formatted into two lines.
   */
   public String toString()
   {
      return street + "\n"
         + city + ", " + state + "  " + zip;
   }

   /**
      Get the street part from this address.
   */
   String getStreet()
   {
      return street;
   }

   /**
      Get the city name from this address.
   */
   String getCity()
   {
      return city;
   }

   /**
      Get the state abbreviation from this address.
   */
   String getState()
   {
      return state;
   }

   /**
      Get the ZIP code from this address.
   */
   String getZIP()
   {
      return zip;
   }

   private String street;
   private String city;
   private String state;
   private String zip;
}
