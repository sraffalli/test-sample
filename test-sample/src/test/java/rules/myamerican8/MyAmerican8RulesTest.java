package rules.myamerican8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import player.Player;
import rules.Rules;
import rules.myamerican8.MyAmerican8Rules;

import card.Card;
import card.Color;
import card.Value;



@RunWith(MockitoJUnitRunner.class)
public class MyAmerican8RulesTest {

	@Mock
	private Player p1;
	@Mock
	private Player p2;
	@Mock
	private Player p3;

	private final Rules rules = new MyAmerican8Rules();

	/*
	 * shouldStopGame()
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldStopGameWithNullPlayer() throws Exception {
		rules.shouldStopGame(null, null);
	}

	@Test
	public void shouldStopGameWithNullPreviousCard() throws Exception {
		Mockito.when(p1.getHandSize()).thenReturn(0);

		Assert.assertFalse(rules.shouldStopGame(null, p1));
	}

	@Test
	public void shouldStopGameWithPreviousCard() throws Exception {
		Mockito.when(p1.getHandSize()).thenReturn(0);

		Assert.assertTrue(rules.shouldStopGame(Card.of(Value._1, Color.Club), p1));
	}

	@Test
	public void shouldStopGameWithPreviousCardAndNoEmptyHand() throws Exception {
		Mockito.when(p1.getHandSize()).thenReturn(1);

		Assert.assertFalse(rules.shouldStopGame(Card.of(Value._1, Color.Club), p1));
	}

	/*
	 * findWinner()
	 */
	@Test
	public void findWinnerWithOnePlayerWithEmptyHand() throws Exception {
		Mockito.when(p1.getHandSize()).thenReturn(0);
		Mockito.when(p2.getHandSize()).thenReturn(2);

		Player winner = rules.findWinner(Arrays.asList(p1, p2));
		Assert.assertEquals(p1, winner);
	}

	@Test
	public void findWinnerWithNoPlayerWithEmptyHand() throws Exception {
		Mockito.when(p1.getHandSize()).thenReturn(3);
		Mockito.when(p2.getHandSize()).thenReturn(2);
		Mockito.when(p3.getHandSize()).thenReturn(4);

		Player winner = rules.findWinner(Arrays.asList(p1, p2, p3));
		Assert.assertEquals(p2, winner);
	}

	/*
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void playWithPreviousCardsNull() throws Exception {
		rules.play(null, p1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void playWithEmptyPlayerHand() throws Exception {
		Mockito.when(p1.getHand()).thenReturn(Collections.<Card> emptyList());

		rules.play(Collections.<Card> emptyList(), p1);
	}

	@Test
	public void playWithNoPreviousCard() throws Exception {
		Card card = Card.of(Value._1, Color.Heart);
		List<Card> hand = Arrays.asList(card);

		Mockito.when(p1.getHand()).thenReturn(hand);
		Mockito.when(p1.chooseACardToPlay(hand)).thenReturn(card);

		Card result = rules.play(Collections.<Card> emptyList(), p1);
		Assert.assertEquals(card, result);
	}

	@Test
	public void playWithNoChoiceInTheHand() throws Exception {
		Card card = Card.of(Value._1, Color.Heart);
		Card card2 = Card.of(Value._3, Color.Club);

		List<Card> hand = Arrays.asList(card);
		List<Card> nextHand = Arrays.asList(card, card2);

		Mockito.when(p1.getHand()).thenReturn(hand).thenReturn(nextHand);
		p1.addCardsToHand(card2);

		Mockito.when(p1.chooseACardToPlay(nextHand)).thenReturn(card2);

		// Arrays.asList doesn't support remove() -> have to create a new list
		Card result = rules.play(new ArrayList<Card>(Arrays.asList(card2)), p1);
		Assert.assertEquals(card2, result);
	}

	@Test
	public void playWithChoicesInTheHand() throws Exception {
		Card card = Card.of(Value._1, Color.Heart);
		Card card2 = Card.of(Value._3, Color.Club);
		Card card3 = Card.of(Value._3, Color.Heart);

		List<Card> hand = Arrays.asList(card, card2);

		Mockito.when(p1.getHand()).thenReturn(hand);

		Mockito.when(p1.chooseACardToPlay(Arrays.asList(card, card2))).thenReturn(card2);

		// Arrays.asList doesn't support remove() -> have to create a new list
		Card result = rules.play(new ArrayList<Card>(Arrays.asList(card3)), p1);
		Assert.assertEquals(card2, result);
	}
}
