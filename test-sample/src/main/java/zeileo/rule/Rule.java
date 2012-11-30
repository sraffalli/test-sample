package zeileo.rule;

import java.util.List;

import zeileo.card.Card;
import zeileo.player.Player;


public interface Rule {

	Card play(List<Card> previous, Player player);

	boolean shouldStopGame(Card playedCard, Player lastPlayer);

	Player findWinner(List<Player> players);
}
