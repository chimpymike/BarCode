public class BarCode {
    // Class Constants
    private static final String ZERO_DIGIT_BARCODE = "||:::";
    private static final int[] DIGIT_WEIGHTS = {7, 4, 2, 1};
    private static final char FULL_BAR = '|';
    private static final char HALF_BAR = ':';
    private static final int FULL_BAR_VALUE = 1;
    private static final int HALF_BAR_VALUE = 0;
    
    private String myZipCode;
    private String myBarCode;

    public BarCode(String code) {
	// Determine if this is a zipcode or a barcode
	// based on the length of the code string
	if (code.length() == 5) {
	    // This is a zipcode
	    myZipCode = code;
	    // Placeholder for the encode to barcode method
	    // encode();
	} else if (code.length() == 32) {
	    // This is a barcode
	    myBarCode = code;
	    // Placeholder for the decode to zipcode method
	    // decode()
	} else {
	    // This is not a valid zipcode or barcode
	    System.out.println("Not a valid zipcode or barcode!!!");
	}
    }

    public String getZipCode() {
	return myZipCode;
    }

    public String getBarCode() {
	return myBarCode;
    }

    private String codeToDigit(String digitBarCode) {
	// Decoded digit
	int digitDecoded = 0;

	// If this is NOT the Zero Digit BarCode special case
	// then apply the digit weights to each decoded bar
	if (!digitBarCode.equals(ZERO_DIGIT_BARCODE)) {
	    for (int i = 0; i < DIGIT_WEIGHTS.length; i++) {
		digitDecoded += DIGIT_WEIGHTS[i] * (digitBarCode.charAt(i) == '|' ? 1 : 0);
	    }
	}
	return String.valueOf(digitBarCode);
    }

    private boolean isValidBarCode() {
	// Check for valid frame bars
	if (!(myBarCode.charAt(0) == FULL_BAR && myBarCode.charAt(myBarCode.length()-1) == FULL_BAR)) {
	    return false;
	} else {
	    // Check for valid digit patterns and a valid check digit
	    int decodedDigitsSum = 0;
	    for (int i = 1; i < myBarCode.length()-7; i += 5) {
		String myBarCodeDigit = myBarCode.substring(i, i + 4);
		if (countChars(myBarCodeDigit, FULL_BAR) != 2 && countChars(myBarCodeDigit, HALF_BAR) != 3) {
		    return false;
		} else {
		    decodedDigitsSum += codeToDigit(myBarCodeDigit);
		}
		// Validate the check digit pattern
		String checkDigitBarCode = myBarCode.substring(26, 30);
		if (countChars(checkDigitBarCode, FULL_BAR) != 2 && countChars(checkDigitBarCode, HALF_BAR) != 3) {
		    return false;
		} else {
		    // Validate the check digit value
		    if (getCheckDigit(decodedDigitsSum) != codeToDigit(checkDigitBarCode)) {
			return false;
		    }
		}
	    }
	}
	// All tests passed, this must be a valid bar code
	return true;
    }

    private int countChars(String targetString, char charToCount) {
	int charCount = 0;

	for (int i = 0; i < targetString.length(); i++) {
	    if (targetString.charAt(i) == charToCount) {
		charCount++;
	    }
	}

	return charCount;
    }

    private int getCheckDigit(int digitsSum) {
	int roundUpTen = (digitsSum/10 + 1) * 10;
	return roundUpTen - digitsSum;
    }
}
