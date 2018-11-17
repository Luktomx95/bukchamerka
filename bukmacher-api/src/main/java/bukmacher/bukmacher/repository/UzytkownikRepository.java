package bukmacher.bukmacher.repository;

import bukmacher.bukmacher.model.encja.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UzytkownikRepository extends JpaRepository<Uzytkownik,Long> {
}
