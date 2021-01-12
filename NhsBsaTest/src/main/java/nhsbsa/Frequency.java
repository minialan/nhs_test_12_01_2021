package nhsbsa;

public enum Frequency {
	WEEK, TWO_WEEK, FOUR_WEEK, MONTH, QUARTER, YEAR;

	public float getFrequencyFloat() {
		switch(this) {
		case WEEK:
			return 1.0f;
		case TWO_WEEK:
			return 2.0f;
		case FOUR_WEEK:
		case MONTH:
			return 4.0f;
		case QUARTER:
			return 13.0f;
		case YEAR:
			return 52.0f;
		default:
			break;
		}
		return 0;
	}
}
