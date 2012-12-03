package rules;

import java.util.List;

import player.Player;

import card.Card;



public interface Rules {

	Card play(List<Card> previous, Player player);

	boolean shouldStopGame(Card playedCard, Player lastPlayer);

	Player findWinner(List<Player> players);
}
