package bukmacher.bukmacher.repository;

import bukmacher.bukmacher.model.encja.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UzytkownikRepository extends JpaRepository<Uzytkownik,Long> {
    Optional<Uzytkownik> findOneByNazwa(String nazwa);
}
