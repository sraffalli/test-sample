package zeileo.game;

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

import zeileo.TestCategories.FunctionalTest;
import zeileo.card.Deck;
import zeileo.ia.IA;
import zeileo.ia.first.FirstIA;
import zeileo.ia.random.RandomIA;
import zeileo.player.Player;
import zeileo.rule.Rule;
import zeileo.rule.zeileo.ZeileoRule;


@RunWith(Parameterized.class)
@Category(FunctionalTest.class)
public class GameParameterizedFunctionalTest {

	private final Game game;
	private final Rule rule;
	private final Deck deck;
	private final List<Player> players = new ArrayList<Player>();
	private final Player expectedWinner;

	public GameParameterizedFunctionalTest(Rule rule, Deck deck, List<Player> players, Player winner) {
		this.game = new Game();
		this.deck = deck;
		this.rule = rule;
		this.players.addAll(players);
		this.expectedWinner = winner;
	}

	@Parameters
	public static Collection<Object[]> data() {
		ZeileoRule zeileoRule = new ZeileoRule();
		IA firstIA = new FirstIA();
		IA randomIA = new RandomIA(new Random());

		Player joe = new Player(firstIA, "joe");
		Player john = new Player(firstIA, "john");
		Player jim = new Player(randomIA, "jim");

		Object[][] data =
				new Object[][] {
								{ zeileoRule, Deck.init32Cards(), Arrays.asList(joe), joe },
								{ zeileoRule, Deck.init32Cards(), Arrays.asList(john, jim), null },
								{ zeileoRule, Deck.init52Cards(), Arrays.asList(john, jim, joe), null },
								{ zeileoRule, Deck.init52Cards(), Arrays.asList(john, jim, joe), null } };
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
