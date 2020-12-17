package unit4;

import java.util.TreeSet;

import lab1.TextIO;

/**
 * Solutions for Exercises Unit 4

This is a non-grade exercise which should be posted to your learning journal.

In mathematics, several operations are defined on sets. The union of two sets A and B is a set that contains all the elements that are in A together with all the elements that are in B. The intersection of A and B is the set that contains elements that are in both A and B. The difference of A and B is the set that contains all the elements of A except for those elements that are also in B.

Suppose that A and B are variables of type set in Java. The mathematical operations on A and B can be computed using methods from the Set interface. In particular: A.addAll(B) computes the union of A and B; A.retainAll(B) computes the intersection of A and B; and A.removeAll(B) computes the difference of A and B. (These operations change the contents of the set A, while the mathematical operations create a new set without changing A, but that difference is not relevant to this exercise.)

For this exercise, you should write a program that can be used as a "set calculator" for simple operations on sets of non-negative integers. (Negative integers are not allowed.) A set of such integers will be represented as a list of integers, separated by commas and, optionally, spaces and enclosed in square brackets. For example: [1,2,3] or [17, 42, 9, 53,108]. The characters +, *, and - will be used for the union, intersection, and difference operations. The user of the program will type in lines of input containing two sets, separated by an operator. The program should perform the operation and print the resulting set. Here are some examples:

Input                                                 Output
-------------------------                         -------------------
[1, 2, 3] + [3, 5, 7] [                          1, 2, 3, 5, 7]
[10,9,8,7] * [2,4,6,8]                         [8]
[ 5, 10, 15, 20 ] - [ 0, 10, 20 ]            [5, 15]

To represent sets of non-negative integers, use sets of type TreeSet<Integer>. Read the user's input, create two TreeSets, and use the appropriate TreeSet method to perform the requested operation on the two sets. Your program should be able to read and process any number of lines of input. If a line contains a syntax error, your program should not crash. It should report the error and move on to the next line of input. (Note: To print out a Set, A, of Integers, you can just say System.out.println(A). Here, the syntax for sets to be the same as that used by the system for outputting a set.)

Discussion
This program would be much easier to write if we could assume that the user's input was in the correct format. Unfortunately, the exercise says that we have to detect errors in the user's input and handle them by printing error messages. To do this we have to constantly look ahead while reading the user's input, to see whether the input agrees with what we are expecting. The techniques for doing this were covered for the example LengthConverter2.java from section 8.2.2 of the textbook. We can use the function TextIO.peek() to look ahead at the next character in the user's input. We need to be able to skip past any blanks in the input. Instead of writing my own method to do that, I use the method TextIO.skipBlanks() in my program.

My program uses exceptions to handle the errors. Exceptions make it possible to organize the error-handling code in a straightforward way. When an error is discovered, an exception is thrown. The main program uses a try..catch statement to try to process one line of input. If an error occurs, the program does not crash. The error is caught, an error message is printed, and the program continues. I throw an error of type IllegalArgumentException when an error is found. This exception is a standard part of Java, but it might be better style to define a new error class to represent the specific type of error that occurs in this program.

My program uses a method named calc() to read and process one line of input. This method in turn uses another method, readSet() to read each of the two sets of integers from the input line. Without error handling, an algorithm for readSet() would be:

Start with an empty set.
Read the '[' that begins the set.
Repeat:
      Read the next number and add it to the set.
      If the next character is ']':
         break.
      Read the comma that separates one number from the next.
Read the ']'.
Return the set.

To turn this into a robust routine, we just have to check, before reading anything, that the next character is legal. If not, throw an exception. This adds a lot of code, but the nice thing about throwing exceptions is that it doesn't disturb the logical flow of the routine.

There is one possible bug in the algorithm. It assumes that there is at least one number in the set. But in mathematics, a set can be empty. I decided to allow for the possibility of empty sets in my program. See the solution, below.

An algorithm for the calc() method is even more straightforward. Again, the basic algorithm ignores the possibility of errors:

Read the first set, A.
Read the operator.
Read the second set, B.
if the operator is '+'
   A.addAll(B) // Sets A equal to the union of A and B.
else if the operator is '*'
   A.retainAll(B) // Sets A to the intersection.
else
   A.removeAll(B) // Sets A to the difference.
Print A.

In the program, an error check has to be added to make sure that there is a legal operator in the correct position. I also add an error check to make sure that there is no extra data on the line.


 * This program is a very simple "set calculator" that can compute
 * the intersection, union, and set difference of two sets of
 * non-negative integers.  Each line of the user's input contains two
 * such sets, separated by one of the operators +, *, or -, standing
 * for union, intersection, and set difference respectively.  A set
 * must be given in the form of a list of non-negative integers, separated
 * by commas, and enclosed in square brackets.  For example:
 * [1, 2, 3] + [4, 3, 10, 0].  Spaces can occur anywhere in the input.
 * If an error is found in the input, the program will report it.
 * The program ends when the user inputs an empty line.
 *
 * This program is mainly a demonstration of Sets.
 */

