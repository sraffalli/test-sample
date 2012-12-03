package zeileo.rule.zeileo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import zeileo.card.Card;
import zeileo.player.Player;
import zeileo.rule.Rule;


public class ZeileoRule implements Rule {

	private final static Logger LOGGER = LoggerFactory.getLogger(ZeileoRule.class);

	/**
	 * The player can play a card that has the same value or the same color of the last played card, and if there is no
	 * card already played, he can play any card of his hand.
	 */
	public Card play(List<Card> previousCards, Player player) {
		Assert.notNull(previousCards);

		List<Card> playerHand = player.getHand();

		// if the player has an empty hand he should have already win the current game
		Assert.notEmpty(playerHand);

		// if the previous card constraint doesn't exist
		// the player can choose in his complete hand his next card to play
		if (previousCards.isEmpty()) {
			LOGGER.info("{} can choose any card of his hand", player);
			return player.chooseACardToPlay(playerHand);
		}

		// else determine all the possible cards he can play
		List<Card> choices = new ArrayList<Card>();
		Card previousCard = previousCards.get(0);

		for (Card c : playerHand) {
			if (previousCard.getColor() == c.getColor() || previousCard.getValue() == c.getValue()) {
				choices.add(c);
			}
		}

		// if the player has no card to play,
		// he takes all the previous played cards in his hand
		if (choices.isEmpty()) {
			LOGGER.info("{} doesn't have any card to play ! He takes the deck and chooses a new card.", player);
			player.addCardsToHand(previousCards);
			previousCards.clear();

			// the player pass
			return play(Collections.<Card> emptyList(), player);
		}

		// else choose a card
		return player.chooseACardToPlay(choices);
	}

	/**
	 * The game stops when a player has played his last card.
	 */
	public boolean shouldStopGame(Card playedCard, Player lastPlayer) {
		Assert.notNull(lastPlayer);
		return playedCard != null && lastPlayer.getHandSize() == 0;

	}

	/**
	 * The winner is the player who has player all his cards or, the player with the less of cards at the end of the
	 * game.
	 */
	public Player findWinner(List<Player> players) {
		int minNumberOfCards = Integer.MAX_VALUE;
		Player playerWithMinHand = null;

		for (Player p : players) {
			if (p.getHandSize() == 0) {
				return p;
			}
			if (minNumberOfCards > p.getHandSize()) {
				minNumberOfCards = p.getHandSize();
				playerWithMinHand = p;
			}
		}
		return playerWithMinHand;
	}
}
