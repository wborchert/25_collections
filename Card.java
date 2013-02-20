public class Card {

private String name;
private int value;
private String suit;

	public Card(int v, String n, String s) {
		value = v;
		name = n;
		suit = s;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String toString() {
		return getName() + " of " + getSuit();
	}
}