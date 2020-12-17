import java.util.ArrayList;

public class SampleTest {
	public static void main(String args[]) {
		ArrayList<Sample> list = new ArrayList();
		Sample sample1 = new Sample(1);
		Sample sample2 = new Sample(2);
		list.add(sample1);
		Boolean contains;
		contains = list.contains(sample2);
		System.out.println("Contains: " + contains);

		String a = "abc";
		String b = "abc";
		if (a == b) {
			// The if statement will evaluate to true,
			// if your JVM string interns a and b,
			// otherwise, it evaluates to false.
			System.out.println("true");
		}

		if (a.equals(b)) {
			// The if statement will evaluate to true,
			// if your JVM string interns a and b,
			// otherwise, it evaluates to false.
			System.out.println("ok");
		}
	}
}
