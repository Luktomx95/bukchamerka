package bukmacher.bukmacher.serwis.mapper.impl;

import bukmacher.bukmacher.model.api.MeczDto;
import bukmacher.bukmacher.model.encja.Mecz;
import bukmacher.bukmacher.model.enumy.Liga;
import bukmacher.bukmacher.model.enumy.MeczStatus;
import bukmacher.bukmacher.model.enumy.MeczWynik;
import bukmacher.bukmacher.serwis.mapper.MeczMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeczMapperImpl implements MeczMapper {

    private final DateTimeFormatter formatterToDto = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final DateTimeFormatter formatterToDbo = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public Mecz mapToDbo(MeczDto meczDto) {
        Mecz mecz = new Mecz();

        mecz.setId(new Long(meczDto.getIdMeczu()));
        mecz.setDataWydarzenia(LocalDateTime.parse(meczDto.getDataWydarzenia(), formatterToDbo));
        mecz.setKolejka(znajdzNumerKolejki(meczDto.getInformacjeOKolejce()));
        mecz.setNazwaWydarzenia(zlozNazweWydarzenia(meczDto.getGospodarz(), meczDto.getGosc()));
        ustawLige(mecz,meczDto);
        if (MeczStatus.NIEROZPOCZETY.getStatus().equals(meczDto.getStatus())) {
            mecz.setStatusMeczu(MeczStatus.NIEROZPOCZETY);
        } else {
            mecz.setStatusMeczu(MeczStatus.ZAKONCZONY);
            ustawWynikMeczu(mecz, meczDto);
        }
        return mecz;
    }

    @Override
    public MeczDto mapToDto(Mecz mecz) {
        return null;
    }

    @Override
    public List<Mecz> mapListToDbo(List<MeczDto> meczeDto) {
        return meczeDto.stream().map(this::mapToDbo).collect(Collectors.toList());
    }

    private int znajdzNumerKolejki(String infoOKolejce){
       String[] parts=  infoOKolejce.split("-");
       return new Integer(parts[1].trim());
    }

    private String zlozNazweWydarzenia(String gospodarz, String gosc) {
        StringBuilder sb = new StringBuilder(gospodarz);
        sb.append(" - ");
        sb.append(gosc);
        return sb.toString();
    }

    private void ustawWynikMeczu(Mecz mecz, MeczDto meczDto) {
        if (meczDto.getLiczbaGoliGospodarzy() > meczDto.getLiczbaGoliGosci()) mecz.setWynikMeczu(MeczWynik.GOSPODARZ);
        else if (meczDto.getLiczbaGoliGosci() > meczDto.getLiczbaGoliGospodarzy()) mecz.setWynikMeczu(MeczWynik.GOSCIE);
        else mecz.setWynikMeczu(MeczWynik.REMIS);
    }

    private void ustawLige(Mecz mecz, MeczDto meczDto) {
        switch (meczDto.getIdLigi()) {
            case 87:
                mecz.setLiga(Liga.LALIGA);
                break;
            case 2:
                mecz.setLiga(Liga.PREMIER_LEAUGE);
                break;
            case 94:
                mecz.setLiga(Liga.SERIEA);
                break;
            case 8:
                mecz.setLiga(Liga.BUNDESLIGA);
                break;
            case 4:
                mecz.setLiga(Liga.LIGUE1);
                break;
            case 16:
                mecz.setLiga(Liga.EXTRAKLASA);
                break;
        }
    }

}
