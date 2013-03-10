public class BarCode {
    // Class Constants
    private static final String ZERO_DIGIT_BARCODE = "||:::";
    
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
}
