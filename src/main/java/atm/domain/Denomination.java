package atm.domain;

public enum Denomination {
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200);

    private final int amount;

    Denomination(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
