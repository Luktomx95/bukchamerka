package bukmacher.bukmacher.serwis.mapper.impl;

import bukmacher.bukmacher.model.dto.AktualnaKolejkaDto;
import bukmacher.bukmacher.model.encja.AktualnaKolejka;
import bukmacher.bukmacher.serwis.mapper.AktualnaKolejkaMapper;
import org.springframework.stereotype.Component;



@Component
public class AktualnaKolejkaMapperImpl implements AktualnaKolejkaMapper {
    @Override
    public AktualnaKolejkaDto mapToDto(AktualnaKolejka aktualnaKolejka) {
        return new AktualnaKolejkaDto().aktualnyNumerKolejki(aktualnaKolejka.getNumerAktualnejKolejki()).idLigi(aktualnaKolejka.getIdLigi());
    }
}
