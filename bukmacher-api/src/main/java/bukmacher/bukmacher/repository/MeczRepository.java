package bukmacher.bukmacher.repository;

import bukmacher.bukmacher.model.encja.Mecz;
import bukmacher.bukmacher.model.enumy.Liga;
import bukmacher.bukmacher.model.enumy.MeczStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeczRepository extends JpaRepository<Mecz, Long> {
    List<Mecz> findAllByLigaAndKolejkaAndStatusMeczu(Liga liga, int kolejka,MeczStatus meczStatus);
    List<Mecz> findAllByLigaAndKolejka(Liga liga, int kolejka);
}
