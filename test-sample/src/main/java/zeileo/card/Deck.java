package zeileo.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import zeileo.NoMoreCardException;


public class Deck {

	private final List<Card> cards = new ArrayList<Card>();

	private Deck() {
	}

	public static Deck init32Cards() {
		Deck deck = new Deck();
		for (Color color : Color.values()) {
			for (Value value : Value.values()) {
				if (value.ordinal() < 5) {
					continue;
				}
				deck.cards.add(Card.of(value, color));
			}
		}
		return deck;
	}

	public int size() {
		return cards.size();
	}

	protected List<Card> getCards() {
		return cards;
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card distribute() throws NoMoreCardException {
		if (cards.isEmpty()) {
			throw new NoMoreCardException();
		}
		return cards.remove(0);
	}
}
