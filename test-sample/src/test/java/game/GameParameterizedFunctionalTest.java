package game;

import game.Game;

import ia.IA;
import ia.first.FirstIA;
import ia.random.RandomIA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import player.Player;
import rules.Rules;
import rules.my8.My8Rules;

import card.Deck;
import categories.TestCategories.FunctionalTest;



@RunWith(Parameterized.class)
@Category(FunctionalTest.class)
public class GameParameterizedFunctionalTest {

	private final Game game;
	private final Rules rule;
	private final Deck deck;
	private final List<Player> players = new ArrayList<Player>();
	private final Player expectedWinner;

	public GameParameterizedFunctionalTest(Rules rule, Deck deck, List<Player> players, Player winner) {
		this.game = new Game();
		this.deck = deck;
		this.rule = rule;
		this.players.addAll(players);
		this.expectedWinner = winner;
	}

	@Parameters
	public static Collection<Object[]> data() {
		My8Rules my8Rules = new My8Rules();
		IA firstIA = new FirstIA();
		IA randomIA = new RandomIA(new Random());

		Player joe = new Player(firstIA, "joe");
		Player john = new Player(firstIA, "john");
		Player jim = new Player(randomIA, "jim");

		Object[][] data =
				new Object[][] {
								{ my8Rules, Deck.init32Cards(), Arrays.asList(joe), joe },
								{ my8Rules, Deck.init32Cards(), Arrays.asList(john, jim), null },
								{ my8Rules, Deck.init52Cards(), Arrays.asList(john, jim, joe), null },
								{ my8Rules, Deck.init52Cards(), Arrays.asList(john, jim, joe), null } };
		return Arrays.asList(data);
	}

	@Test
	public void test() {
		Player winner = game.playAGame(rule, deck, players);
		Assert.assertNotNull(winner);

		if (expectedWinner != null) {
			Assert.assertEquals(expectedWinner, winner);
		}
	}
}
