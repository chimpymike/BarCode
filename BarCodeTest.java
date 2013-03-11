public class BarCodeTest {
    public static void main(String[] args) {
	System.out.println("BarCodeTest");

	// 1.
	BarCode testBarCode1 = new BarCode("02138");
	System.out.println("ZipCode: " + testBarCode1.getZipCode());
	System.out.println("Barcode: " + testBarCode1.getBarCode());

	// 2.
	System.out.println("Creating BarCode #2 ..."); // DEBUG
	BarCode testBarCode2 = new BarCode("||:|:::|:|:||::::::||:|::|:::|||");
	String testZipCode2 = testBarCode2.getZipCode();
	if (testZipCode2.equals("")) {
	    System.out.println("Error. " + testBarCode2.getBarCode() + " is not a valid BarCode.");
	    System.out.println("ZipCode: " + testZipCode2);
	} else {
	    System.out.println("ZipCode: " + testZipCode2);
	}

	// 3.
	System.out.println("Creating BarCode #3 ..."); // DEBUG
	BarCode testBarCode3 = new BarCode("06478");
	System.out.println("BarCode: " + testBarCode3.getBarCode());

	// 4.
	System.out.println("Creating BarCode #4 ..."); // DEBUG
	BarCode testBarCode4 = new BarCode(testBarCode3.getBarCode());
	System.out.println("ZipCode: " + testBarCode4.getZipCode());

	// 5. and 6.
	System.out.println("Creating BarCode #5 (and #6) ..."); // DEBUG
	BarCode testBarCode5 = new BarCode("||:::||::|:::|:|:|::|||:::||:::|");
	if (testBarCode5.getZipCode().equals("")) {
	    System.out.println("Error. " + testBarCode5.getBarCode() + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: " + testBarCode5.getZipCode());
	}

	// 7.
	System.out.println("Creating BarCode #7 ..."); // DEBUG
	BarCode testBarCode7 = new BarCode("||:|:::|:|:||::::::||:|::|:::||");
	if (testBarCode7.getZipCode().equals("")) {
	    System.out.println("Error. " + testBarCode7.getBarCode() + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: " + testBarCode7.getZipCode());
	}

	// 8.
	System.out.println("Creating BarCode #8 ..."); // DEBUG
	BarCode testBarCode8 = new BarCode("||:|:::|:|:||::::::||:|::|:::||:");
	if (testBarCode8.getZipCode().equals("")) {
	    System.out.println("Error. " + testBarCode8.getBarCode() + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: " + testBarCode8.getZipCode());
	}
	
    }
}
