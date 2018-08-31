package atm.domain;

import javax.persistence.*;

@Entity
public class BankNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Denomination denomination;
    private Integer amount;

    public BankNote() {
    }

    public BankNote(Denomination denomination, Integer amount) {
        this.denomination = denomination;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}