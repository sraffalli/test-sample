package game;

import game.Game;

import ia.IA;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import player.Player;
import rules.Rules;

import card.Deck;
import categories.TestCategories.IntegrationTest;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:game.xml" })
@ActiveProfiles({ "first" })
@Category(IntegrationTest.class)
public class GameWithFirstIAIntegrationTest {

	@Inject
	private Game game;

	@Inject
	private Rules rule;

	@Inject
	private IA ia;

	@Test
	public void play() throws Exception {
		Player winner =
				game.playAGame(rule, Deck.init32Cards(), Arrays.asList(new Player(ia, "john"), new Player(ia, "jim")));

		Assert.assertNotNull(winner);
	}
}
