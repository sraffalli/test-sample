package ia.random;

import ia.IA;

import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

import card.Card;



public class RandomIA implements IA {

	private final Random random;

	public RandomIA(Random random) {
		this.random = random;
	}

	public Card choose(List<Card> cards) {
		Assert.notEmpty(cards);
		return cards.get(Math.abs(random.nextInt()) % cards.size());
	}
}
