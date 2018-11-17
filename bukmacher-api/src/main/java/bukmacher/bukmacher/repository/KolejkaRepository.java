package bukmacher.bukmacher.repository;

import bukmacher.bukmacher.model.encja.Kolejka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KolejkaRepository extends JpaRepository<Kolejka,Long> {
    Kolejka findFirstByIdLigiAndNumerKolejki(int idLigi, int numerKolejki);
}
