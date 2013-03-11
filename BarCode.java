/*
 * Name: Michael Callahan
 * Course: CSCI E-50B
 * Date: 03/11/2013
 * Assignment Number: 52
 */

// BarCode.java

/**
 * This class will valiate and encode/decode USPS ZipCode BarCodes.
 *
 * @author Michael Callahan
 * @version Last Modified 03_11_2013
 */
public class BarCode {
    // Class Constants
    // The zero digit barcode string for handling the zero digit special case
    private static final String ZERO_DIGIT_BARCODE = "||:::";
    
    // The digit weights for encoding/decoding zipcode digits
    // zero not included because it is not needed for encoding/decoding
    private static final int[] DIGIT_WEIGHTS = {7, 4, 2, 1};
    
    // The barcode full and half bar characters and corresponding values for encoding/decoding
    private static final char FULL_BAR = '|';
    private static final char HALF_BAR = ':';
    private static final int FULL_BAR_VALUE = 1;
    private static final int HALF_BAR_VALUE = 0;
    
    // Class fields
    // The zipcode number -- represented as a string to keep leading zeros
    private String myZipCode;
    // The zipcode encoded as a barcode
    private String myBarCode;

    /**
     * Constructs a new BarCode object from either a ZipCode or a BarCode
     *
     * @param code The ZipCode to be encoded or the BarCode to be decoded
     */
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
	    // Set both fields to an empty string
	    myZipCode = "";
	    myBarCode = "";
	}
    }

    /**
     * Returns the value of the myZipCode field.
     *
     * @return the String value of the private myZipCode field.
     */
    public String getZipCode() {
	return myZipCode;
    }

    /**
     * Returns the value of the myBarCode field.
     *
     * @return the String value of the private myBarCode field.
     */
    public String getBarCode() {
	return myBarCode;
    }

    /**
     * Encodes a ZipCode to a BarCode
     *
     * @return the BarCode String that is the encoded ZipCode
     */
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

    /**
     * Decodes a BarCode to a ZipCode
     *
     * @return the ZipCode String that is the decoded BarCode
     */
    private String decode() {
	// Test for a valid barcode
	if (isValidBarCode()) {
	    // Variable to hold the decoded zipcode
	    StringBuilder decodedZipCode = new StringBuilder();
	    // Decode the 5 zipcode digits
	    for (int i = 1; i < myBarCode.length()-7; i += 5) {
		// Get the single digit barcode string
		String myBarCodeDigit = myBarCode.substring(i, i + 5);
		// Add the decoded single digit
		decodedZipCode.append(codeToDigit(myBarCodeDigit));
	    }
	    // Return the decoded zipcode as a String
	    return decodedZipCode.toString();
	} else {
	    // This is not a valid barcode, return an empty string
	    return "";
	}
    }

    /**
     * Decodes a single BarCoded Digit to it's integer value represented
     * as a String
     *
     * @param digitBarCode the single digit BarCode string to be decoded
     * @return the decoded digit represented as a string
     */
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
	return String.valueOf(digitDecoded);
    }

    /**
     * Encodes a single Digit to it's BarCode representation
     *
     * @param digit the single Digit string to be encoded
     * @return the encoded digit barcode String
     */
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
		// If the weight + the total full bars encoded so far
		// is less than or equal to the number to be encoded
		// then we add a full bar (1)
		if (DIGIT_WEIGHTS[i] + encodedTotal <= digitNumber) {
		    encodedDigit.append(FULL_BAR);
		    encodedTotal += DIGIT_WEIGHTS[i];
		} else {
		    // This weight + total full bars so far would be greater
		    // than the number to be encoded so we add a half bar (0)
		    encodedDigit.append(HALF_BAR);
		}
	    }
	    // Append the required full or half bar depending on the count of half bars
	    encodedDigit.append(countChars(encodedDigit.toString(), HALF_BAR) == 2 ? HALF_BAR : FULL_BAR);
	    return encodedDigit.toString();
	}
    }

    /**
     * Tests if the BarCode is valid
     *
     * Validates the barcode frame, digit patterns, and the check digit
     *
     * @return true if this is a valid barcode, false if it is not a valid barcode
     */
    private boolean isValidBarCode() {
	// Check for valid frame bars
	if (!(myBarCode.charAt(0) == FULL_BAR && myBarCode.charAt(myBarCode.length()-1) == FULL_BAR)) {
	    return false;
	} else {
	    // Check for valid digit patterns and a valid check digit
	    int decodedDigitsSum = 0;
	    for (int i = 1; i < myBarCode.length()-7; i += 5) {
		// Get the single barcode digit
		String myBarCodeDigit = myBarCode.substring(i, i + 5);
		// Validate the number of full and half bars in the single barcode digit
		if (countChars(myBarCodeDigit, FULL_BAR) != 2 && countChars(myBarCodeDigit, HALF_BAR) != 3) {
		    return false;
		} else {
		    // Calculate the valid digits sum for validating the check digit
		    decodedDigitsSum += Integer.parseInt(codeToDigit(myBarCodeDigit));
		}
	    }
	    // Validate the check digit pattern
	    // Get the check digit barcode
	    String checkDigitBarCode = myBarCode.substring(26, 31);
	    // Validate the number of full and half bars in the check digit barcode
	    if (countChars(checkDigitBarCode, FULL_BAR) != 2 && countChars(checkDigitBarCode, HALF_BAR) != 3) {
		return false;
	    } else {
		// Validate the check digit value
		if (getCheckDigit(decodedDigitsSum) != Integer.parseInt(codeToDigit(checkDigitBarCode))) {
		    return false;
		}
	    }
	}
	// All tests passed, this must be a valid bar code
	return true;
    }

    /**
     * Tests if the ZipCode is valid
     *
     * Validates that all the zipcode characters are digits
     *
     * @return true if this is a valid zipcode, false if it is not a valid zipcode
     */
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

    /**
     * Counts the number of occurrences of the given char
     * in the given string
     *
     * @param targetString the string to search for the given char
     * @param charToCount the char to count in the given string
     * @return the integer number of chars in the string
     */
    private int countChars(String targetString, char charToCount) {
	int charCount = 0;

	// Iterate through the chars in the string and
	// count the number found
	for (int i = 0; i < targetString.length(); i++) {
	    if (targetString.charAt(i) == charToCount) {
		charCount++;
	    }
	}

	return charCount;
    }

    /**
     * Calculates the check digit for the given
     * sum of zipcode digits
     *
     * @param digitsSum the sum of the zipcode digits
     * @return the check digit integer value
     */
    private int getCheckDigit(int digitsSum) {
	// Get the next multiple of 10 after the digits sum
	int roundUpTen = (digitsSum/10 + 1) * 10;
	// Calculate the check digit and return
	return roundUpTen - digitsSum;
    }
}
