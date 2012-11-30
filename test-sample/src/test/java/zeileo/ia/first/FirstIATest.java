package zeileo.ia.first;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;

import zeileo.card.Card;
import zeileo.card.Color;
import zeileo.card.Value;
import zeileo.ia.IA;


public class FirstIATest {

	private final IA ia = new FirstIA();

	@Test(expected = IllegalArgumentException.class)
	public void chooseWithNull() throws Exception {
		ia.choose(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void chooseWithEmpty() throws Exception {
		ia.choose(Collections.<Card> emptyList());
	}

	@Test
	public void chooseWithOneCard() throws Exception {
		Assert.assertEquals(Card.of(Value._1, Color.Club), ia.choose(Arrays.asList(Card.of(Value._1, Color.Club))));
	}

	@Test
	public void chooseWithSeveralCards() throws Exception {
		Card choice = ia.choose(Arrays.asList(Card.of(Value._2, Color.Club), Card.of(Value._1, Color.Club)));

		Assert.assertEquals(Card.of(Value._2, Color.Club), choice);
	}
}
