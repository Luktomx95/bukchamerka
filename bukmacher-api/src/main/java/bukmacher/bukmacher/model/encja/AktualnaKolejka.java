package bukmacher.bukmacher.model.encja;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "aktualna_kolejka")
@NoArgsConstructor
@Data
public class AktualnaKolejka  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int idLigi;
    @Column
    private int numerAktualnejKolejki;
}
