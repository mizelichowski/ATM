package atm.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private Set<BankNote> bankNotes;

    public ATM() {
    }

    public ATM(Set<BankNote> bankNotes) {
        this.bankNotes = bankNotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BankNote> getBankNotes() {
        return bankNotes;
    }

    public void setBankNotes(Set<BankNote> bankNotes) {
        this.bankNotes = bankNotes;
    }
}
