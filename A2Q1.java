import java.io.*;

public class A2Q1 {
	 public static void main(String[] args) {
		 // Easier to read both at once; since we can't return both arrays and/or sizes,
		 // all the processing will be done in the method processMedals
		 processMedals("a2a.txt");

		 System.out.println("\nEnd of processing.");
	 }

	 public static void processMedals(String filename) {
		 final int MAX_SIZE = 500;

		 BufferedReader in;
		 String line, line2;
		 Counter[] events, countries;
		 int eventsSize = 0, countriesSize = 0;
		 
		 events = new Counter[MAX_SIZE];
		 countries = new Counter[MAX_SIZE];
		 
		 try {
			 in = new BufferedReader(new FileReader(filename));
			 
			 line = in.readLine();
			 while (line != null) {
				 line2 = in.readLine();

				 countriesSize = addUnique(line, countries, countriesSize);
				 eventsSize = addUnique(line2, events, eventsSize);
				 
				 // The third line in the group (specific event) gets ignored
				 // Read it and toss it
				 line2 = in.readLine();
				 line = in.readLine();
			 }
			 
			 in.close();
		 } catch (IOException ioe) {
			 System.out.println(ioe.getMessage());
			 ioe.printStackTrace();
		 }
		 
		 System.out.println("Count of gold medallists by country:");
		 printList(countries, countriesSize);
		 System.out.println("\nCount of gold medallists by event type:");
		 printList(events, eventsSize);
	 }
	 
	 // Add a string to a list of counters, without duplicates
	 // (count up if the item is already in the list)
	 public static int addUnique(String value, Counter[] list, int size) {
		 for (int i = 0; i < size; i++) {
			 if (list[i].match(value)) {
				 // Found a match
				 list[i].increase();
				 return size;
			 }
		 }
		 
		 // No match found
		 list[size] = new Counter(value);
		 return size + 1;
	 }

	 // Print out a partially-filled array of counters
	 public static void printList(Counter[] list, int size) {
			for (int i = 0; i < size; i++) {
				 System.out.println(list[i]);
			}
	 }
}

class Counter {
	// Instance variables
	private String text;
	private int count;

	// Constructor
	public Counter(String text) {
		this.text = text;
		this.count = 1;
	}

	// Match a string
	public boolean match(String text) {
		return text.equals(this.text);
	}

	// Increase the count
	public void increase() {
		count++;
	}
	
	// Retrieve the count
	public int getCount() {
		return count;
	}

	// Instance methods
	public String toString() {
		return text + " - " + count;
	}
}