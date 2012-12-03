package ia.random;

import ia.IA;
import ia.random.RandomIA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import card.Card;
import card.Color;
import card.Value;



public class RandomIATest {

	private final Random random = Mockito.mock(Random.class);

	private final IA ia = new RandomIA(random);

	@Before
	public void setup() {
		Mockito.reset(random);
	}

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
		Mockito.when(random.nextInt()).thenReturn(122);

		Assert.assertEquals(Card.of(Value._1, Color.Club), ia.choose(Arrays.asList(Card.of(Value._1, Color.Club))));
	}

	@Test
	public void chooseWithNegativeIndex() throws Exception {
		Mockito.when(random.nextInt()).thenReturn(-122);

		Assert.assertEquals(Card.of(Value._1, Color.Club), ia.choose(Arrays.asList(Card.of(Value._1, Color.Club))));
	}

	@Test
	public void chooseWithSeveralCards() throws Exception {
		Mockito.when(random.nextInt()).thenReturn(13);

		Card choice = ia.choose(Arrays.asList(Card.of(Value._2, Color.Club), Card.of(Value._1, Color.Club)));

		Assert.assertEquals(Card.of(Value._1, Color.Club), choice);
	}
}
