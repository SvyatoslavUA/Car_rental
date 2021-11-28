package lpnu.entity.enumeration;

public enum CarClass {
    ECONOMY (100),
    COMFORT (150),
    BUSINESS(200);

    private int price;

    CarClass(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
