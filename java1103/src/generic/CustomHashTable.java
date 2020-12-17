package generic;

/**
 * An implementation of hash tables from scratch Creates a hash table in which
 * both the keys and the values are of type String.
 * 
 * @author ericel123
 *
 * @param <K>
 * @param <V>
 */
public class CustomHashTable<K, V> {

	private Entry<K, V>[] hashTable;

	// Map capacity.
	// Do not have to worry about increasing the size of the table
	private int capacity = 10;

	// Couldn't figure any better way to suppress the warning
	@SuppressWarnings("unchecked")
	public CustomHashTable() {
		hashTable = new Entry[capacity];

	}

	// Just as the name says entry to our HashMap
	// Should hold a Key/Value pair
	// Remember a Key can never be null, but a value can
	static class Entry<K, V> {
		K key;

		V value;

		Entry<K, V> next;

		public Entry(K key, V value, Entry<K, V> next) {

			this.key = key;

			this.value = value;

			this.next = next;

		}

	}

	// Adds a new entry to our HashMap
	// Remember a Key can never be null, but a value can
	public void put(K key, V value) {
		// If key is null, then return
		// Key cannot be null
		if (key == null)

			return;

		// calculate hash of key.
		int hash = hash(key);

		// creates new entry.
		Entry<K, V> newEntry = new Entry<K, V>(key, value, null);

		// if empty has no entry at location, store new entry.
		if (hashTable[hash] == null) {
			hashTable[hash] = newEntry;

		} else {

			Entry<K, V> previous = null;

			Entry<K, V> current = hashTable[hash];
			// we have reached last entry of bucket.
			while (current != null) {
				if (current.key.equals(key)) {
					// If map/table is empty,
					// add entry(node) as first node.
					if (previous == null) {
						newEntry.next = current.next;
						hashTable[hash] = newEntry;

						return;
					} else {
						newEntry.next = current.next;
						previous.next = newEntry;
						return;
					}
				}
				previous = current;
				current = current.next;

			}
			previous.next = newEntry;
		}

	}

	// Returns value of given key
	// if key doesn't exist, return null
	// if key exist, but no value, returns a null value
	public V get(K key) {
		int hash = hash(key);

		if (hashTable[hash] == null) {
			return null;
		} else {
			Entry<K, V> temp = hashTable[hash];
			while (temp != null) {
				if (temp.key.equals(key))
					return temp.value;
				// return value corresponding to key.
				temp = temp.next;
			}
			// returns null if no matching key
			return null;

		}

	}

	// Removes an entry from table
	// By a given key
	public boolean remove(K deleteKey) {
		int hash = hash(deleteKey);
		if (hashTable[hash] == null) {
			return false;
		} else {
			Entry<K, V> previous = null;
			Entry<K, V> current = hashTable[hash];
			// we have reached last entry node of map/table.
			while (current != null) {

				if (current.key.equals(deleteKey)) {
					// delete first entry node.
					if (previous == null) {

						hashTable[hash] = hashTable[hash].next;

						return true;
					} else {

						previous.next = current.next;

						return true;
					}
				}
				previous = current;

				current = current.next;
			}
			return false;

		}

	}

	public void display() {
		for (int i = 0; i < capacity; i++) {

			if (hashTable[i] != null) {

				Entry<K, V> entry = hashTable[i];

				while (entry != null) {

					System.out.print("{" + entry.key + "=" + entry.value + "}" + " ");

					entry = entry.next;

				}

			}
		}

	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	// Returns size of objects in collection
	public int size() {
		int count = 0;
		for (int i = 0; i < capacity; i++) {
			if (hashTable[i] != null) {
				Entry<K, V> entry = hashTable[i];
				while (entry != null) {
					entry = entry.next;
					count++;
				}
			}
		}
		return count;

	}
}
