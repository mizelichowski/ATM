package atm.domain;

import javax.persistence.*;

@Entity
public class BankNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected Integer amount;
    @ManyToOne
    private ATM atm;

    public BankNote() {
    }

    public BankNote(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
