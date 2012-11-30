package zeileo.ia.first;

import java.util.List;

import org.springframework.util.Assert;

import zeileo.card.Card;
import zeileo.ia.IA;


public class FirstIA implements IA {

	public Card choose(List<Card> cards) {
		Assert.notEmpty(cards);
		return cards.get(0);
	}
}
