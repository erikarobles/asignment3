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
<<<<<<< Updated upstream
=======
		
>>>>>>> Stashed changes
		readInput();
		createdOrderedLists();
		printResults();
		
	}
	
	/**
	 * Traverses through mentionList and creates three other ordered lists.
	 * 
	 */

	private void createdOrderedLists() {

		for (int i= 0; i < mentionList.getSize()-1; i++) {
			alphabticalList.addInOrder(mentionList.get(i));
			mostPopularList.addInOrder(mentionList.get(i));
			leastPopularList.addInOrder(mentionList.get(i));
		}

	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 */
	private void readInput() {
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
	
	/**
	 * Helper method
	 * takes a users input, 
	 * if its a name, it finds the corresponding alias and vise versa
	 * then creates a new object with the corret name and alias 
	 * @param takes in the input, which is either a name, alias or invalid input
	 * @return returns the new Avenger object
	 **/
	
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
	
	/**
	 * Helper method
	 * checks if the given String input exists in the avengerRoster array
	 * @param takes in the input, which is either a name, alias or invalid input
	 * @return true, if the input is in the avengerRoster, false otherwise
	 */

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
	
	/**
	 * Helper method
	 * checks if the given Avenger input already exists in the mentionList
	 * @param takes in the Avenger to look in the list for
	 * @return true, if the input is in the mentionList, false otherwise
	 */

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
	
	/**
	 * Helper method
	 * looks for the given Avenger in mentionList, 
	 * then it takes that Avenger and increases its frequency by 1
	 * @param takes in the Avenger to look in the list for
	 */
	
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
	
	/**
	 * First, if there is an apostrophe, the substring
	 * before the apostrophe is used and the rest is ignored.
	 * Words are converted to all lower case.
	 * All other punctuation and numbers are skipped.
	 * @return the avenger's name or alias
	 */
	
	private String cleanWord(String next) {
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}
	
	/**
	 * takes a sorted list and displays either the first four,
	 * if the list contains less than four avengers, it displays the whole list
	 * @return a string of each avenger mentioned in the correct order
	 */
	private String printFour(SLL<Avenger> list) {
		
		String avengerList = "";
		if (list.getSize()-1 > topN) {
			for (int i= 0; i < topN; i++) {
				avengerList = avengerList + list.get(i).toString() + "\n";
			}
		} else {
			for(int i = 0; i < list.getSize()-1;i++) {
				avengerList = avengerList + list.get(i).toString() + "\n";
			}
		}
		
		return avengerList;
	}

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.print("Number of Avengers Mentioned: ");
		System.out.println(mentionList.getSize());
		System.out.println();

		System.out.print("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		mentionList.printList();
		System.out.println();
		
		System.out.print("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		System.out.println((mostPopularList));

		System.out.print("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		System.out.println(printFour(leastPopularList));

		System.out.print("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		alphabticalList.printList();
	}
}
