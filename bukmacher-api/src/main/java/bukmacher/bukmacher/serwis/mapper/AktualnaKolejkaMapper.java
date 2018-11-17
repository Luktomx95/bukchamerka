package bukmacher.bukmacher.serwis.mapper;

import bukmacher.bukmacher.model.dto.AktualnaKolejkaDto;
import bukmacher.bukmacher.model.encja.AktualnaKolejka;

public interface AktualnaKolejkaMapper {
    AktualnaKolejkaDto mapToDto(AktualnaKolejka aktualnaKolejka);
}
