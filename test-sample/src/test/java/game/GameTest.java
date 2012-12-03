package game;

import game.Game;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import player.Player;
import rules.my8.My8Rules;

import card.Card;
import card.Color;
import card.Deck;
import card.Value;



@RunWith(MockitoJUnitRunner.class)
public class GameTest {

	private static final Card _1_CLUB = Card.of(Value._1, Color.Club);
	private static final Card _2_CLUB = Card.of(Value._2, Color.Club);
	private static final Card _1_SPADE = Card.of(Value._1, Color.Spade);
	private static final Card _1_HEART = Card.of(Value._1, Color.Heart);

	@Mock
	private My8Rules rule;
	@Mock
	private Deck deck;
	@Mock
	private Player p1;
	@Mock
	private Player p2;

	private final Game game = new Game();

	@Test
	public void playAGameWithOnePlayerFinishedHand() throws Exception {
		List<Player> players = Arrays.asList(p1, p2);

		deck.shuffle();
		Mockito.when(deck.size()).thenReturn(4);
		Mockito.when(deck.distribute()).thenReturn(_1_CLUB).thenReturn(_1_HEART).thenReturn(_1_SPADE).thenReturn(_2_CLUB);
		p1.addCardsToHand(_1_CLUB);
		p1.addCardsToHand(_1_SPADE);
		p2.addCardsToHand(_1_HEART);
		p2.addCardsToHand(_2_CLUB);
		Mockito.when(rule.play(Mockito.anyListOf(Card.class), Mockito.eq(p1))).thenReturn(_1_CLUB).thenReturn(_1_HEART);
		Mockito.when(rule.shouldStopGame(_1_CLUB, p1)).thenReturn(false);
		Mockito.when(rule.play(Mockito.anyListOf(Card.class), Mockito.eq(p2))).thenReturn(_1_SPADE);
		Mockito.when(rule.shouldStopGame(_1_SPADE, p2)).thenReturn(false);
		Mockito.when(rule.shouldStopGame(_1_HEART, p1)).thenReturn(true);

		Mockito.when(rule.findWinner(players)).thenReturn(p1);

		Player winner = game.playAGame(rule, deck, players);

		Assert.assertSame(p1, winner);

		Mockito.verify(rule).shouldStopGame(_1_SPADE, p2);
	}

	@Test
	public void playAGameWithNoPlayerFinishedHand() throws Exception {
		List<Player> players = Arrays.asList(p1, p2);

		deck.shuffle();
		Mockito.when(deck.size()).thenReturn(4);
		Mockito.when(deck.distribute()).thenReturn(_1_CLUB).thenReturn(_1_HEART).thenReturn(_1_SPADE).thenReturn(_2_CLUB);
		p1.addCardsToHand(_1_CLUB);
		p1.addCardsToHand(_1_SPADE);
		p2.addCardsToHand(_1_HEART);
		p2.addCardsToHand(_2_CLUB);
		Mockito.when(rule.play(Mockito.anyListOf(Card.class), Mockito.eq(p1))).thenReturn(_1_CLUB).thenReturn(null);
		Mockito.when(rule.shouldStopGame(_1_CLUB, p1)).thenReturn(false);
		Mockito.when(rule.play(Mockito.anyListOf(Card.class), Mockito.eq(p2))).thenReturn(_2_CLUB).thenReturn(null);
		Mockito.when(rule.shouldStopGame(_2_CLUB, p2)).thenReturn(false);
		Mockito.when(rule.shouldStopGame(null, p1)).thenReturn(false);
		Mockito.when(rule.shouldStopGame(null, p2)).thenReturn(false);

		Mockito.when(rule.findWinner(players)).thenReturn(p1);

		Player winner = game.playAGame(rule, deck, players);

		Assert.assertSame(p1, winner);

		Mockito.verify(rule).shouldStopGame(null, p1);
		Mockito.verify(rule).shouldStopGame(null, p2);
	}
}
