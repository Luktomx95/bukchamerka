package bukmacher.bukmacher.repository;

import bukmacher.bukmacher.model.encja.AktualnaKolejka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AktualnaKolejkaRepository extends JpaRepository<AktualnaKolejka,Long> {
    AktualnaKolejka findFirstByIdLigi(int idLigi);

}
