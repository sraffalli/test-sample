package card;

import org.junit.Assert;
import org.junit.Test;

import card.Card;
import card.Color;
import card.Value;




public class CardTest {

	/*
	 * Card.of(Value, Color)
	 */
	@Test
	public void newCard() throws Exception {
		Card card = Card.of(Value._3, Color.Heart);

		Assert.assertEquals(Value._3, card.getValue());
		Assert.assertEquals(Color.Heart, card.getColor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void newCardWithNullValue() throws Exception {
		Card.of(null, Color.Heart);
	}

	@Test(expected = IllegalArgumentException.class)
	public void newCardWithNullColor() throws Exception {
		Card.of(Value._1, null);
	}

	/*
	 * equals() && hashCode()
	 */
	@Test
	public void equalsWithTwoEquals() throws Exception {
		Card card1 = Card.of(Value._1, Color.Club);
		Card card2 = Card.of(Value._1, Color.Club);

		Assert.assertTrue(card1.equals(card2));
		Assert.assertEquals(card1.hashCode(), card2.hashCode());
	}

	@Test
	public void equalsWithSame() throws Exception {
		Card card1 = Card.of(Value._1, Color.Club);

		Assert.assertTrue(card1.equals(card1));
	}

	@Test
	public void equalsWithNull() throws Exception {
		Card card1 = Card.of(Value._1, Color.Club);

		Assert.assertFalse(card1.equals(null));
	}

	@Test
	public void equalsWithObject() throws Exception {
		Card card1 = Card.of(Value._1, Color.Club);

		Assert.assertFalse(card1.equals(new Object()));
	}

	@Test
	public void equalsWithTwoDifferent() throws Exception {
		Card card1 = Card.of(Value._1, Color.Club);
		Card card2 = Card.of(Value._2, Color.Heart);

		Assert.assertFalse(card1.equals(card2));
	}

	@Test
	public void equalsWithTwoDifferentValues() throws Exception {
		Card card1 = Card.of(Value._1, Color.Club);
		Card card2 = Card.of(Value._2, Color.Club);

		Assert.assertFalse(card1.equals(card2));
	}

	@Test
	public void equalsWithTwoDifferentColors() throws Exception {
		Card card1 = Card.of(Value._1, Color.Club);
		Card card2 = Card.of(Value._1, Color.Heart);

		Assert.assertFalse(card1.equals(card2));
	}
}
