package atm.domain;

public class BankNote100PLN extends BankNote {

    public BankNote100PLN(Denomination denomination, Integer amount) {
        super(denomination, amount);
    }

    @Override
    public void setDenomination(Denomination denomination) {
        super.setDenomination(Denomination.HUNDRED);
    }

    @Override
    public void setAmount(Integer amount) {
        super.setAmount(amount);
    }
}
