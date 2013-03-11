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

	
	
    }
}
