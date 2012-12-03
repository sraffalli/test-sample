package ia.first;

import ia.IA;

import java.util.List;

import org.springframework.util.Assert;

import card.Card;



public class FirstIA implements IA {

	public Card choose(List<Card> cards) {
		Assert.notEmpty(cards);
		return cards.get(0);
	}
}
