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

	public Player() {
		this.ia = new RandomIA(new Random());
	}

	public Player(IA ia) {
		this.ia = ia;
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
