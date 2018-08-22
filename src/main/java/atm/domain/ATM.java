package atm.domain;

public class ATM {
    private static ATM atmInstance;
    private static BankNotes bankNotes;

    public static ATM getInstance() {
        if (atmInstance == null) {
            atmInstance = new ATM();
        }
        return atmInstance;
    }
}


