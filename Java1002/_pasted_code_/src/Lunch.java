class Food {
    Food() { System.out.println("bland"); }
}
class Pepper extends Food {
    Pepper() { this("spicy"); }
    Pepper(String flavor) { System.out.println(flavor); }
}
public class Lunch {
    public static void main(String[] args) {
        Food lunch = new Pepper();
    }
}