package generic;

public class CustomHashTableTest {
	public static void main(String[] args) {
		CustomHashTable<String, String> hashMapCustom = new CustomHashTable<String, String>();
		hashMapCustom.put("21", "12");
		hashMapCustom.put("25", "121");
		hashMapCustom.put("30", "151");
		hashMapCustom.put("33", "15");
		hashMapCustom.put("35", "89");


		System.out.println("key 21="
		+ hashMapCustom.get("21"));
		System.out.println("key 51="
		+ hashMapCustom.get("51"));

		System.out.print("Display Data : ");
		hashMapCustom.display();

		System.out.println("key 21 removed: "
		+ hashMapCustom.remove("21"));
		System.out.println("key 51 removed: "
		+ hashMapCustom.remove("51"));

		System.out.print("Display Data : ");
		hashMapCustom.display();

		}

}
