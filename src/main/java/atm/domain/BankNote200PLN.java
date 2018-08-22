package atm.domain;

public class BankNote200PLN extends BankNote {

    public BankNote200PLN(Denomination denomination, Integer amount) {
        super(denomination, amount);
    }

    @Override
    public void setDenomination(Denomination denomination) {
        super.setDenomination(Denomination.TWO_HUNDRED);
    }

    @Override
    public void setAmount(Integer amount) {
        super.setAmount(amount);
    }
}
