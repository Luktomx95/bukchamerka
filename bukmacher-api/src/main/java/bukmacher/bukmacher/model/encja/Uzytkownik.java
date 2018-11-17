package bukmacher.bukmacher.model.encja;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "uzytkownik")
@Data
@NoArgsConstructor
public class Uzytkownik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nazwa;

    @Column(nullable = false, precision = 7, scale = 2)    // Creates the database field with this size.
    @Digits(integer = 9, fraction = 2)                    // Validates data when used as a form
    private BigDecimal stanKonta;

    private LocalDate ostatnieLogowanie;

    public void rozliczTyp(Typ typ) {
        if (typ.getTypMeczWynik().equals(typ.getMecz().getWynikMeczu())) {
            this.stanKonta = new BigDecimal(typ.getKurs() * typ.getStawka()).add(this.stanKonta);
        }
    }

    public Uzytkownik(long id){
        this.id=id;
    }
}
