package lab5;

//Name:Leonidas Karnesis
// USC NetID:karnesis
// CSCI455 PA2
// Spring 2018
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */

public class SolitaireBoard {

	public static final int NUM_FINAL_PILES = 9;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

	// Note to students: you may not use an ArrayList -- see assgt description for
	// details.

	/**
	 * 
	 * 
	 * assert board[i]>=0 &&
	 * board.length<pile[NUM_FINAL_PILES*(NUM_FINAL_PILES+1)/2]; sum=0; for
	 * (i=0;i<board.length();i++){ sum=sum+board[i]} assert sum= CARD_TOTAL;
	 * 
	 * 
	 */

	private int[] board = new int[CARD_TOTAL + 1];
	private int sum = 0;
	Random rand = new Random();
	private int currentpos;

	/**
	 * Creates a solitaire board with the configuration specified in piles. piles
	 * has the number of cards in the first pile, then the number of cards in the
	 * second pile, etc. PRE: piles contains a sequence of positive numbers that sum
	 * to SolitaireBoard.CARD_TOTAL
	 */

	public SolitaireBoard(ArrayList<Integer> piles) {

		for (int i = 0; i < piles.size(); i++) {
			board[i] = piles.get(i);
			currentpos = piles.size() - 1;

			sum = sum + board[i];

		}
		assert isValidSolitaireBoard();
	}

	/**
	 * Creates a solitaire board with a random initial configuration.
	 */

	public SolitaireBoard() {

		randomize();
		assert isValidSolitaireBoard();

	}

	/**
	 * Plays one round of Bulgarian solitaire. Updates the configuration according
	 * to the rules of Bulgarian solitaire: Takes one card from each pile, and puts
	 * them all together in a new pile. The old piles that are left will be in the
	 * same relative order as before, and the new pile will be at the end.
	 */
	public void playRound() {
		assert isValidSolitaireBoard();
		int newpile = 0;// calculate the number of cards in the new pile
		for (int i = 0; i <= currentpos; i++) {
			if (board[i] != 0) {
				board[i]--;
				newpile++;
			}
		}
		board[currentpos + 1] = newpile;
		currentpos++;

		// remove all 0
		for (int j = 0; j < currentpos-1; j++) {
			if (board[j] == 0) {
				int l = j;
				while (board[l] == 0) {
					for (int i = j; i <= currentpos; i++) {
						board[i] = board[i + 1];

						// remove 0 from the end and changing the currentpos
						int countzeros = 0;
						for (int k = currentpos; board[k] == 0; k--) {
							countzeros++;

							currentpos = currentpos - countzeros;
						}
					}
				}
			}
		}
	}

	/**
	 * Returns true iff the current board is at the end of the game. That is, there
	 * are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES,
	 * in any order.
	 */

	public boolean isDone() {
		assert isValidSolitaireBoard();
		int[] check = Arrays.copyOf(board, currentpos + 1);
		Arrays.sort(check);
		if (check[0] == CARD_TOTAL) {
			return false;
		}
		for (int i = 0; i < currentpos; i++) {
			if (check[i + 1] != check[i] + 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns current board configuration as a string with the format of a
	 * space-separated list of numbers with no leading or trailing spaces. The
	 * numbers represent the number of cards in each non-empty pile.
	 */
	public String configString() {

		assert isValidSolitaireBoard();
		String numbs = "";

		for (int i = 0; i < currentpos + 1; i++) {

			numbs = numbs + board[i] + " ";
		}
		return numbs;

	}

	/**
	 * Returns true iff the solitaire board data is in a valid state (See
	 * representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard() {
		
		if (board[0]==CARD_TOTAL) {return true;}
		for (int i = 0; i < currentpos; i++) {

			if (sum == CARD_TOTAL && board[i] >= 0 && board[i] < CARD_TOTAL + 1) {
				return true;

			}
		}

		return false;

	}

	// randomize the number
	private void randomize() {
		currentpos = rand.nextInt(CARD_TOTAL) + 1;
		int i = 0;

		for (i = 0; i < currentpos && sum != CARD_TOTAL; i++) {

			board[i] = rand.nextInt(CARD_TOTAL - sum) + 1;
			sum = sum + board[i];

		}
		if (sum != CARD_TOTAL) {
			board[i] = CARD_TOTAL - sum;
		}
		sum = sum + board[i];
		currentpos = i;

	}
}
