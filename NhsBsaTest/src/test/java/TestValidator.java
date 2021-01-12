import static org.junit.Assert.*;

import org.junit.Test;

import nhsbsa.Frequency;
import nhsbsa.RegularAmount;
import validator.ConstraintValidator;

public class TestValidator {
	
	/*
	 	GIVEN any Frequency
		WHEN a non-numeric or blank Amount is provided
		THEN no validation error
	*/
	@Test
	public void testInvalidAmountString() throws Exception {
		System.out.println("testInvalidAmountString");
		final RegularAmount regularAmount = new RegularAmount("ee", Frequency.YEAR);
		Exception exception = assertThrows(NumberFormatException.class,()->{	
			new ConstraintValidator(regularAmount).validateAmount();
		});
		assertTrue(exception.getMessage().contains("ee"));
	}
	
	/*
	 	GIVEN any Frequency
		WHEN a non-numeric or blank Amount is provided
		THEN no validation error
	*/
	@Test
	public void testInvalidAmountEmpty() throws Exception {
		System.out.println("testInvalidAmountEmpty");
		final RegularAmount regularAmount = new RegularAmount("", Frequency.QUARTER);
		Exception exception = assertThrows(NumberFormatException.class,()->{	
			new ConstraintValidator(regularAmount).validateAmount();
		});
		assertTrue(exception.getMessage().contains("empty String"));
	}
	
	/*
	 	GIVEN any Amount
		WHEN a null Frequency is provided
		THEN no validation error
	 */
	@Test
    public void testNullFrequency() throws Exception {
		System.out.println("testNullFrequency");
		final RegularAmount regularAmount = new RegularAmount("1.30", null);
		Exception exception = assertThrows(NullPointerException.class,()->{	
			new ConstraintValidator(regularAmount).validateAmount();
		});
		assertTrue(exception.getMessage().contains("is null"));
    }
	
	/*
	 	GIVEN a WEEK Frequency
		WHEN any Amount is provided
		THEN no validation error
	 */
	@Test
	public void testWeekly() throws Exception {
		System.out.println("testWeekly");
		RegularAmount regularAmount = new RegularAmount("12.20", Frequency.WEEK);
		String message = new ConstraintValidator(regularAmount).validateAmount();
		assertTrue(message.isEmpty());
	}
	
	/*
		GIVEN a MONTH Frequency
		WHEN any Amount is provided
		THEN no validation error
	 */
	@Test
	public void testMonthly() throws Exception {
		System.out.println("testMonthly");
		RegularAmount regularAmount = new RegularAmount("1.12", Frequency.MONTH);
		String message = new ConstraintValidator(regularAmount).validateAmount();
		assertTrue(message.isEmpty());
	}
	
	/*
	 	GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
		AND an associated Number of Weeks is 2, 4, 13, 52 respectively
		WHEN a Amount that divides by the Number of Weeks to a whole number of pence is provided
		THEN no validation error
	 */
	@Test
	public void testTwoWeekValid() throws Exception {
		System.out.println("testTwoWeekValid");
		RegularAmount regularAmount = new RegularAmount("2.20", Frequency.TWO_WEEK);
		String message = new ConstraintValidator(regularAmount).validateAmount();
		assertTrue(message.isEmpty());
	}
	
	/*
	 	GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
		AND an associated Number of Weeks is 2, 4, 13, 52 respectively
		WHEN a Amount that divides by the Number of Weeks to a whole number of pence is provided
		THEN no validation error
	 */
	@Test
	public void testFourWeekValid() throws Exception {
		System.out.println("testFourWeekValid");
		RegularAmount regularAmount = new RegularAmount("44.44", Frequency.FOUR_WEEK);
		String message = new ConstraintValidator(regularAmount).validateAmount();
		assertTrue(message.isEmpty());
	}
	
	/*
	 	GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
		AND an associated Number of Weeks is 2, 4, 13, 52 respectively
		WHEN a Amount that does not divide by the Number of Weeks to a whole number of pence is provided
		THEN a validation error is produced
	 */
	@Test
	public void testInValidTwoWeek() throws Exception {
		System.out.println("testInValidTwoWeek");
		RegularAmount regularAmount = new RegularAmount("7.07", Frequency.TWO_WEEK);
		String message = new ConstraintValidator(regularAmount).validateAmount();
		assertTrue(message.contains("is not divisible by"));
	}
	
	/*
	 	GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
		AND an associated Number of Weeks is 2, 4, 13, 52 respectively
		WHEN a Amount that does not divide by the Number of Weeks to a whole number of pence is provided
		THEN a validation error is produced
	 */
	@Test
	public void testInValidFourWeek() throws Exception {
		System.out.println("testInValidFourWeek");
		RegularAmount regularAmount = new RegularAmount("13.07", Frequency.FOUR_WEEK);
		String message = new ConstraintValidator(regularAmount).validateAmount();
		assertTrue(message.contains("is not divisible by"));
	}
}
