package validator;

import nhsbsa.Frequency;
import nhsbsa.RegularAmount;

public class ConstraintValidator {
	
	// If frequency is a multiple of a week, monetary amount 
	// must be divisible to a weekly value that is a whole number of pence.

	private RegularAmount regularAmount;
	private String errorMessage = "";
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ConstraintValidator(RegularAmount regularAmount) {
		this.regularAmount = regularAmount;
	}
	
	public boolean performValidityCheck() {
		Frequency frequency = regularAmount.getFrequency();
		if(!frequency.name().equals("WEEK") && !frequency.name().equals("MONTH")) {
			return true;
		}
		return false;
	}
	
	public String validateAmount() throws NullPointerException, NumberFormatException {
		
		if(performValidityCheck()) {
			float remainder = Float.valueOf(regularAmount.getAmount())*100 % regularAmount.getFrequency().getFrequencyFloat();
//			System.out.println("remainder_____"+remainder);
			if(remainder != 0) {
				return regularAmount.getAmount() + " is not divisible by " + regularAmount.getFrequency() + " to a whole number of pence";
			}
		}
		return "";
	}
}
