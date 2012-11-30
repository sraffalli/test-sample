package zeileo.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

import zeileo.card.Card;
import zeileo.ia.IA;
import zeileo.ia.random.RandomIA;


public class Player {

	private final List<Card> hand = new ArrayList<Card>();

	private final IA ia;
	private final String name;

	protected Player(String name) {
		this(new RandomIA(new Random()), name);
	}

	public Player(IA ia, String name) {
		this.ia = ia;
		this.name = name;
	}

	public void addCardsToHand(List<Card> cards) {
		hand.addAll(cards);
	}

	public void addCardsToHand(Card... cards) {
		addCardsToHand(Arrays.asList(cards));
	}

	public int getHandSize() {
		return hand.size();
	}

	public List<Card> getHand() {
		return Collections.unmodifiableList(hand);
	}

	@Override
	public String toString() {
		return "Player[" + name + "]";
	}

	public Card chooseACardToPlay(List<Card> choices) {
		Assert.notNull(choices);

		if (choices.isEmpty()) {
			return null;
		}
		Card card = ia.choose(choices);

		if (!hand.remove(card)) {
			throw new IllegalStateException("The card chosen is not present in the player's hand !");
		}
		return card;
	}
}
