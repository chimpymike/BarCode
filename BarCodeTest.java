public class BarCodeTest {
    public static void main(String[] args) {
	System.out.println("BarCodeTest");

	// 1.
	BarCode testBarCode1 = new BarCode("02138");
	System.out.println("ZipCode: " + testBarCode1.getZipCode());
	// System.out.println("Barcode: " + testBarCode.getZipCode());

	// 2.
	BarCode testBarCode2 = new BarCode("||:|:::|:|:||::::::||:|::|:::|||");
	String testZipCode2 = testBarCode2.getZipCode();
	if (testZipCode2.equals("")) {
	    System.out.println("Error. " + testBarCode2 + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: + " testZipCode2);
	}

	
	
    }
}
