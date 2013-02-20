import java.util.*;
import java.math.*;
public class Deck {

private ArrayList<Card> deck;

	public Deck() {
		deck = new ArrayList<Card>();
		String suit = "Diamonds";
		for(int i = 0; i < 4; i++) {
			if(i == 1) {
				suit = "Spades";
			}
			else if( i == 2) {
				suit = "Hearts";
			}
			else if( i == 3) {
				suit = "Clubs";
			}
			deck.add(new Card(1, "Ace", suit));
			deck.add(new Card(2, "Two", suit));
			deck.add(new Card(3,"Three", suit));
			deck.add(new Card(4,"Four", suit));
			deck.add(new Card(5,"Five", suit));
			deck.add(new Card(6,"Six", suit));
			deck.add(new Card(7,"Seven", suit));
			deck.add(new Card(8,"Eight", suit));
			deck.add(new Card(9,"Nine", suit));
			deck.add(new Card(10,"Ten", suit));
			deck.add(new Card(11,"Jack", suit));
			deck.add(new Card(12,"Queen", suit));
			deck.add(new Card(13,"King", suit));	
		}
	}
	
	public void shuffle() {
		Card old = null;
		int index = 0;
		for(int j = 0; j < deck.size(); j++) {
			index = (int)(Math.random() * (deck.size()));
			old = deck.get(j);
			deck.set(j, deck.get(index));
			deck.set(index, old);
		}
	}
	
	public void sort() {
		for(int i = 0; i < deck.size(); i++) {
			for(int j = 0; j < deck.size(); j++) {
				if(deck.get(j).getValue() <= deck.get(i).getValue()) {
					deck.add(i, deck.get(j));
				}
			}
		}
	}
	
	public ArrayList<Card> deal(int numcards) {
		ArrayList<Card> dealtcards = new ArrayList<Card>();
		for(int i = 0; i < numcards; i++) {
			dealtcards.add(deck.remove(i));
		}
	return dealtcards;
	}
	
	public void returnCards(ArrayList<Card> returnedcards) {
		deck.addAll(returnedcards);
	}
}