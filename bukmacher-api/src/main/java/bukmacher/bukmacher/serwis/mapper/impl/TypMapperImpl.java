package bukmacher.bukmacher.serwis.mapper.impl;

import bukmacher.bukmacher.model.dto.TypDto;
import bukmacher.bukmacher.model.encja.Mecz;
import bukmacher.bukmacher.model.encja.Typ;
import bukmacher.bukmacher.model.encja.Uzytkownik;
import bukmacher.bukmacher.model.enumy.MeczWynik;
import bukmacher.bukmacher.model.enumy.TypStatus;
import bukmacher.bukmacher.serwis.mapper.TypMapper;
import org.springframework.stereotype.Component;

@Component
public class TypMapperImpl implements TypMapper {
    @Override
    public Typ mapDoDbo(TypDto typDto) {
        Typ typ = new Typ();
        typ.setKurs(typDto.getKurs());
        typ.setMecz(new Mecz(typDto.getIdMeczu()));
        typ.setStatusTypu(TypStatus.OTWARTY);
        typ.setUzytkownik(new Uzytkownik(typDto.getIdUzytkownika()));
        typ.setStawka(typDto.getStawka());
        typ.setTypMeczWynik(zwrocMeczWynik(typDto.getTypMecz()));
        return typ;
    }

    private MeczWynik zwrocMeczWynik(String typ){
        switch (typ){
            case "1": return MeczWynik.GOSPODARZ;
            case "2": return MeczWynik.GOSCIE;
            case "X": return MeczWynik.REMIS;
        }
        return null;
    }
}
