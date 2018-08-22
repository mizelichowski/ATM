package atm.domain;

public class BankNote20PLN extends BankNote {

    public BankNote20PLN(Denomination denomination, Integer amount) {
        super(denomination, amount);
    }

    @Override
    public void setDenomination(Denomination denomination) {
        super.setDenomination(Denomination.TWENTY);
    }

    @Override
    public void setAmount(Integer amount) {
        super.setAmount(amount);
    }
}