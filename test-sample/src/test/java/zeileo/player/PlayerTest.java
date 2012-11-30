package zeileo.player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import zeileo.card.Card;
import zeileo.card.Color;
import zeileo.card.Value;
import zeileo.ia.IA;


public class PlayerTest {

	private static final Card CARD1 = Card.of(Value._1, Color.Heart);
	private static final Card CARD2 = Card.of(Value._2, Color.Heart);

	private final IA ia = Mockito.mock(IA.class);

	@Before
	public void setup() {
		Mockito.reset(ia);
	}

	/*
	 * addCardsToHand()
	 */
	@Test
	public void addCardsToHand() throws Exception {
		Player p = new Player();
		p.addCardsToHand(CARD1, CARD2);

		Assert.assertEquals(2, p.getHandSize());
		Assert.assertEquals(CARD1, p.getHand().get(0));
		Assert.assertEquals(CARD2, p.getHand().get(1));
	}

	@Test
	public void addCardsToHandWithNoEmptyHand() throws Exception {
		Player p = new Player();

		p.addCardsToHand(CARD1, CARD2);
		p.addCardsToHand(Card.of(Value._3, Color.Heart), Card.of(Value._4, Color.Heart));

		Assert.assertEquals(4, p.getHandSize());
	}

	@Test
	public void addCardsToHandWithNoEmptyHandAndSameCards() throws Exception {
		Player p = new Player();

		p.addCardsToHand(CARD1, CARD2);
		p.addCardsToHand(CARD2, CARD1);

		Assert.assertEquals(4, p.getHandSize());
	}

	/*
	 * chooseACardToPlay()
	 */
	@Test
	public void chooseACardToPlay() throws Exception {
		Player p = new Player(ia);
		p.addCardsToHand(CARD1);
		List<Card> choices = Arrays.asList(CARD1);

		Mockito.when(ia.choose(choices)).thenReturn(CARD1);

		Assert.assertEquals(CARD1, p.chooseACardToPlay(choices));
		Assert.assertEquals(0, p.getHandSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void chooseACardToPlayWithNullChoices() throws Exception {
		Player p = new Player(ia);
		p.chooseACardToPlay(null);
	}

	@Test
	public void chooseACardToPlayWithEmptyChoices() throws Exception {
		Player p = new Player(ia);

		Assert.assertNull(p.chooseACardToPlay(Collections.<Card> emptyList()));
		Assert.assertEquals(0, p.getHandSize());
	}

	@Test
	public void chooseACardToPlayWithMultipleChoices() throws Exception {
		Player p = new Player(ia);
		p.addCardsToHand(CARD1, CARD2);
		List<Card> choices = Arrays.asList(CARD1, CARD2);

		Mockito.when(ia.choose(choices)).thenReturn(CARD1);

		Assert.assertEquals(CARD1, p.chooseACardToPlay(choices));
		Assert.assertEquals(1, p.getHandSize());
	}

	@Test(expected = IllegalStateException.class)
	public void chooseACardToPlayWithChoiceNotInTheHand() throws Exception {
		Player p = new Player(ia);
		p.addCardsToHand(CARD2);
		List<Card> choices = Arrays.asList(CARD1, CARD2);

		Mockito.when(ia.choose(choices)).thenReturn(CARD1);

		p.chooseACardToPlay(choices);
	}
}
