package card;

import org.springframework.util.Assert;



public class Card {

	private final Value value;
	private final Color color;

	private Card(Value value, Color color) {
		Assert.notNull(value);
		Assert.notNull(color);

		this.value = value;
		this.color = color;
	}

	public static final Card of(Value value, Color color) {
		return new Card(value, color);
	}

	@Override
	public String toString() {
		return "Card[" + value + ", " + color + "]";
	}

	@Override
	public int hashCode() {
		return 31 * color.hashCode() * value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (color != other.color)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public Value getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

}
