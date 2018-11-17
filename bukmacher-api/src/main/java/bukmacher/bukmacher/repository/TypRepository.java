package bukmacher.bukmacher.repository;

import bukmacher.bukmacher.model.encja.Typ;
import bukmacher.bukmacher.model.enumy.TypStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypRepository extends JpaRepository<Typ,Long> {
    List<Typ> findAllByStatusTypu(TypStatus statusTypu);
}
