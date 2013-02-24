public class BarCodeTest {
    public static void main(String[] args) {
	System.out.println("BarCodeTest");

	BarCode testBarCode = new BarCode("02138");
	System.out.println("ZipCode: " + testBarCode.getZipCode());
    }
}
