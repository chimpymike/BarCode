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
	    myBarCode = encode();
	} else if (code.length() == 32) {
	    // This is a barcode
	    myBarCode = code;
	    myZipCode = decode();
	} else {
	    // This is not a valid zipcode or barcode
	    System.out.println("Not a valid zipcode or barcode!!!");
	    myZipCode = "";
	    myBarCode = "";
	}
    }

    public String getZipCode() {
	return myZipCode;
    }

    public String getBarCode() {
	return myBarCode;
    }

    private String encode() {
	// Variable to hold the encoded zipcode
	StringBuilder encodedZipCode = new StringBuilder();
	// Variable to hold the sum of the digits for calculating the check digit
	int digitsSum = 0;
	
	// Test for a valid zipcode
	if (isValidZipCode()) {
	    // Append the starting frame bar
	    encodedZipCode.append(FULL_BAR);
	    
	    // Encode each digit in the zipcode and append to the encoded zipcode
	    for (int i = 0; i < myZipCode.length(); i++) {
		String currentDigit = myZipCode.substring(i, i + 1);
		encodedZipCode.append(digitToCode(currentDigit));
		digitsSum += Integer.parseInt(currentDigit);
	    }

	    // Append the encoded check digit to the encoded zipcode
	    int checkDigit = getCheckDigit(digitsSum);
	    encodedZipCode.append(digitToCode(Integer.toString(checkDigit)));

	    // Append the ending frame bar
	    encodedZipCode.append(FULL_BAR);

	    // Return the encoded zipcode
	    return encodedZipCode.toString();
	} else {
	    // This is not a valid zipcode, return an empty string
	    return "";
	}
    }

    private String decode() {
	// Test for a valid barcode
	if (isValidBarCode()) {
	    // Variable to hold the decoded zipcode
	    StringBuilder decodedZipCode = new StringBuilder();
	    // Decode the 5 zipcode digits
	    for (int i = 1; i < myBarCode.length()-7; i += 5) {
		// Get the single digit string
		String myBarCodeDigit = myBarCode.substring(i, i + 5);
		decodedZipCode.append(codeToDigit(myBarCodeDigit));
	    }
	    // Return the decoded zipcode as a String
	    return decodedZipCode.toString();
	} else {
	    // This is not a valid barcode, return an empty string
	    return "";
	}
    }

    private String codeToDigit(String digitBarCode) {
	// Decoded digit
	int digitDecoded = 0;

	// If this is NOT the Zero Digit BarCode special case
	// then apply the digit weights to each decoded bar
	if (!digitBarCode.equals(ZERO_DIGIT_BARCODE)) {
	    for (int i = 0; i < DIGIT_WEIGHTS.length; i++) {
		digitDecoded += DIGIT_WEIGHTS[i] * (digitBarCode.charAt(i) == FULL_BAR ? FULL_BAR_VALUE : HALF_BAR_VALUE);
	    }
	}
	return String.valueOf(digitBarCode);
    }

    private String digitToCode(String digit) {
	int digitNumber = Integer.parseInt(digit);

	// If this is the Zero Digit BarCode special case
	// then return the zero digit barcode
	if (digitNumber == 0) {
	    return ZERO_DIGIT_BARCODE;
	} else {
	    int encodedTotal = 0;
	    StringBuilder encodedDigit = new StringBuilder();
	    for (int i = 0; i < DIGIT_WEIGHTS.length; i++) {
		if (DIGIT_WEIGHTS[i] + encodedTotal <= digitNumber) {
		    encodedDigit.append(FULL_BAR);
		    encodedTotal += DIGIT_WEIGHTS[i];
		} else {
		    encodedDigit.append(HALF_BAR);
		}
	    }
	    // Append the required full or half bar depending on the count of half bars
	    encodedDigit.append(countChars(encodedDigit.toString(), HALF_BAR) == 2 ? HALF_BAR : FULL_BAR);
	    return encodedDigit.toString();
	}
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
		    decodedDigitsSum += Integer.parseInt(codeToDigit(myBarCodeDigit));
		}
		// Validate the check digit pattern
		String checkDigitBarCode = myBarCode.substring(26, 30);
		if (countChars(checkDigitBarCode, FULL_BAR) != 2 && countChars(checkDigitBarCode, HALF_BAR) != 3) {
		    return false;
		} else {
		    // Validate the check digit value
		    if (getCheckDigit(decodedDigitsSum) != Integer.parseInt(codeToDigit(checkDigitBarCode))) {
			return false;
		    }
		}
	    }
	}
	// All tests passed, this must be a valid bar code
	return true;
    }

    private boolean isValidZipCode() {
	// Check that all zipcode characters are digits
	for (int i = 0; i < myZipCode.length(); i++) {
	    if (!Character.isDigit(myZipCode.charAt(i))) {
		return false;
	    }
	}
	// Must be all valid digits
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
