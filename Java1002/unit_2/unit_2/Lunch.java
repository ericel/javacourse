package unit_2;

import java.util.*;


abstract class Food {
    abstract void printFlavor();
}
class Pepper extends Food {
    void printFlavor() { System.out.println("spicy"); }
}
public class Lunch {
	 static int sum = 0;
	    static void add() { sum++; }
	    public static void main(String[] args) {
	        for (int i = 0; i < 100; i++) add();
	        System.out.println(sum);
	    }
}