package zeileo.game;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import zeileo.card.Card;
import zeileo.card.Deck;
import zeileo.player.Player;
import zeileo.rule.Rule;


public class Game {

	private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

	public Player playAGame(Rule rule, Deck deck, List<Player> players) {
		LOGGER.info("Start a new game with the {} with the following players : {}", rule.getClass().getSimpleName(),
					StringUtils.collectionToCommaDelimitedString(players));

		LOGGER.info("Shuffle the deck...");
		deck.shuffle();

		LOGGER.info("Distribute the cards to players...");
		int initialDeckSize = deck.size();
		for (int i = 0; i < initialDeckSize; i++) {
			players.get(i % players.size()).addCardsToHand(deck.distribute());
		}

		LinkedList<Card> previousCards = new LinkedList<Card>();
		int numberOfPlayedCard = 1;

		LOGGER.info("Begin to play...");
		// while a player has played a card in the last round
		while (numberOfPlayedCard != 0) {
			numberOfPlayedCard = 0;

			for (Player p : players) {
				Card playedCard = rule.play(previousCards, p);
				LOGGER.info("{} plays the {}", p, playedCard);

				// game should be stopped ?
				if (rule.shouldStopGame(playedCard, p)) {
					LOGGER.info("{} stops the game !", p);
					// find the winner
					return rule.findWinner(players);
				}

				if (playedCard != null) {
					previousCards.addFirst(playedCard);
					numberOfPlayedCard++;
				}
			}
		}

		LOGGER.info("No player can play anymore, the game is finished !");

		// find the winner at the end of the game
		Player winner = rule.findWinner(players);

		// flush all hands
		for (Player player : players) {
			player.flushHand();
		}
		return winner;
	}
}
