package atm.domain;

public class Refill {
    private Integer BankNote20PLNAmount;
    private Integer BankNote50PLNAmount;
    private Integer BankNote100PLNAmount;
    private Integer BankNote200PLNAmount;

    public Refill() {
    }

    public Refill(Integer bankNote20PLNAmount, Integer bankNote50PLNAmount, Integer bankNote100PLNAmount, Integer bankNote200PLNAmount) {
        BankNote20PLNAmount = bankNote20PLNAmount;
        BankNote50PLNAmount = bankNote50PLNAmount;
        BankNote100PLNAmount = bankNote100PLNAmount;
        BankNote200PLNAmount = bankNote200PLNAmount;
    }

    public Integer getBankNote20PLNAmount() {
        return BankNote20PLNAmount;
    }

    public void setBankNote20PLNAmount(Integer bankNote20PLNAmount) {
        BankNote20PLNAmount = bankNote20PLNAmount;
    }

    public Integer getBankNote50PLNAmount() {
        return BankNote50PLNAmount;
    }

    public void setBankNote50PLNAmount(Integer bankNote50PLNAmount) {
        BankNote50PLNAmount = bankNote50PLNAmount;
    }

    public Integer getBankNote100PLNAmount() {
        return BankNote100PLNAmount;
    }

    public void setBankNote100PLNAmount(Integer bankNote100PLNAmount) {
        BankNote100PLNAmount = bankNote100PLNAmount;
    }

    public Integer getBankNote200PLNAmount() {
        return BankNote200PLNAmount;
    }

    public void setBankNote200PLNAmount(Integer bankNote200PLNAmount) {
        BankNote200PLNAmount = bankNote200PLNAmount;
    }
}
