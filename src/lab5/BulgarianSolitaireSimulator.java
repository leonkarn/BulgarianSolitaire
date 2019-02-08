package lab5;

import java.util.Scanner;
import java.util.ArrayList;
//Name:Leonidas Karnesis
//USC NetID:karnesis
//CSCI455 PA2
//Spring 2018

public class BulgarianSolitaireSimulator {

	private static Scanner in2;

	public static void main(String[] args) {

		boolean singleStep = false;
		boolean userConfig = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}
		// create one temporary arraylist for the error checking and one arraylist to
		// input the values of the user if the values that the user inputed are correct
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		ArrayList<Integer> pile = new ArrayList<Integer>();
		Scanner in = new Scanner(System.in);
		int sum = 0;
		int numbs;
		boolean valid = false;
		// When the -u option turned on
		if (userConfig == true) {
			System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
			System.out.println(
					"You will be entering the initial configuration of the cards (i.e., how many in each pile).");
			// error checking
			while (sum != SolitaireBoard.CARD_TOTAL || valid == false) {
				String s1 = "";
				System.out.println("Please enter a space-separated list of positive integers followed by newline:");
				s1 = in.nextLine();
				in2 = new Scanner(s1);
				valid = true;
				sum = 0;
				tmp = new ArrayList<Integer>();
				while (in2.hasNextInt()) {
					numbs = in2.nextInt();
					if (numbs <= 0 || numbs > SolitaireBoard.CARD_TOTAL) {
						valid = false;					}
					sum = sum + numbs;
					if (sum == 45 && in2.hasNext()) {
						valid = false;
					}
					tmp.add(numbs);
				}
				if (sum != SolitaireBoard.CARD_TOTAL || valid == false) {
					System.out.println(
							"ERROR: Each pile must have at least one card and the total number of cards must be 45");
				}			}
			pile = tmp;
			SolitaireBoard sb2 = new SolitaireBoard(pile);
			play(sb2, singleStep, in);		}
		// With the -s option turned on
		if (singleStep == true && userConfig == false) {
			SolitaireBoard sb = new SolitaireBoard();
			play(sb, singleStep, in);
		}
		
	}

	// private method to play and print the results of the solitaire with -u or -s
	// or both options enabled
	private static void play(SolitaireBoard solitaire, boolean singleStep, Scanner in) {
		int i = 0;
		System.out.println("Initial configuration:" + solitaire.configString());

		while (!solitaire.isDone()) {
			solitaire.playRound();
			System.out.println("[" + i + "]  Current configuration:" + solitaire.configString());

			if (singleStep) {
				System.out.print("<Type return to continue>");
				in.nextLine();
			}
			i++;
		}
		System.out.println("Done!");
	}
}
