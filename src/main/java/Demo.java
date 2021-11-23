import repository.ProductDao;

public class Demo {
    //mvn exec:java -Dexec.mainClass=Demo -Dexec.args="1-2 6-7 3-3 4-1 6-8 card-1234"
    //mvn exec:java -Dexec.mainClass=Demo -Dexec.args="1-2 6-7 3-3 7-8 8-11"
    public static void main(String[] args) {
        ReceiptManager receiptManager = new ReceiptManager(new ProductDao(), System.out);
        receiptManager.printInformation();
        receiptManager.printHeader();
        receiptManager.printProducts(args);
        receiptManager.printTotal();
    }
}
