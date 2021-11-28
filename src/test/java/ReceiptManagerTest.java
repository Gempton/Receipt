import by.java.testTask.ReceiptManager;
import by.java.testTask.model.ReceiptProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ReceiptManagerTest {

    private ReceiptManager receiptManager = new ReceiptManager();

    @Test
    void shouldCalculateTotalWithCard() {
        ReceiptProduct receiptProduct1 = new ReceiptProduct();
        ReceiptProduct receiptProduct2 = new ReceiptProduct();
        ReceiptProduct receiptProduct3 = new ReceiptProduct();

        receiptProduct1.setTotalPrice(50.0);
        receiptProduct2.setTotalPrice(70.0);
        receiptProduct3.setTotalPrice(100.0);

        List<ReceiptProduct> receiptProducts = List.of(receiptProduct1, receiptProduct2, receiptProduct3);

        double totalPrice = 0;
        for (ReceiptProduct receiptProduct : receiptProducts) {
            totalPrice += receiptProduct.getTotalPrice();
        }

        double discount = 0.15;
        totalPrice = totalPrice - (totalPrice * discount);

        Assertions.assertEquals(totalPrice, receiptManager.calculateTotal(receiptProducts, discount));
    }

    @Test
    void shouldCalculateTotalWithNoCard() {
        ReceiptProduct receiptProduct1 = new ReceiptProduct();
        ReceiptProduct receiptProduct2 = new ReceiptProduct();
        ReceiptProduct receiptProduct3 = new ReceiptProduct();

        receiptProduct1.setTotalPrice(50.0);
        receiptProduct2.setTotalPrice(70.0);
        receiptProduct3.setTotalPrice(100.0);

        List<ReceiptProduct> receiptProducts = List.of(receiptProduct1, receiptProduct2, receiptProduct3);

        double totalPrice = 0;
        for (ReceiptProduct receiptProduct : receiptProducts) {
            totalPrice += receiptProduct.getTotalPrice();
        }

        double discount = 0.0;

        Assertions.assertEquals(totalPrice, receiptManager.calculateTotal(receiptProducts, discount));
    }

    @Test
    void shouldGetCardDiscountWithNoCard() {
        String[] args = {"1-1", "2-2", "3-3"};

        double cardDiscount = 0.0;

        Assertions.assertEquals(cardDiscount, receiptManager.getCardDiscount(args));
    }

    @Test
    void shouldGetCardDiscountWithCard() {
        String[] args = {"1-1", "2-2", "3-3", "card-1234"};

        double cardDiscount = 0.15;

        Assertions.assertEquals(cardDiscount, receiptManager.getCardDiscount(args));
    }
}
