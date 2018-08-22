package atm.domain;

public class BankNote50PLN extends BankNote {

    public BankNote50PLN(Denomination denomination, Integer amount) {
        super(denomination, amount);
    }

    @Override
    public void setDenomination(Denomination denomination) {
        super.setDenomination(Denomination.FIFTY);
    }

    @Override
    public void setAmount(Integer amount) {
        super.setAmount(amount);
    }
}
