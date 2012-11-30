package zeileo.game;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zeileo.card.Deck;
import zeileo.player.Player;
import zeileo.rule.Rule;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:game.xml" })
@ActiveProfiles({ "first" })
@Ignore
public class GameWithFirstIAIntegrationTest {

	@Inject
	@Named("john")
	private Player john;

	@Inject
	@Named("jim")
	private Player jim;

	@Inject
	private Game game;

	@Inject
	private Rule rule;

	@Inject
	private Deck deck;

	@Test
	public void play() throws Exception {
		Player winner = game.playAGame(rule, deck, Arrays.asList(john, jim));

		Assert.assertNotNull(winner);
	}
}
