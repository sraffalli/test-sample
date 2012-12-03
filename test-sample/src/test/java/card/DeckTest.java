package card;

import org.junit.Assert;
import org.junit.Test;

import card.Card;
import card.Color;
import card.Deck;
import card.NoMoreCardException;
import card.Value;



public class DeckTest {

	@Test
	public void init32Cards() throws Exception {
		Deck deck = Deck.init32Cards();

		Assert.assertEquals(32, deck.size());
		Assert.assertFalse(deck.getCards().contains(Card.of(Value._6, Color.Heart)));
		Assert.assertTrue(deck.getCards().contains(Card.of(Value._7, Color.Heart)));
	}

	@Test
	public void shuffle() throws Exception {
		Deck deck = Deck.init32Cards();

		Assert.assertEquals(Card.of(Value._7, Color.Heart), deck.getCards().get(0));
		Assert.assertEquals(Card.of(Value._8, Color.Heart), deck.getCards().get(1));
		Assert.assertEquals(Card.of(Value._9, Color.Heart), deck.getCards().get(2));

		deck.shuffle();

		// at least one card has moved
		Assert.assertFalse(deck.getCards().get(0).equals(Card.of(Value._7, Color.Heart))
				&& deck.getCards().get(1).equals(Card.of(Value._8, Color.Heart))
				&& deck.getCards().get(2).equals(Card.of(Value._9, Color.Heart)));
		Assert.assertEquals(32, deck.size());
	}

	@Test
	public void distribute() throws Exception {
		Deck deck = Deck.init32Cards();

		Assert.assertEquals(Card.of(Value._7, Color.Heart), deck.distribute());
		Assert.assertEquals(31, deck.size());
		Assert.assertEquals(Card.of(Value._8, Color.Heart), deck.distribute());
		Assert.assertEquals(30, deck.size());
	}

	@Test(expected = NoMoreCardException.class)
	public void distributeWithNoMoreCardException() throws Exception {
		Deck deck = Deck.init32Cards();

		for (int i = 0; i < 32; i++) {
			deck.distribute();
		}

		Assert.assertEquals(0, deck.size());
		deck.distribute();
	}
}
