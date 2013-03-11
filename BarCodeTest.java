/*
 * Name: Michael Callahan
 * Course: CSCI E-50B
 * Date: 03/11/2013
 * Assignment Number: 52
 */

// BarCodeTest.java

/**
 * This class will test the BarCode class.
 *
 * @author Michael Callahan
 * @version Last Modified 03_11_2013
 */
public class BarCodeTest {
    public static void main(String[] args) {
	// 1.
	System.out.println("Part 1.");
	String testZipCode1 = "02138";
	BarCode testBarCode1 = new BarCode(testZipCode1);
	System.out.println("Barcode: " + testBarCode1.getBarCode());
	System.out.println();

	// 2.
	System.out.println("Part 2.");
	String testBarCodeString2 = "||:|:::|:|:||::::::||:|::|:::|||";
	BarCode testBarCode2 = new BarCode(testBarCodeString2);
	String testZipCode2 = testBarCode2.getZipCode();
	if (testZipCode2.equals("")) {
	    System.out.println("Error. " + testBarCodeString2 + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: " + testZipCode2);
	}
	System.out.println();

	// 3.
	System.out.println("Part 3.");
	String testZipCode3 = "06478";
	BarCode testBarCode3 = new BarCode(testZipCode3);
	System.out.println("BarCode: " + testBarCode3.getBarCode());
	System.out.println();

	// 4.
	System.out.println("Part 4.");
	BarCode testBarCode4 = new BarCode(testBarCode3.getBarCode());
	System.out.println("ZipCode: " + testBarCode4.getZipCode());
	System.out.println();

	// 5. and 6.
	System.out.println("Parts 5. and 6.");
	String testBarCodeString5 = "||:::||::|:::|:|:|::|||:::||:::|";
	BarCode testBarCode5 = new BarCode(testBarCodeString5);
	String testZipCode5 = testBarCode5.getZipCode();
	if (testZipCode5.equals("")) {
	    System.out.println("Error. " + testBarCodeString5 + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: " + testZipCode5);
	}
	System.out.println();

	// 7.
	System.out.println("Part 7.");
	String testBarCodeString7 = "||:|:::|:|:||::::::||:|::|:::||";
	BarCode testBarCode7 = new BarCode(testBarCodeString7);
	String testZipCode7 = testBarCode7.getZipCode();
	if (testZipCode7.equals("")) {
	    System.out.println("Error. " + testBarCodeString7 + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: " + testZipCode7);
	}
	System.out.println();

	// 8.
	System.out.println("Part 8.");
	String testBarCodeString8 = "||:|:::|:|:||::::::||:|::|:::||:";
	BarCode testBarCode8 = new BarCode(testBarCodeString8);
	String testZipCode8 = testBarCode8.getZipCode();
	if (testZipCode8.equals("")) {
	    System.out.println("Error. " + testBarCodeString8 + " is not a valid BarCode.");
	} else {
	    System.out.println("ZipCode: " + testZipCode8);
	}
	
    }
}
