package service;

public class DiscountService {

    private static final Double DISCOUNT = 0.15;
    private boolean hasCard;

    public DiscountService(boolean hasCard) {
        this.hasCard = hasCard;
    }

    public double calculateDiscount(double sum) {
        if (hasCard) {
            return sum - (sum * DISCOUNT);
        }
        return sum;
    }

    public boolean isHasCard() {
        return hasCard;
    }

    public void setHasCard(boolean hasCard) {
        this.hasCard = hasCard;
    }
}
