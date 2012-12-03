package zeileo.game;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zeileo.TestCategories.IntegrationTest;
import zeileo.card.Deck;
import zeileo.ia.IA;
import zeileo.player.Player;
import zeileo.rule.Rule;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:game.xml" })
@ActiveProfiles({ "first" })
@Category(IntegrationTest.class)
public class GameWithFirstIAIntegrationTest {

	@Inject
	private Game game;

	@Inject
	private Rule rule;

	@Inject
	private IA ia;

	@Test
	public void play() throws Exception {
		Player winner =
				game.playAGame(rule, Deck.init32Cards(), Arrays.asList(new Player(ia, "john"), new Player(ia, "jim")));

		Assert.assertNotNull(winner);
	}
}
