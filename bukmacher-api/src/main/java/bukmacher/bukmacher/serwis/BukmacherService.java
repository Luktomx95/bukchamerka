package bukmacher.bukmacher.serwis;

import bukmacher.bukmacher.model.dto.AktualnaKolejkaDto;
import bukmacher.bukmacher.model.dto.TypDto;
import bukmacher.bukmacher.model.dto.WydarzenieDto;

import java.util.List;

public interface BukmacherService {
    List<WydarzenieDto> przygotujListeWydarzen(long idLigi,int numerKolejki);
    Integer zwrocAktualnyNumerKolejki();
    AktualnaKolejkaDto zwrocAktualnyNumerKolejkiWLidze(long idLigi);
    void zapiszTyp(TypDto typDto);
}
