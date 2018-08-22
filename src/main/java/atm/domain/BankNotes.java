package atm.domain;

public class BankNotes {
    private BankNote20PLN bankNote20PLN;
    private BankNote50PLN bankNote50PLN;
    private BankNote100PLN bankNote100PLN;
    private BankNote200PLN bankNote200PLN;

    public BankNotes() {
    }

    public BankNotes(BankNote20PLN bankNote20PLN, BankNote50PLN bankNote50PLN,
                     BankNote100PLN bankNote100PLN, BankNote200PLN bankNote200PLN) {
        this.bankNote20PLN = bankNote20PLN;
        this.bankNote50PLN = bankNote50PLN;
        this.bankNote100PLN = bankNote100PLN;
        this.bankNote200PLN = bankNote200PLN;
    }

    public BankNote20PLN getBankNote20PLN() {
        return bankNote20PLN;
    }

    public void setBankNote20PLN(BankNote20PLN bankNote20PLN) {
        this.bankNote20PLN = bankNote20PLN;
    }

    public BankNote50PLN getBankNote50PLN() {
        return bankNote50PLN;
    }

    public void setBankNote50PLN(BankNote50PLN bankNote50PLN) {
        this.bankNote50PLN = bankNote50PLN;
    }

    public BankNote100PLN getBankNote100PLN() {
        return bankNote100PLN;
    }

    public void setBankNote100PLN(BankNote100PLN bankNote100PLN) {
        this.bankNote100PLN = bankNote100PLN;
    }

    public BankNote200PLN getBankNote200PLN() {
        return bankNote200PLN;
    }

    public void setBankNote200PLN(BankNote200PLN bankNote200PLN) {
        this.bankNote200PLN = bankNote200PLN;
    }
}
