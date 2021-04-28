public class Card implements Comparable<Card> {
	
	private String suit;
	private String rank;

	public Card (String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	public boolean equals(Card other) {
		if(this.suit == other.suit && this.rank == other.rank){
			return true;
		}
		return false;
	}

	public String getSuit () {
		return suit;
	}
	
	public String getRank () {
		return rank;
	}
	
	public void setSuit (String suit) {
		this.suit = suit;
	}
	
	public void setRank (String rank) {
		this.rank = rank;
	}
	
	public String toString () {
		return rank + " of " + suit;
	}

	private int getSuitValue() {
		String x = this.getSuit();
		if (x == "D") {
			return 0;
		}
		else if (x == "C") {
			return 1;
		}
		else if (x == "H") {
			return 2;
		}
		else {
			return 3;
		}
	}

	private int getRankValue() {
		String x = this.getRank();
		if (x == "K"|| x == "J" ||x == "Q") {
			return 11;
		}
		else if (x == "A") {
			return 12;
		} else {
			return Integer.parseInt(rank);
		}
	}

	@Override
	public int compareTo(Card x) {
		if (this.equals(x)) {
			return 0;
		}
		int cmp = this.getRankValue() - x.getRankValue();
		if (cmp != 0) {
			return cmp;
		}
		else{
			cmp = this.getSuitValue() - x.getSuitValue();
			return cmp;
		}
	}
}
