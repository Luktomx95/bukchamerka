package bukmacher.bukmacher.serwis.impl;

import bukmacher.bukmacher.komponenty.KlientApi;
import bukmacher.bukmacher.komponenty.impl.LigaHelperImpl;
import bukmacher.bukmacher.model.api.MeczDto;
import bukmacher.bukmacher.model.encja.*;
import bukmacher.bukmacher.model.enumy.MeczStatus;
import bukmacher.bukmacher.model.enumy.TypStatus;
import bukmacher.bukmacher.repository.*;
import bukmacher.bukmacher.serwis.AsynchronicznaObslugaMeczy;
import bukmacher.bukmacher.serwis.mapper.MeczMapper;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

//TODO: ustawić synchroniczne wywolanie uslug (cron)
@Log
@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class AsynchronicznaObslugaMeczyImpl implements AsynchronicznaObslugaMeczy {

    private final MeczRepository meczRepository;

    private final KlientApi klientApi;

    private final MeczMapper meczMapper;

    private final AktualnaKolejkaRepository aktualnaKolejkaRepository;

    private final KolejkaRepository kolejkaRepository;

    private final TypRepository typRepository;

    private final UzytkownikRepository uzytkownikRepository;


    @Override
    public void aktualizujWynikiMeczyIRozliczTypy() {
        LigaHelperImpl.ligi.forEach(liga -> {
                    AktualnaKolejka aktualnaKolejka = aktualnaKolejkaRepository.findFirstByIdLigi(liga.getIdLigi());
                    Kolejka definicjaKolejki = kolejkaRepository.findFirstByIdLigiAndNumerKolejki(liga.getIdLigi(), aktualnaKolejka.getNumerAktualnejKolejki());
                    if (LocalDate.now().isAfter(definicjaKolejki.getKoniecKolejki())) {

                        List<MeczDto> pobraneMecze = klientApi.pobierzDaneOMeczach(liga);
                        List<Mecz> meczeNierozpoczete = meczRepository.findAllByLigaAndKolejkaAndStatusMeczu(liga, aktualnaKolejka.getNumerAktualnejKolejki(), MeczStatus.NIEROZPOCZETY);
                        List<Mecz> meczeDoAktualizacji = new LinkedList<>();

                        meczeNierozpoczete.forEach(mecz -> {
                            MeczDto pobranyMecz = pobraneMecze.stream().filter(meczDto -> meczDto.getIdMeczu().equals(mecz.getId().toString())).findFirst().get();
                            meczeDoAktualizacji.add(meczMapper.mapToDbo(pobranyMecz));
                        });

                        log.info("Aktualizacja kolejki w lidze: " + liga.name() + ". Numer nowej kolejki: " + aktualnaKolejka.getNumerAktualnejKolejki() + 1);
                        aktualnaKolejka.setNumerAktualnejKolejki(aktualnaKolejka.getNumerAktualnejKolejki() + 1);
                        aktualnaKolejkaRepository.save(aktualnaKolejka);

                        log.info("Aktualizacja meczy w lidze: " + liga.name() + " Liczba zaaktualizowanych rekordow: " + meczeDoAktualizacji.size());
                        meczRepository.saveAll(meczeDoAktualizacji);

                    } else {
                        log.info("Aktualne wpisy meczow dla ligi: " + liga.name() + ". Nie ma potrzeby aktualizacji wynikow meczy");
                    }
                }
        );
        rozliczTypy();
    }

    private void rozliczTypy() {
        List<Typ> listaTypowDoRozliczenia = typRepository.findAllByStatusTypu(TypStatus.OTWARTY);
        List<Uzytkownik> uzytkownicyDoZaaktualiowania = new LinkedList<>();
        listaTypowDoRozliczenia.forEach(typ -> {
            Uzytkownik uzytkownik = typ.getUzytkownik();
            uzytkownik.rozliczTyp(typ);
            if (!uzytkownicyDoZaaktualiowania.contains(uzytkownik)) uzytkownicyDoZaaktualiowania.add(uzytkownik);
            typ.setStatusTypu(TypStatus.ZAMKNIETY);
        });
        typRepository.saveAll(listaTypowDoRozliczenia);
        uzytkownikRepository.saveAll(uzytkownicyDoZaaktualiowania);
    }


}
