package bukmacher.bukmacher.model.encja;

import bukmacher.bukmacher.model.enumy.MeczWynik;
import bukmacher.bukmacher.model.enumy.TypStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "typ")
@Data
@NoArgsConstructor
public class Typ implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float kurs;

    private float stawka;

    @Enumerated(EnumType.STRING)
    private MeczWynik typMeczWynik;

    @Enumerated(EnumType.STRING)
    private TypStatus statusTypu;

    @ManyToOne(fetch = FetchType.EAGER)
    private Mecz mecz;

    @ManyToOne(fetch = FetchType.EAGER)
    private Uzytkownik uzytkownik;
}
