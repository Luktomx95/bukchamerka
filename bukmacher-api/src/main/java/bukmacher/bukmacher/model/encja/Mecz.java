package bukmacher.bukmacher.model.encja;


import bukmacher.bukmacher.model.enumy.Liga;
import bukmacher.bukmacher.model.enumy.MeczStatus;
import bukmacher.bukmacher.model.enumy.MeczWynik;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mecz")
@NoArgsConstructor
@Data
public class Mecz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazwaWydarzenia;

    private LocalDateTime dataWydarzenia;

    private int kolejka;

    @Enumerated(EnumType.STRING)
    private Liga liga;

    @Enumerated(EnumType.STRING)
    private MeczStatus statusMeczu;

    @Enumerated(EnumType.STRING)
    private MeczWynik wynikMeczu;

    public Mecz(Long id) {
        this.id = id;
    }
}