public class SetCalc {


   public static void main(String[] args) {

      System.out.println("This program will compute union, intersection,");
      System.out.println("and set difference of sets of integers.");
      System.out.println("");
      System.out.println("");
      System.out.println("Enter set computations (press return to end):");

      while (true) {
         char ch;
         System.out.print("\n? ");
         TextIO.skipBlanks();
         if (TextIO.peek() == '\n') {
               // The input line is empty. 
               // Exit the loop and end the program.
            break;
         }
         try {
            calc(); // Reads and processes one line of input.
         }
         catch (IllegalArgumentException e) {
               // An error was found in the input line.
            System.out.println("Error in input: " + e.getMessage());
            System.out.print("Discarding input: ");
         }
         do {  // Copy extra characters on line to output.  If there
               // was no error, then the only character that is copied
               // is the end-of-line character.
            ch = TextIO.getAnyChar();
            System.out.print(ch);
         } while (ch != '\n');
      }

   } // end main()


   /**
    * Read a line of input, consisting of two sets separated by
    * an operator.  Perform the operation and output the value.
    * If any syntax error is found in the input, an
    * IllegalArgumentException is thrown
    */
   private static void calc() {

      TreeSet<Integer> A, B;  // The two sets of integers.
      char op;                // The operator, +, *, or -.

      A = readSet(); // Read the first set.

      TextIO.skipBlanks();
      if (TextIO.peek() != '*' && TextIO.peek() != '+'
                                         && TextIO.peek() != '-')
         throw new IllegalArgumentException(
         "Expected *, +, or  - after first set.");
      op = TextIO.getAnyChar(); // Read the operator.

      B = readSet(); // Read the second set.

      TextIO.skipBlanks();
      if (TextIO.peek() != '\n')
         throw new IllegalArgumentException("Extra unexpected input.");

      // Perform the operation.  This modifies set A to represent
      // the answer.  Display the answer by printing out A. The
      // output format for a set of integers is the same as the
      // input format used by this program.

      if (op == '+')
         A.addAll(B);     // Union.
      else if (op == '*')
         A.retainAll(B);  // Intersection.
      else
         A.removeAll(B);  // Set difference.
     
      System.out.print("Value:  " + A);

   } // end calc()


   /**
    * Reads a set of non-negative integers from standard input,
    * and stores them in a TreeSet that contains objects belonging
    * to the wrapper class Integer.  The set must be enclosed
    * between square brackets and must contain a list of zero or
    * more non-negative integers, separated by commas.  Spaces
    * are allowed anywhere.  If the input is not of the correct
    * form, an IllegalArgumentException is thrown.
    */
   private static TreeSet<Integer> readSet() {

      TreeSet<Integer> set = new TreeSet<Integer>();  // The set that will be read.

      TextIO.skipBlanks();
      if (TextIO.peek() != '[')
         throw new IllegalArgumentException("Expected '[' at start of set.");
      TextIO.getAnyChar(); // Read the '['.

      TextIO.skipBlanks();
      if (TextIO.peek() == ']') {
            // The set has no integers.  This is the empty set, which
            // is legal.  Return the value.
         TextIO.getAnyChar(); // Read the ']'.
         return set;
      }

      while (true) {
            // Read the next integer and add it to the set.
         TextIO.skipBlanks();
         if (! Character.isDigit(TextIO.peek()))
            throw new IllegalArgumentException("Expected an integer.");
         int n = TextIO.getInt(); // Read the integer.
         set.add( new Integer(n) );
         TextIO.skipBlanks();
         if (TextIO.peek() == ']')
            break;  // ']' marks the end of the set.
         else if (TextIO.peek() == ',')
            TextIO.getAnyChar(); // Read a comma and continue.
         else
            throw new IllegalArgumentException("Expected ',' or ']'.");
      }

      TextIO.getAnyChar(); // Read the ']' that ended the set.

      return set;

   } // end readSet()


} // end class SetCalc