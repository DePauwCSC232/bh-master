/**
CSC232 - Spring 2014
A facade around a Reader and a Writer to match the IOModel interface.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-04-11
 */

package csc232.ui;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import acm.io.IOModel;

public class ReaderWriterIOModel implements IOModel
{

   public ReaderWriterIOModel(Reader in, Writer out)
   {
      this.in = new Scanner(in);
      this.out = new PrintWriter(out, true); // automatically flush on println
   }

   public void print(String s)
   {
      out.print(s);
   }

   public void print(boolean b)
   {
      out.print(b);
   }

   public void print(char c)
   {
      out.print(c);
   }

   public void print(double d)
   {
      out.print(d);
   }

   public void print(float f)
   {
      out.print(f);
   }

   public void print(int i)
   {
      out.print(i);
   }

   public void print(long l)
   {
      out.print(l);
   }

   public void print(Object o)
   {
      out.print(o);
   }

   public void println()
   {
      out.println();
   }

   public void println(String s)
   {
      out.println(s);
   }

   public void println(boolean b)
   {
      out.println(b);
   }

   public void println(char c)
   {
      out.println(c);
   }

   public void println(double d)
   {
      out.println(d);
   }

   public void println(float f)
   {
      out.println(f);
   }

   public void println(int i)
   {
      out.println(i);
   }

   public void println(long l)
   {
      out.println(l);
   }

   public void println(Object o)
   {
      out.println(o);
   }

   public final String readLine()
   {
      return readLine(null);
   }

   public String readLine(String prompt)
   {
      if (prompt != null)
      {
         print(prompt);
         out.flush();
      }
      return in.nextLine();
   }

   public final int readInt()
   {
      return readInt(null, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }

   public final int readInt(int low, int high)
   {
      return readInt(null, low, high);
   }

   public final int readInt(String prompt)
   {
      return readInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }

   public int readInt(String prompt, int low, int high)
   {
      String msg = null;
      while (true)
      {
         String line = readLine(prompt);
         try
         {
            int n = Integer.parseInt(line);
            if (n >= low && n <= high)
            {
               return n;
            }
            msg = "Value is outside the range [" + low + ":" + high + "]";
         }
         catch (NumberFormatException ex)
         {
            msg = "Illegal numeric format";
         }
         showErrorMessage(msg);
         if (prompt == null)
         {
            prompt = "Retry: ";
         }
      }
   }

   public final double readDouble()
   {
      return readDouble(null, Double.NEGATIVE_INFINITY,
               Double.POSITIVE_INFINITY);
   }

   public final double readDouble(double low, double high)
   {
      return readDouble(null, low, high);
   }

   public final double readDouble(String prompt)
   {
      return readDouble(prompt, Double.NEGATIVE_INFINITY,
               Double.POSITIVE_INFINITY);
   }

   public double readDouble(String prompt, double low, double high)
   {
      String msg = null;
      while (true)
      {
         String line = readLine(prompt);
         try
         {
            double d = Double.valueOf(line).doubleValue();
            if (d >= low && d <= high)
            {
               return d;
            }
            msg = "Value is outside the range [" + low + ":" + high + "]";
         }
         catch (NumberFormatException ex)
         {
            msg = "Illegal numeric format";
         }
         showErrorMessage(msg);
         if (prompt == null)
         {
            prompt = "Retry: ";
         }
      }
   }

   public final boolean readBoolean()
   {
      return readBoolean(null);
   }

   public final boolean readBoolean(String prompt)
   {
      return readBoolean(prompt, "true", "false");
   }

   public boolean readBoolean(String prompt, String trueLabel, String falseLabel)
   {
      while (true)
      {
         String line = readLine(prompt);
         if (line.equalsIgnoreCase(trueLabel))
         {
            return true;
         }
         else if (line.equalsIgnoreCase(falseLabel))
         {
            return false;
         }
         else
         {
            showErrorMessage("Illegal boolean format");
            if (prompt == null)
            {
               prompt = "Retry: ";
            }
         }
      }
   }

   public void showErrorMessage(String message)
   {
      out.println(message);
   }

   private Scanner in;
   private PrintWriter out;
}
