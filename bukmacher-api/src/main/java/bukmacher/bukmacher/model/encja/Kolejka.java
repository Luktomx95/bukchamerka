package bukmacher.bukmacher.model.encja;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "kolejka")
@NoArgsConstructor
public class Kolejka{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int idLigi;

    private int numerKolejki;

    private LocalDate poczatekKolejki;

    private LocalDate koniecKolejki;
}
