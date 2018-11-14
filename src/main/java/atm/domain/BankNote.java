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

    public Long getId() {
        return id;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankNote{" +
                "denomination=" + denomination +
                ", amount=" + amount +
                '}';
    }
}