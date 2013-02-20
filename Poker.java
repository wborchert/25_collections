import java.util.*;
import java.io.*;

public class Poker {

private ArrayList<Card> hand;
private Deck deck;
private BufferedReader br;
private int totalscore;
private int numRounds;

	public Poker() {
		deck = new Deck();
		hand = new ArrayList<Card>();
	}
	
	public static void main(String[] args) throws IOException {
		Poker p = new Poker();
		p.initializeReader();
		String again = "";
		while(true) {
			p.getDeck().shuffle();
			p.addToHand(p.getDeck().deal(5));
			p.addToDeck(p.offer());
			p.addToHand(p.getDeck().deal(5 - p.getHand().size()));
			p.setRounds(p.getRounds() + 1);
			p.setTotalScore(p.getTotalScore() + p.score(p.getHand()));
			System.out.println("Your score this round: " + p.score(p.getHand()));
			System.out.println("Your total score after " + p.getRounds() + " rounds: " + p.getTotalScore());
			p.addToDeck(p.getHand());
			p.getHand().clear();
			while(true) {
				System.out.println("Do you want to play again? Yes/No");
				again = p.getReader().readLine();
				if(again.equals("No")) {
					System.exit(0);
				}
				else if(again.equals("Yes")) {
					break;
				}
			}
		}
	}

	public ArrayList<Card> offer() {
		System.out.println("Which cards would you like to exchange? Type the number of their location " + 
		"in your hand to return that card. Type 0 if you are done returning cards.");
		ArrayList<Card> cards = new ArrayList<Card>();
		while(true) {
			try {
			int loc = Integer.parseInt(br.readLine()) - 1;
			if(loc == -1) {
				break;
			}
			else if(loc >= 0 && loc < hand.size()) {
				cards.add(getHand().remove(loc));
				System.out.println("Your hand: " + hand);
			}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cards;
	}
	
	private int score(ArrayList<Card> newhand) {
		int score = 0;
		String type = scoreMatches(newhand);
			if(type.equals("One Pair")) {score = 1;}
			else if(type.equals("Two Pairs")) {score = 9;}
			else if(type.equals("Three of a Kind")) {score = 18;}
			if(scoreStraight(newhand)) {score = 109;}
			if(scoreFlush(newhand)) {score = 212;}
			if(type.equals("Full House")) {score = 302;}
			else if(type.equals("Four of a Kind")) {score = 2115;}
			if(scoreStraightFlush(newhand)) {score = 4230;}
			if(scoreRoyalFlush(newhand)) {score = 10000;}
		return score;
	}
	
	private Deck getDeck() {
		return deck;
	}
	
	private ArrayList<Card> getHand() {
		return hand;
	}
	
	private BufferedReader getReader() {
		return br;
	}
	
	private int getRounds() {
		return numRounds;
	}
	
	private void setRounds(int r) {
		numRounds = r;
	}
	
	private int getTotalScore() {
		return totalscore;
	}
	
	private void setTotalScore(int s) {
		totalscore = s;
	}
		
	private void addToHand(ArrayList<Card> cards) {
		hand.addAll(cards);
		System.out.println("Your hand: " + getHand());
	}
	
	private void addToDeck(ArrayList<Card> cards) {
		getDeck().returnCards(cards);
	}
	
	private void initializeReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private String scoreMatches(ArrayList<Card> newhand) {
		ArrayList<Integer> numMatches = new ArrayList<Integer>();
		ArrayList<Card> temphand = new ArrayList<Card>();
		ArrayList<Card> safehand = new ArrayList<Card>();
		int counter = 0;
		safehand.addAll(newhand);
		String handType = "";
		while(newhand.size() > 0) {
			temphand.add(newhand.remove(0));
			while(counter < newhand.size()) {
				if(temphand.get(temphand.size() - 1).getValue() == newhand.get(counter).getValue()) {
					temphand.add(newhand.remove(counter));
					counter--;
				}
				counter++;
			}
			counter = 0;
			numMatches.add(temphand.size());
			temphand.clear();
		}
		newhand.addAll(safehand);
		while(numMatches.remove(new Integer(1))) {}
		if(numMatches.size() == 1) {
			switch (numMatches.get(0)) {
				case 2: handType = "One Pair";
						break;
				case 3: handType = "Three of a Kind";
						break;
				case 4: handType = "Four of a Kind";
						break;
			} 
		}
		else if(numMatches.size() == 2) {
			switch (numMatches.get(0) + numMatches.get(1)) {
				case 4: handType = "Two Pairs";
						break;
				case 5: handType = "Full House";
						break;
			} 
		}
		return handType;
	}
	
	private boolean scoreFlush(ArrayList<Card> newhand) {
		boolean handType = false;
		int counter = 0;
		for(int i = 1; i < newhand.size(); i++) {
			if(newhand.get(0).getSuit().equals(newhand.get(i).getSuit())) {
				counter++;
			}
		}
		if(counter == 4) {
			handType = true;
		}
		return handType;
	}
	
	private boolean scoreStraight(ArrayList<Card> newhand) {
		boolean handType = false;
		int counter = 0;
		int valueone = 0;
		int valuetwo = 0;
		sortHand(newhand);
		for(int i = 0; i < newhand.size(); i++) {
			valueone = newhand.get(i).getValue();
			if(i == newhand.size() - 1) {
				valuetwo = newhand.get(0).getValue();
			}
			else {
				valuetwo = newhand.get(i + 1).getValue();
			}
			if(valueone == valuetwo - 1 || valueone == valuetwo + 12) {
				counter++;
			}
			}
		if(counter == 4) {
			handType = true;
		}
		return handType;
	}
	
	private boolean scoreStraightFlush(ArrayList<Card> newhand) {
		boolean handType = false;
		if(scoreStraight(newhand) && scoreFlush(newhand)) {
			handType = true;
		}
		return handType;
	}
	
	private boolean scoreRoyalFlush(ArrayList<Card> newhand) {
		boolean handType = false;
		if(scoreStraightFlush(newhand) && newhand.get(0).getValue() == 1 && newhand.get(1).getValue() == 10) {
			handType = true;
		}
		return handType;
	}
	
	public void sortHand(ArrayList<Card> newhand) {
		for(int i = 0; i < newhand.size() - 1; i++) {
			for(int j = i + 1; j < newhand.size(); j++) {
				if(newhand.get(j).getValue() <= newhand.get(i).getValue()) {
					newhand.add(i, newhand.get(j));
					newhand.remove(j + 1);
				}
			}
		}
	}
}