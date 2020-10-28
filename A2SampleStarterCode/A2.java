
import java.util.Scanner;
/** 
 * COMP 2503 Winter 2020 Assignment 2 
 * 
 * This program must read a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi, Sidhant Kaushik, Erika Robles
 * @date Fall 2020
*/

public class A2 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4; 
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	private SLL<Avenger> mentionList = new SLL<Avenger>();
	private SLL<Avenger> alphabticalList = new SLL<Avenger>();
	private SLL<Avenger> mostPopularList = new SLL<Avenger>(new MostFrequent());
	private SLL<Avenger> leastPopularList = new SLL<Avenger>(new LeastFrequent());
	
	public static void main(String[] args) {
		A2 a1 = new A2();
		a1.run();
	}

	public void run() {
		readInput();
		createdOrderedLists();
		printResults();
	}

	private void createdOrderedLists() {
		// TODO: 
		// Create a mover and traverse through the mentionList.
		// Add each avenger to the other three lists.
		for (int i= 0; i < mentionList.size(); i++){
			mostPopularList.addInOrder(mentionList.get(i));
		}
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 */
	private void readInput() {
		/*
		In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name then
				- Create a new avenger object with the corresponding alias and last name.
				- if this avenger has already been mentioned, increase the frequency count for the object already in the list.
				- if this avenger has not been mentioned before, add the newly created avenger to the end of the list, remember to set the frequency.
		*/ 
		while (input.hasNext()) {
			String word = cleanWord(input.next());

			if (word.length() > 0) {
				totalwordcount++;
			}
			
			if (!existsInRoster(word)) {

					Avenger a = createAvenger(word); //creates a new avenger object	

					if (a!= null) {
						
						Node<Avenger> aNode = new Node<Avenger>(a);
						
						if (listContains(a)) { //if avengersArrayList already had this avenger, increase freq
							increaseFreq(a);
						} else {
							a.setFreq();
							mentionList.addTail(aNode); // if not, add it to the list
						}
					}
			}
		}
		}
	
	private Avenger createAvenger(String input) {

		Avenger a = null;

		for (int r = 0; r < avengerRoster.length; r++) {

			for (int c = 0; c < 2; c++) {

				if (input.equals(avengerRoster[r][c])) {
					if (c == 1) { //if the input is an name
						a = new Avenger(input, avengerRoster[r][0]);
					} else if (c == 0) { //if the input is a last name
						a = new Avenger(avengerRoster[r][1], input);
					}
				}
			}

		}
		return a;
	}

	private boolean existsInRoster (String input){
		for(int i = 0; i < avengerRoster.length; i++){
			for(int j = 0; j < 2; j++){
				if(avengerRoster[i][j] == input) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean listContains(Avenger a) {
		Node<Avenger> mover = mentionList.getHead();
		
		while (mover != null) {
			if (mover.getData().getName().equals(a.getName()) || 
					mover.getData().getAlias().equals(a.getAlias())) { 
				return true;
			} else {
				mover = mover.getNext();
			}
			
		}
		
		return false;
	}
	
	private void increaseFreq(Avenger a) {
		Node<Avenger> mover = mentionList.getHead();
		
		while (mover != null) {
			if (mover.getData().getName().equals(a.getName()) || 
				mover.getData().getAlias().equals(a.getAlias())) { 
				mentionList.find(mover.getData()).getData().increaseFreq(); break;
			} else {
				mover = mover.getNext();
			}
			
		}

	}
	
	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: ");
		System.out.println(mentionList.size());

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		mentionList.printList();

		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output

		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		
		System.out.println();
	}
}
